package com.sunny.Service;

import com.sunny.Data.Entity.Company;
import com.sunny.Data.Entity.Customer;
import com.sunny.Data.Repository.CompanyRepository;
import com.sunny.Data.Repository.CustomerRepository;
import com.sunny.LogIn.ClientSession;
import com.sunny.Service.Ex.LoginException.InvalidLoginException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class LoginServiceImpl implements LoginService {
    public static final int LENGTH_TOKEN = 10;
    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    private final int tokenExpirationMinutes;
    private final static int CUSTOMER_TYPE = 0;
    private final static int COMPANY_TYPE = 1;

    public LoginServiceImpl(CustomerRepository customerRepository, CompanyRepository companyRepository,
                            @Value("${token.expiration.minutes}") int tokenExpirationMinutes) {
        this.customerRepository = customerRepository;
        this.companyRepository = companyRepository;
        this.tokenExpirationMinutes = tokenExpirationMinutes;
    }


    @Override
    public ClientSession createSession(String email, String password, int type) {
        if (type == CUSTOMER_TYPE) {
            Optional<Customer> optCustomer = customerRepository.findByEmailAndPassword(email, password);
            UUID uuid = optCustomer
                    .orElseThrow (()-> new InvalidLoginException("Invalid login credentials"))
                    .getUuid();
            return ClientSession.ofNow(uuid, tokenExpirationMinutes, type);
        } else if (type == COMPANY_TYPE) {
            Optional<Company> optCompany = companyRepository.findByEmailAndPassword(email, password);
            UUID uuid = optCompany
                    .orElseThrow(()-> new InvalidLoginException("Invalid login credentials"))
                    .getUuid();
            return ClientSession.ofNow(uuid, tokenExpirationMinutes, type);

        } else {
            throw new IllegalArgumentException("Invalid login type: " + type);
        }
    }

    @Override
    public String generateToken() {
        return UUID.randomUUID()
                .toString()
                .replaceAll("-", "")
                .substring(0, LENGTH_TOKEN);
    }
}
