package com.kamyczki.stone.read;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Entity(name = "stones")
@Builder
@AllArgsConstructor
public class Stone {

   @Id
   private String id;

    private Long ownerId;
    private String name;
    private String description;
    private String zipCode;
}
