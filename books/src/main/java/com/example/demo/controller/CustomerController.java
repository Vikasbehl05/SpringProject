package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Customer> add(@RequestBody Customer customer) {

		Customer cust = customerService.saveCustomer(customer);
		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	@Transactional(propagation = Propagation.NEVER)
	public List<Customer> readall() {

		List<Customer> lst = new ArrayList<Customer>();
		lst = (List<Customer>) customerService.readallRecords();
		return lst;
	}

	@RequestMapping(value = "/readid", method = RequestMethod.POST)
	public Customer readById(@RequestParam(value = "id", required = true) String id) {

		Optional<Customer> wapper = Optional.of(customerService.findAccountByPrimaryKey(id));
		Customer customer = wapper.get();

		return customer;
	}

}
