package com.learn.keycloakprofessorapp.repository;

import com.learn.keycloakprofessorapp.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
