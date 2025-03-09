package com.poly.admin.repository;

import com.poly.admin.model.EmployeePassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRepo extends JpaRepository<EmployeePassword, String> {

    Optional<EmployeePassword> findByEmployee_Email(String email);

    Optional<EmployeePassword> findByEmployee_Id(Integer id);

    @Query("""
            SELECT ep.hashedPassword FROM EmployeePassword ep
            WHERE ep.employee.id = :employeeId
            """)
    String findPasswordByEmployee_Id(@Param("employeeId") Integer id);

    boolean existsByEmployee_Id(Integer id);
}
