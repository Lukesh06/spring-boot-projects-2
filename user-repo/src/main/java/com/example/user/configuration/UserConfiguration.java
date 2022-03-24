package com.example.user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.user.Utility.EmpIdGeneratorUtility;

@Configuration
public class UserConfiguration {

	@Bean
	public EmpIdGeneratorUtility getEmpIgGeneratorUtility() {
		return new EmpIdGeneratorUtility();
	}
	
}
