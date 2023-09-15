package com.novare.natflax.NatflaxAdvance.Repositories;



import com.novare.natflax.NatflaxAdvance.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer>{


      Optional<User> findByEmail(String email);
}