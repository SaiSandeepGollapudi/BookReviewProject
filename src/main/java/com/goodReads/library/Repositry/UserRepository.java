package com.goodReads.library.Repositry;

import com.goodReads.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Override
    Optional<User> findById(Integer integer);

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);


}
