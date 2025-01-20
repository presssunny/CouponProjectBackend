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
@Schema(description = "Data Transfer Object for Client")
public class CompanyDto {

    @JsonProperty("id")
    @Schema(description ="Unique identifier of the company",
            example = "25682f8g-56d5-480f-dg54-d25384f238df")
    private UUID uuid;

    @Schema(description = "Name of the company", example = "zara")
    private String name;

    @Schema(description = "email of the company",
            example = "zaraMail@gmail.com")
    private String email;

}
