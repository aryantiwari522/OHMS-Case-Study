package com.ohms.repository;

import java.util.Optional;

import com.ohms.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ohms.models.ERole;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
