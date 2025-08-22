package com.emimol.hotel.service;

import com.emimol.hotel.dto.search.request.SearchRQ;
import com.emimol.hotel.dto.search.response.SearchRS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {

    private final ObjectMapper objectMapper;

    public SearchRS search(SearchRQ searchRQ) throws JsonProcessingException {
        String json= """
                {
                    "Currency": "USD",
                    "TokenId": "42d06aa7-06f1-49af-9dec-3fa3ecddf214",
                    "Hotels": [
                        {
                            "HCode": "DXB9890898",
                            "HName": "City King Hotel",
                            "Available": true,
                            "Amount": 572.0
                        },
                        {
                            "HCode": "DXB9891106",
                            "HName": "Prime Hotel",
                            "Available": true,
                            "Amount": 608.0
                        },
                        {
                            "HCode": "DXB9891249",
                            "HName": "Al Raein Hotel Apartment",
                            "Available": true,
                            "Amount": 650.0
                        },
                        {
                            "HCode": "DXB9890275",
                            "HName": "Citymax Hotels Bur Dubai",
                            "Available": true,
                            "Amount": 651.0
                        },       \s
                        {
                            "HCode": "DXB9890001",
                            "HName": "Jood Palace Hotel Dubai",
                            "Available": true,
                            "Amount": 1350.0
                        }  \s
                    ]
                }
                """;

        return objectMapper.readValue(json,SearchRS.class);
    }
}
