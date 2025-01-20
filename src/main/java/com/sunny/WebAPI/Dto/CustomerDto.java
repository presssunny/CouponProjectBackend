package com.sunny.WebAPI.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "uuid")
@Schema(description = "Data Transfer Object for Customer")
public class CustomerDto {
    @JsonProperty("id")
    @Schema(description = "Unique identifier of the customer",
            example = "25682f8g-56d5-480f-dg54-d25384f238df")
    private UUID uuid;

    @Schema(description = "firstName of the customer",
            example = "sunny")
    private String firstName;

    @Schema(description = "lastName of the customer",
            example = "press")
    private String lastName;

    @Schema(description = "email of the customer",
            example = "presssunny@gmail.com")
    private String email;

}
