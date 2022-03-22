package com.example.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/mapping")
public class MappingTestController {

	
	@GetMapping(value = { "/{id}/{name}", "/", "/{id}", "/{name}" })
	public String getUser(@PathVariable(required = false) String id, @PathVariable(required = false) String name) {
		return "Id is :" + id + " and name is:" + name;
	}
	
	@PutMapping
	public String updateUser() {
		return "This is Put Mapping for Update";
	}

	@DeleteMapping
	public String deleteUser() {
		return "This is delete Mapping";
	}

}
