package com.APB.main.Asia_Payments_Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AsiaPaymentsBankApplication {
	private static boolean accountCreationInProgress = false;

    public static synchronized boolean isAccountCreationInProgress() {
        return accountCreationInProgress;
    }

    public static synchronized void setAccountCreationInProgress(boolean value) {
        accountCreationInProgress = value;
    }
	public static void main(String[] args) {
		SpringApplication.run(AsiaPaymentsBankApplication.class, args);
	}
	

}
