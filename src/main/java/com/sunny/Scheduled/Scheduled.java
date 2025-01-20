package com.sunny.Scheduled;

import com.sunny.Data.Repository.CouponRepository;
import com.sunny.LogIn.ClientSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class Scheduled {
    private final CouponRepository couponRepository;
    private final Map<String, ClientSession> tokens;

    @Autowired
    public Scheduled(CouponRepository couponRepository, Map<String, ClientSession> tokens) {
        this.couponRepository = couponRepository;
        this.tokens = tokens;
    }

    @Transactional
    @org.springframework.scheduling.annotation.Scheduled(fixedRate = 300000)
    public void sessionExpiredCheck() {
        boolean answer = tokens.values().removeIf(ClientSession::expired);
        if (answer) {
            System.out.println("An active session that expired was detected and automatically deleted due to expiration.");
        }
        System.out.println("No active sessions that expired were detected during the session check.");
    }

    @Transactional
    @org.springframework.scheduling.annotation.Scheduled(cron = "0 * * * * *")
    public void deleteExpiredCoupons() {
                 couponRepository.deleteExpiredCoupons(LocalDate.now());
        }

}