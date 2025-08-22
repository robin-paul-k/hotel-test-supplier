package com.emimol.hotel.service;

import com.emimol.hotel.dto.book.request.BookRQ;
import com.emimol.hotel.dto.book.response.BookRS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    public BookRS book(BookRQ bookRQ) {
        return new BookRS("1_120-1902736_1");
    }
}