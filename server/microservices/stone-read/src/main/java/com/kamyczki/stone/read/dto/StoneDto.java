package com.kamyczki.stone.read.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoneDto {
    private final String id;
    private final Long ownerId;
    private final String name;
    private final String description;
    private final String zipCode;
}
