package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController(value = "/login")
public class LoginController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Customer> add(@RequestParam String username, String password) {

		Customer cust = customerService.findAccountByPrimaryKey(username);
		if (cust.getPassword() == password)
			return new ResponseEntity<Customer>(cust, HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
	}

}
