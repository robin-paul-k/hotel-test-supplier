package com.emimol.hotel.dto.book.request; 
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaxDetail{
    @NotNull(message = "PaxSrNo is required")
    @JsonProperty("PaxSrNo")
    private Integer paxSrNo;

    @NotBlank(message = "IsChild cannot be blank")
    @JsonProperty("IsChild")
    private String isChild;

    @NotBlank(message = "Title cannot be blank")
    @JsonProperty("Title")
    private String title;

    @NotBlank(message = "FirstName cannot be blank")
    @JsonProperty("FirstName")
    private String firstName;

    @NotBlank(message = "LastName cannot be blank")
    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("ChildAge")
    private Integer childAge;

    @JsonProperty("PassportNo")
    private String passportNo;
}
