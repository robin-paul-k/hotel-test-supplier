package com.emimol.hotel.dto.book.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRS {
    @JsonProperty("APIRefNo")
    private String aPIRefNo;
}
