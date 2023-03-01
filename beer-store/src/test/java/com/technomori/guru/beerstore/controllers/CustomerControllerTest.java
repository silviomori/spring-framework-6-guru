package com.technomori.guru.beerstore.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technomori.guru.beerstore.domain.Customer;
import com.technomori.guru.beerstore.services.CustomerService;
import com.technomori.guru.beerstore.services.CustomerServiceImpl;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

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

    @Test
    void testSaveNewCustomer() throws JsonProcessingException, Exception {
        Customer newCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .fullName("Apollo Longus")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        given(customerService.saveNewCustomer(any(Customer.class))).willReturn(newCustomer);

        mockMvc.perform(
                post("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCustomer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testCustomerUpdate() throws Exception {
        Customer customer = (Customer) new CustomerServiceImpl().listCustomers().toArray()[0];

        mockMvc.perform(
                put("/api/v1/customers/{}", customer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());

        verify(customerService, times(1)).updateCustomer(any(String.class), any(Customer.class));
    }

}
