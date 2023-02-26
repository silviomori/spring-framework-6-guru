package com.technomori.guru.beerstore.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.technomori.guru.beerstore.domain.Customer;
import com.technomori.guru.beerstore.services.CustomerService;
import com.technomori.guru.beerstore.services.CustomerServiceImpl;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Test
    void testGetCustomerById() throws Exception {
        Customer customerTest = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .fullName("Aurelianus Unimanus")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        given(customerService.getCustomerById(any(String.class))).willReturn(customerTest);

        mockMvc.perform(get("/api/v1/customers/" + customerTest.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(customerTest.getId().toString())))
                .andExpect(jsonPath("$.fullName", is(customerTest.getFullName().toString())));
    }

    @Test
    void testListCustomers() throws Exception {
        Collection<Customer> customers = new CustomerServiceImpl().listCustomers();
        given(customerService.listCustomers()).willReturn(customers);

        mockMvc.perform(get("/api/v1/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(customers.size())));
    }

}
