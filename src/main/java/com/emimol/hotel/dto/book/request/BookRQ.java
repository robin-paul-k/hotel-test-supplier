package com.emimol.hotel.dto.book.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class BookRQ {
    @NotNull(message = "CityId is required")
    @JsonProperty("CityId")
    private Integer cityId;

    @NotNull(message = "NationalityId is required")
    @JsonProperty("NationalityId")
    private Integer nationalityId;

    @NotBlank(message = "CheckInDate cannot be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "CheckInDate must be in yyyy-MM-dd format")
    @JsonProperty("CheckInDate")
    private String checkInDate;

    @NotBlank(message = "CheckOutDate cannot be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "CheckOutDate must be in yyyy-MM-dd format")
    @JsonProperty("CheckOutDate")
    private String checkOutDate;

    @NotBlank(message = "HCode cannot be blank")
    @JsonProperty("HCode")
    private String hCode;

    @NotBlank(message = "HKey cannot be blank")
    @JsonProperty("HKey")
    private String hKey;

    @NotBlank(message = "TokenId cannot be blank")
    @JsonProperty("TokenId")
    private String tokenId;

    @NotBlank(message = "ClientRefNo cannot be blank")
    @JsonProperty("ClientRefNo")
    private String clientRefNo;

    @NotNull(message = "ExpectedAmount is required")
    @JsonProperty("ExpectedAmount")
    private Integer expectedAmount;

    @JsonProperty("AirlineName")
    private String airlineName;

    @JsonProperty("AirlinePNR")
    private String airlinePNR;

    @NotBlank(message = "VoucherBooking cannot be blank")
    @JsonProperty("VoucherBooking")
    private String voucherBooking;

    @Valid
    @NotNull(message = "RoomDetail list is required")
    @Size(min = 1, message = "At least one RoomDetail is required")
    @JsonProperty("RoomDetail")
    private List<RoomDetail> roomDetail;
}
