package com.technomori.guru.beerstore.services;

import java.util.Collection;

import com.technomori.guru.beerstore.domain.Customer;

public interface CustomerService {

    Collection<Customer> listCustomers();

    Customer getCustomerById(String id);

    Customer saveNewCustomer(Customer customer);

    Customer updateCustomer(String id, Customer customer);

}
