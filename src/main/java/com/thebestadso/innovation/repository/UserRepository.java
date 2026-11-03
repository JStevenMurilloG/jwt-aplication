package com.thebestadso.innovation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thebestadso.innovation.entity.Users;

@Repository 
public interface UserRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
}
