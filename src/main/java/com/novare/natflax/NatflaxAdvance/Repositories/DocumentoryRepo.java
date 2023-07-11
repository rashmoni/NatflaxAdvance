package com.novare.natflax.NatflaxAdvance.Repositories;

import com.novare.natflax.NatflaxAdvance.Entity.Documentory;
import com.novare.natflax.NatflaxAdvance.Entity.Movie;
import com.novare.natflax.NatflaxAdvance.Payloads.DocumentoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DocumentoryRepo extends JpaRepository<Documentory, Integer> {

    Optional<Documentory> findByTitle(String title);
}


