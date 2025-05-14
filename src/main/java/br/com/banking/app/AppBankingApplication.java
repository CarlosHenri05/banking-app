package br.com.banking.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.banking.app", "br.com.banking.app.transaction"})
public class AppBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppBankingApplication.class, args);
	}

}
