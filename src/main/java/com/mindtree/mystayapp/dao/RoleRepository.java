package com.mindtree.mystayapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.mystayapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	public Role findByRoleType(String roleType);

	
}
