package com.app.entry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entry.model.User;
/**
 * @author lk00564920
 * UserRepository
 * Helps to manage all crud operation related to user entity
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserName(String userName);
}