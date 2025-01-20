package com.sunny.Data.Repository;

import com.sunny.Data.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    Optional<Company> findByEmailAndPassword(String email, String password);

    Optional<Company> findByUuid(UUID uuid);
}
