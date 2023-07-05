package com.learnwithankit.springbootrestfulwebservicesankit.repository;

import com.learnwithankit.springbootrestfulwebservicesankit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository -> not needed as SimpleJpaRepository (implements JpaRepository) has repository annotation also methods have transaction annotation
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
