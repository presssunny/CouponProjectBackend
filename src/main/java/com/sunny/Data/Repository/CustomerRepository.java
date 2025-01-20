package com.sunny.Data.Repository;

import com.sunny.Data.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUuid(UUID customerUuid);
    Optional<Customer> findByEmailAndPassword(String email, String password);


}
