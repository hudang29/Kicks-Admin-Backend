package com.poly.admin.repository;

import com.poly.admin.model.EmployeePassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRepo extends JpaRepository<EmployeePassword, String> {

    Optional<EmployeePassword> findByEmployee_Email(String email);
}
