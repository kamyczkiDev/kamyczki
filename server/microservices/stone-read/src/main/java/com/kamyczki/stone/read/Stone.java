package com.kamyczki.stone.read;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "stone")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
class Stone{

   @Id
   private String id;

    private Long ownerUserId;
    private String name;
    private String description;
    private String zipCode;
}
