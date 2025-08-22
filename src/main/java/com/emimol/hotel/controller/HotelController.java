package com.emimol.hotel.controller;

import com.emimol.hotel.dto.book.request.BookRQ;
import com.emimol.hotel.dto.book.response.BookRS;
import com.emimol.hotel.dto.search.request.SearchRQ;
import com.emimol.hotel.dto.search.response.SearchRS;
import com.emimol.hotel.service.BookService;
import com.emimol.hotel.service.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/test-hotel")
public class HotelController {

    private final SearchService searchService;
    private final BookService bookService;

    @PostMapping("/search")
    public SearchRS search(@Valid @RequestBody SearchRQ searchRQ) throws JsonProcessingException {
        return searchService.search(searchRQ);
    }

    @PostMapping("/book")
    public BookRS book(@Valid @RequestBody BookRQ bookRQ) {
        return bookService.book(bookRQ);
    }
}
