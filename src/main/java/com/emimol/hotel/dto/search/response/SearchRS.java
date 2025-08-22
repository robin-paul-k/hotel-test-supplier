package com.emimol.hotel.dto.search.response; 
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchRS {
    @JsonProperty("Currency") 
    private String currency;
    @JsonProperty("TokenId") 
    private String tokenId;
    @JsonProperty("Hotels") 
    private List<Hotel> hotels;
}
