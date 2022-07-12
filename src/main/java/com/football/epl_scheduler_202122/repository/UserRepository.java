package com.football.epl_scheduler_202122.repository;

import com.football.epl_scheduler_202122.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
