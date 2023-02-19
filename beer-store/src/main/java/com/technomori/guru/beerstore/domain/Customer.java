package com.technomori.guru.beerstore.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Customer {
    private UUID id;
    private Integer version;
    private String fullName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
