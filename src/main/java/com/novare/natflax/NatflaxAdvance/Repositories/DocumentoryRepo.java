package com.novare.natflax.NatflaxAdvance.Repositories;

import com.novare.natflax.NatflaxAdvance.Entity.Documentary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DocumentoryRepo extends JpaRepository<Documentary, Integer> {

    Optional<Documentary> findByTitle(String title);
}


