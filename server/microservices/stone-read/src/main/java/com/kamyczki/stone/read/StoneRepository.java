package com.kamyczki.stone.read;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoneRepository extends JpaRepository<Stone,String> {
}
