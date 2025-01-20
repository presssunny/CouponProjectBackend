package com.sunny.Service;

import com.sunny.WebAPI.Dto.CouponDto;
import java.util.Set;
import java.util.UUID;

public interface CompanyProjectService {
    CouponDto createCoupon(UUID uuid, CouponDto couponDto);

    void deleteCoupon(UUID uuid, UUID couponDtoUuid);

    CouponDto updateCouponQuantity(UUID uuid, UUID couponDtoUuid, int quantity);

    Set<CouponDto> seeAllCoupon(UUID uuid);

}