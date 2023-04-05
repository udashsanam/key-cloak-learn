package com.learn.keycloakstudentapp.repository;

import com.learn.keycloakstudentapp.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
