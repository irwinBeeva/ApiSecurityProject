package com.beeva.main.data.dao.h2Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beeva.main.model.User;

@Repository
public interface CustomUserH2 extends CrudRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User findOneByUsername(@Param("username") String username);
}
