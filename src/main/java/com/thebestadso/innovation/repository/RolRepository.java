package com.thebestadso.innovation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thebestadso.innovation.entity.Roles;

@Repository 
public interface RolRepository extends JpaRepository<Roles, Long>{
    
}
