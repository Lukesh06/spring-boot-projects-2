package com.example.user.Utility;

import java.util.UUID;

public class EmpIdGeneratorUtility {

	public String getUserReferenceNumber() {
		String userReferenceNumber = UUID.randomUUID().toString();
		return userReferenceNumber;
	}
	
}
