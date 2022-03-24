package com.example.user.Utility;

import java.util.UUID;

public class EmpIdGeneratorUtility {

	public String getEmpId() {
		String empId = UUID.randomUUID().toString();
		return empId;
	}
	
}
