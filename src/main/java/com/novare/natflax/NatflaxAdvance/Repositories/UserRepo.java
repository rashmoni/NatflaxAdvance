package com.novare.natflax.NatflaxAdvance.Repositories;



import com.novare.natflax.NatflaxAdvance.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
