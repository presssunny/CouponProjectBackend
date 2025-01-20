package com.sunny.WebAPI.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "uuid")
@Schema(description = "Data Transfer Object for Coupon")
public class CouponDto {

    @JsonProperty("id")
    @Schema(description = "Unique identifier of the Coupon",
            example = "25682f8g-56d5-480f-dg54-d25384f238df")
    private UUID uuid;
    @Schema(description = "The category of the coupon",
            example = "Clothing")
    private final String category;
    @Schema(description = "Title or name of the coupon",
            example = "50% Off Summer Sale")
    private final String title;
    @Schema(description = "The starting date of the coupon",
            example = "02/06/2024")
    private final LocalDate startDate;
    @Schema(description = "The starting date of the coupon",
            example = "02/06/2025")
    private final LocalDate endDate;
    @Schema(description = "The amount of existing coupons",
            example = "150")
    private final int amount;
    @Schema(description = "Additional details or features of the coupon",
            example = "Valid for online purchases only. Cannot be combined with other offers.")
    private final String description;
    @Schema(description = "The price of the coupon",
            example = "150.99")
    private final double price;
    @Schema(description = "Image URL or path of the coupon",
            example = "https://example.com/coupon-image.jpg")
    private String image;

}
