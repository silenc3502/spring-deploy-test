package com.example.springdeploytest.order.service;

import com.example.springdeploytest.account.entity.Account;
import com.example.springdeploytest.account.repository.AccountRepository;
import com.example.springdeploytest.book.entity.Book;
import com.example.springdeploytest.book.repository.BookRepository;
import com.example.springdeploytest.order.entity.OrderItem;
import com.example.springdeploytest.order.entity.Orders;
import com.example.springdeploytest.order.repository.OrderItemRepository;
import com.example.springdeploytest.order.repository.OrdersRepository;
import com.example.springdeploytest.order.service.request.CreateAllOrderItemRequest;
import com.example.springdeploytest.order.service.request.CreateAllOrdersRequest;
import com.example.springdeploytest.order.service.request.CreateOrderItemRequest;
import com.example.springdeploytest.order.service.response.CreateAllOrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    final private OrdersRepository ordersRepository;
    final private OrderItemRepository orderItemRepository;
    final private BookRepository bookRepository;
    final private AccountRepository accountRepository;

    @Override
    @Transactional
    public CreateAllOrdersResponse createAll(
            CreateAllOrdersRequest ordersRequest,
            CreateAllOrderItemRequest orderItemRequest) {

        // accountId로 Account 찾기
        // 사용자가 유효한지 여부 파악
        Long accountId = ordersRequest.getAccountId();
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        // Orders의 경우 사용자가 유효하니 즉시 생성
        Orders orders = ordersRequest.toOrders(account);
        Orders savedOrders = ordersRepository.save(orders);

        // 실제 orderItemRequest에는 { id .... }, { id .... }, { id .... } 형식으로 배치될 것이므로
        // id만 딸로 빼놓은 리스트를 구성하기 위해 아래와 같은 작업을 진행함.
        // getBookId를 통해 bookId 값만 뽑아오고 List로 만듬
        List<Long> bookIdList = orderItemRequest.getOrderItems().stream()
                .map(CreateOrderItemRequest::getBookId)
                .distinct()
                .collect(Collectors.toList());

        // findAll -> 조금 더 확장된 개념인 findAllById 가 나타남.
        // findAll과 하는 일은 동일하지만 조건이 추가된 형식이라 보면 됩니다.
        // 조건은 bookIdList에 해당하는 모든 정보를 검색합니다.
        List<Book> bookList = bookRepository.findAllById(bookIdList);
        if (bookList.size() != bookIdList.size()) {
            throw new IllegalArgumentException("존재하지 않는 책이 포함되었습니다.");
        }

        // Orders는 주문 그 자체이므로 1개일 수 밖에 없음
        // 그러나 주문에 포함된 항목들은 다수일 수 있음.
        // 그렇기에 OrderItem은 리스트 형식으로 구성됨
        // OrderItem 리스트를 저장하기 위해 save가 아닌 saveAll로 bulk 연산함 (다중 처리)
        // for 루프 돌면서 save 하는 것과 saveAll의 차이는 결국 데이터 무결성을 보장하기 위함
        // 데이터 무결성이라는 것이 1 + 1 = 2가 나와야 하는데
        // 1 + 1 = 1 이 나오는 괴현상을 나타나지 않게 해주는 것임.
        // 좀 더 전문 용어로는 Lock을 걸어서 데이터를 안전하게 보호한다 보면 되는데
        // 그냥 다수의 데이터를 처리할 때는 무조건 saveAll을 사용한다 생각하는 것이 속편함.
        List<OrderItem> orderItemList = orderItemRequest.toOrderItemList(bookList, savedOrders);
        List<OrderItem> savedOrderItemList = orderItemRepository.saveAll(orderItemList);

        return CreateAllOrdersResponse.from(savedOrders, savedOrderItemList);
    }
}
