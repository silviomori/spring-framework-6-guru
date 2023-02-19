package com.technomori.guru.beerstore.services;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.technomori.guru.beerstore.domain.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .fullName("Aurelianus Unimanus")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .fullName("Veronica Pulcher")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .fullName("Maximinus Cicero")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Customer customer4 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .fullName("Castor Calvus")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        customerMap = new HashMap<>();
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
        customerMap.put(customer4.getId(), customer4);

    }

    @Override
    public Collection<Customer> listCustomers() {
        return customerMap.values();
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerMap.get(UUID.fromString(id));
    }

}
