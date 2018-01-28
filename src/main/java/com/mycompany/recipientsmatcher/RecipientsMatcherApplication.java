package com.mycompany.recipientsmatcher;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mycompany.recipientsmatcher.service.RecipientsService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@SpringBootApplication
public class RecipientsMatcherApplication implements CommandLineRunner {
	
	@Autowired
	private RecipientsService recipientsService;


	@Override
	public void run(String... args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

                recipientsService.init();
                recipientsService.proc();
                recipientsService.writeResult();
                System.out.println("Successfully processed!\n Please check output.csv");
                        
	
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RecipientsMatcherApplication.class, args);
	}

}
