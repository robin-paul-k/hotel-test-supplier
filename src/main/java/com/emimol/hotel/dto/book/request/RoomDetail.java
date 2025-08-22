package com.emimol.hotel.dto.book.request; 
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RoomDetail{

    @NotNull(message = "RoomSrNo is required")
    @JsonProperty("RoomSrNo")
    private Integer roomSrNo;

    @NotNull(message = "NoOfAdult is required")
    @JsonProperty("NoOfAdult")
    private Integer noOfAdult;

    @JsonProperty("NoOfChild")
    private Integer noOfChild;

    @JsonProperty("ChildAges")
    private List<Integer> childAges;

    @NotBlank(message = "RateKey cannot be blank")
    @JsonProperty("RateKey")
    private String rateKey;

    @Valid
    @NotNull(message = "PaxDetails list is required")
    @Size(min = 1, message = "At least one PaxDetails is required")
    @JsonProperty("PaxDetails")
    private List<PaxDetail> paxDetails;
}
