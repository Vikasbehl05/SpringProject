package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public Customer saveCustomer(Customer c1) {

		Customer temp;
		temp = customerRepository.save(c1);
		return temp;
	}

	public List<Customer> readallRecords() {
		List<Customer> listCustomer = (List<Customer>) customerRepository.findAll();

		return listCustomer;
	}

	public Customer findAccountByPrimaryKey(@PathVariable String acid) {

		Optional<Customer> acc = customerRepository.findById(acid);
		Customer customer = acc.get();
		return customer;

	}

	public boolean deleteCustomer(@PathVariable String id) {
		boolean exists = customerRepository.existsById(id);
		if (exists)
			customerRepository.deleteById(id);
		return exists;
	}

	public Customer updateCustomer(Customer c1) {
		Optional<Customer> temp = customerRepository.findById(c1.getUserId());
		Customer customer = temp.get();
		customer = c1;
		customerRepository.save(customer);
		return customer;

	}
	
	

}
