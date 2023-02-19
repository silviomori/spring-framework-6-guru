package com.technomori.guru.beerstore.controllers;

import java.net.URI;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technomori.guru.beerstore.domain.Customer;
import com.technomori.guru.beerstore.services.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Collection<Customer> listCustomers() {
        return customerService.listCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<Void> saveNewCustomer(@RequestBody Customer customer) {
        customerService.saveNewCustomer(customer);
        return ResponseEntity.created(URI.create(
                CustomerController.class.getAnnotation(RequestMapping.class).value()[0] + "/" + customer.getId()))
                .build();
    }
}
