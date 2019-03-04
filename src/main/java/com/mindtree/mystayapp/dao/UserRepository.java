package com.mindtree.mystayapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.mystayapp.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUserName(String userName);
}
