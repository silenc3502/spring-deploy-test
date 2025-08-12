package com.example.springdeploytest.book.service;

import com.example.springdeploytest.book.service.request.ListBookRequest;
import com.example.springdeploytest.book.service.request.RegisterBookRequest;
import com.example.springdeploytest.book.service.response.ListBookResponse;
import com.example.springdeploytest.book.service.response.RegisterBookResponse;

public interface BookService {
    RegisterBookResponse register(RegisterBookRequest request, Long accountId);
    ListBookResponse list(ListBookRequest request);
}
