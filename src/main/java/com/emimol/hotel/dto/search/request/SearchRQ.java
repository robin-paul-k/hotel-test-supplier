package com.emimol.hotel.dto.search.request; 
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class SearchRQ {
    @NotNull(message = "CityId is required")
    @JsonProperty("CityId")
    private Integer cityId;

    @NotBlank(message = "NationalityId cannot be blank")
    @JsonProperty("NationalityId")
    private String nationalityId;

    @NotBlank(message = "CheckInDate cannot be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "CheckInDate must be in yyyy-MM-dd format")
    @JsonProperty("CheckInDate")
    private String checkInDate;

    @NotBlank(message = "CheckOutDate cannot be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "CheckOutDate must be in yyyy-MM-dd format")
    @JsonProperty("CheckOutDate")
    private String checkOutDate;

    @JsonProperty("HCodes")
    private String hCodes;

    @Valid
    @NotNull(message = "RoomDetail list is required")
    @Size(min = 1, message = "At least one RoomDetail is required")
    @JsonProperty("RoomDetail")
    private List<RoomDetail> roomDetail;
}
