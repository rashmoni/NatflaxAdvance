package com.novare.natflax.NatflaxAdvance.Repositories;

import com.novare.natflax.NatflaxAdvance.Entity.Documentory;
import com.novare.natflax.NatflaxAdvance.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DocumentoryRepo extends JpaRepository<Documentory, Integer> {

    }

