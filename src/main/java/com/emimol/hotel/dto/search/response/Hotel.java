package com.emimol.hotel.dto.search.response; 
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Hotel{
    @JsonProperty("HCode") 
    private String hCode;
    @JsonProperty("HName") 
    private String hName;
    @JsonProperty("Available") 
    private boolean available;
    @JsonProperty("Amount") 
    private double amount;
}
