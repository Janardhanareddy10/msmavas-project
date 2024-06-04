package com.APB.main.Asia_Payments_Bank.service;

import java.util.List;

import com.APB.main.Asia_Payments_Bank.DTO.CustomerDTO;

import com.APB.main.Asia_Payments_Bank.response.LoginResponse;



public interface CustomerService {
	
	boolean updatePassword(String email, String password) throws Exception;
	boolean verifyEmail(String token);
    String addCustomer123(CustomerDTO customerDTO);
boolean isEmailAlreadyRegistered(String email);
//   
//	String addCustomer(CustomerDTO customerDTO);
boolean isMobileNumberAlreadyRegistered(String mobileNumber);
////	String addCustomer1(CustomerDTO customerDTO);
////    String verifyEmail(String email, String verificationToken);
////    String createPassword(String email, String password);
}
