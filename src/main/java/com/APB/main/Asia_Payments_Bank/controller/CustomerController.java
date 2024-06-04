package com.APB.main.Asia_Payments_Bank.controller;


import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.APB.main.Asia_Payments_Bank.DTO.CustomerDTO;

import com.APB.main.Asia_Payments_Bank.response.LoginResponse;
import com.APB.main.Asia_Payments_Bank.service.CustomerService;
import com.APB.main.Asia_Payments_Bank.service.PasswordService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer( @RequestBody CustomerDTO customerDTO,String jsonPayload) {
    	if (customerService.isEmailAlreadyRegistered(customerDTO.getEmail())) {
    	    // Email is already registered, return a bad request response
    	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already registered");
    	}
    	if (customerService.isMobileNumberAlreadyRegistered(customerDTO.getMobileNumber())) {
    	    // Mobile number is already registered, return a bad request response
    	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile number is already registered");
    	}
        String id = customerService.addCustomer123(customerDTO);
        return ResponseEntity.ok(id);
    }
    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        // Verify customer's email using the verification token
        boolean isVerified = customerService.verifyEmail(token);
        if (isVerified) {
            return ResponseEntity.ok("Email verified successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification token");
        }
    }
    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestParam String email, @RequestParam String password) {
        try {
            boolean passwordUpdated = customerService.updatePassword(email, password);
            if (passwordUpdated) {
                return ResponseEntity.ok("Password updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update password: " + e.getMessage());
        }
    }
    

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> loginCustomer(@RequestBody LoginDTO loginDTO) {
//        LoginResponse loginResponse = customerService.loginCustomer(loginDTO);
//        return ResponseEntity.ok(loginResponse);
//    }

//    @GetMapping(path = "/getAll")
//    public ResponseEntity<List<CustomerDTO>> findAllCustomers() {
//        List<CustomerDTO> customers = customerService.findAllCustomers();
//        return ResponseEntity.ok(customers);
//    }
//
//    @GetMapping(path = "/{customerId}")
//    public ResponseEntity<?> findCustomerById(@PathVariable Long customerId) {
//        CustomerDTO customerDTO = customerService.findCustomerById(customerId);
//
//        if (customerDTO != null) {
//            return ResponseEntity.ok(customerDTO);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found for id: " + customerId);
//        }
//    }
//
//    @DeleteMapping("/deleteAll")
//    public ResponseEntity<String> deleteAllCustomers() {
//        customerService.deleteAllCustomers();
//        return ResponseEntity.ok("All customers deleted successfully");
//    }
//
//    @PutMapping("/password")
//    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
//        try {
//            if (!customerService.isEmailAlreadyRegistered(updatePasswordDTO.getEmail())) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
//            }
//
//            // Update the password
//            customerService.updatePassword(updatePasswordDTO.getEmail(), updatePasswordDTO.getPassword());
//            return ResponseEntity.ok("Password updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update password");
//        }
//    }
//
//    @PutMapping("/{customerId}")
//    public ResponseEntity<?> updateCustomerDetails(@PathVariable Long customerId, @RequestBody CustomerDTO updatedDetails) {
//        try {
//            customerService.updateCustomerDetails(customerId, updatedDetails);
//            return ResponseEntity.ok("Customer details updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update customer details");
//        }
//    }s
}

