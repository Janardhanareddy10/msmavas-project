package com.APB.main.Asia_Payments_Bank.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.APB.main.Asia_Payments_Bank.model.Customer;


public interface CustomerRepo extends JpaRepository<Customer,Long> {
	Optional<Customer> findOneByEmailAndPassword(String email, String password);
	 Optional<Customer> findByEmail(String email);
    
    boolean existsByEmail(String email);
    boolean existsByMobileNumber(String mobileNumber);
    Optional<Customer> findByVerificationToken(String verificationToken); 

}
