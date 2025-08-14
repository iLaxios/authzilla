package com.laxios.mfaservice.repository;

import com.laxios.mfaservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findById(UUID id);
    boolean existsById(UUID id);
}
