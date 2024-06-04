package com.APB.main.Asia_Payments_Bank.response;

import com.APB.main.Asia_Payments_Bank.DTO.CustomerDTO;

public class LoginResponse {
    private String message;
    
    private CustomerDTO customerDTO;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public LoginResponse(String message, CustomerDTO customerDTO) {
        this.message = message;
        this.customerDTO = customerDTO;
    }
}

