package com.sunny.WebAPI.Controller;

import com.sunny.LogIn.SessionManager;
import com.sunny.Service.CustomerProjectService;
import com.sunny.WebAPI.Dto.CouponDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Customer API", description = "Operation related to customer service")
public class CustomerController {

    private final CustomerProjectService service;
    private final SessionManager sessionManager;

    @Operation(summary = "Get all the purchased coupon by customer token")
    @ApiResponse(responseCode = "200", description = "successful operation")
    @GetMapping("/{token}/AllPurchasedCoupons")
    public ResponseEntity<Set<CouponDto>> getAllPurchasedCouponsByCustomerToken(@PathVariable String token) {
        return ResponseEntity.ok(service.getPurchasedCoupon(sessionManager.handleCustomerToken(token)));
    }

    @Operation(summary = "Get all the un purchased coupons by customer token")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/{token}/AllUnPurchasedCoupons")
    public ResponseEntity<Set<CouponDto>> getAllUnPurchasedCouponsByCustomerToken(@PathVariable String token) {
        return ResponseEntity.ok(service.getUnPurchasedCoupon(sessionManager.handleCustomerToken(token)));
    }


    @Operation(summary = "Get expiring coupons by customer token")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/{token}/ExpiringCoupons")
    public ResponseEntity<Set<CouponDto>> getExpiringCouponsByCustomerToken(@PathVariable String token) {
        return ResponseEntity.ok(service.getExpiringCoupons(sessionManager.handleCustomerToken(token)));
    }

    @Operation(summary = "Purchase a coupon by customer token and coupon UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Coupon not found")
    })
    @PostMapping("/{token}/{uuid}/coupon/purchasingCoupon")
    public ResponseEntity<Optional<CouponDto>> purchasingCoupon(@PathVariable UUID uuid,
                                                                @PathVariable String token) {
        return ResponseEntity.ok(service.purchaseCoupon(sessionManager.handleCustomerToken(token), uuid));
    }
}

