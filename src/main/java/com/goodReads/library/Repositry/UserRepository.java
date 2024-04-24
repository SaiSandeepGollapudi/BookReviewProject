package com.goodReads.library.Repositry;

import com.goodReads.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
