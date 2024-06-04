package com.APB.main.Asia_Payments_Bank.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.APB.main.Asia_Payments_Bank.DTO.CustomerDTO;
import com.APB.main.Asia_Payments_Bank.model.Customer;
import com.APB.main.Asia_Payments_Bank.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    @Override
    public String addCustomer123(CustomerDTO customerDTO) {
        String verificationToken = UUID.randomUUID().toString();
        String verificationLink = "http://localhost:8090/asia/api/customer/verify-email?token=" + verificationToken;

        String emailContent = "Dear " + customerDTO.getFirstName() + ",\n\n"
                + "Thank you for registering with us. Please click on the following link to verify your email address:\n\n"
                + verificationLink + "\n\n"
                + "Best regards,\n"
                + "Your Company";

        try {
            emailService.sendEmail(customerDTO.getEmail(), "Email Verification", emailContent);
        } catch (Exception e) {
            // Handle email sending failure
            e.printStackTrace();
            return null; // or throw an exception
        }

        Customer customer = new Customer();
       
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        customer.setAddress(customerDTO.getAddress());
        customer.setCity(customerDTO.getCity());
        customer.setState(customerDTO.getState());
        customer.setZipCode(customerDTO.getZipCode());
        customer.setVerificationToken(verificationToken);

        customerRepo.save(customer);
        return customer.getFirstName();
    }

    @Override
    public boolean verifyEmail(String token) {
        Optional<Customer> customerOptional = customerRepo.findByVerificationToken(token);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setVerified(true);
            customerRepo.save(customer);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmailAlreadyRegistered(String email) {
        return customerRepo.existsByEmail(email);
    }

    @Override
    public boolean isMobileNumberAlreadyRegistered(String mobileNumber) {
        return customerRepo.existsByMobileNumber(mobileNumber);
    }

    @Override
    public boolean updatePassword(String email, String password) {
        Optional<Customer> customerOptional = customerRepo.findByEmail(email);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            // Encode the new password before savinggive repo
            customer.setPassword(passwordEncoder.encode(password));
            customerRepo.save(customer);
            return true; // Return true if password updated successfully
        } else {
            return false; // Return false if customer not found
        }
    }
}
