package com.sunny.Service;

import com.sunny.WebAPI.Dto.CouponDto;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CustomerProjectService {

    Set<CouponDto> getPurchasedCoupon(UUID customerUuid);
    Set<CouponDto> getUnPurchasedCoupon(UUID customerUuid);
    Set<CouponDto> getExpiringCoupons(UUID customerUuid);
    Optional<CouponDto> purchaseCoupon(UUID customerUuid,UUID couponUuid);
}
