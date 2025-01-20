package com.sunny.Data.Repository;

import com.sunny.Data.Entity.Coupon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByUuid(UUID couponUuid);

    @Query("select c from Coupon c join c.customers customer where customer.uuid = :customerUuid")
    Set<Coupon> findPurchasedCouponsByCustomer(@Param("customerUuid") UUID customerUuid);

    @Transactional
    @Modifying
    @Query("delete from Coupon c where c.endDate < :currentDate")
    void deleteExpiredCoupons( LocalDate currentDate);

}
