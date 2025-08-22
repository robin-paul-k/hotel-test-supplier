package com.emimol.hotel.dto.search.request; 
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
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
}
