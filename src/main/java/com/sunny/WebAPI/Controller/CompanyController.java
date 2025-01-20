package com.sunny.WebAPI.Controller;

import com.sunny.LogIn.SessionManager;
import com.sunny.Service.CompanyProjectService;
import com.sunny.WebAPI.Dto.CouponDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

;

@Controller
@ResponseBody
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Company API", description = "Operation related to company service")

public class CompanyController {

    private final CompanyProjectService companyService;
    private final SessionManager sessionManager;

    @PostMapping("/company/create/{token}")
    @Operation(summary = "Create a new coupon with company token")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Coupon created successfully"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<CouponDto> createCoupon(@PathVariable String token,
                                                  @RequestBody CouponDto couponDto) {

        CouponDto createdCoupon = companyService.createCoupon(sessionManager.handleCompanyToken(token), couponDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(couponDto.getUuid())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdCoupon);
    }


    @DeleteMapping("/company/delete/{token}/{couponDtoUuid}")
    @Operation(summary = "Delete a coupon with company token and coupon UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Coupon deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Coupon not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable String token,
                             @PathVariable UUID couponDtoUuid) {
        companyService.deleteCoupon(sessionManager.handleCompanyToken(token), couponDtoUuid);
    }

    @PutMapping("/company/update-amount/{token}/{couponDtoUuid}")
    @Operation(summary = "Update the quantity of a coupon with company token and coupon UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Coupon quantity updated successfully"),
            @ApiResponse(responseCode = "404", description = "Coupon not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<CouponDto> updateCouponQuantity(@PathVariable String token,
                                                          @PathVariable UUID couponDtoUuid,
                                                          @RequestParam int quantity) {

        CouponDto updatedCoupon = companyService.updateCouponQuantity(sessionManager.handleCompanyToken(token),
                couponDtoUuid,
                quantity);

        return ResponseEntity.ok(updatedCoupon);
    }

    @GetMapping("/company/seeAll/{token}")
    @Operation(summary = "Get all coupons with company token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Company not found")
    })
    public ResponseEntity<Set<CouponDto>> seeAllCoupon(@PathVariable String token) {
        Set<CouponDto> allCoupons = companyService.seeAllCoupon(sessionManager.handleCompanyToken(token));

        return ResponseEntity.ok(allCoupons);
    }

}

