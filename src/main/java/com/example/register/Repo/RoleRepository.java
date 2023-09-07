package com.example.register.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.register.Entity.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
	 

}
