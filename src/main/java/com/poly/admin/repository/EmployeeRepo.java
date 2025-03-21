package com.poly.admin.repository;

import com.poly.admin.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

    @Modifying
    @Query("UPDATE Employee e SET e.status = :status WHERE e.id = :id")
    void updateStatusById(@Param("id") Integer id, @Param("status") Boolean status);

    boolean existsByEmail(String email);

    @Query("""
            SELECT e.role FROM Employee e
            where e.email = :email
            """)
    String findRoleByEmail(@Param("email") String email);

    Optional<Employee> findByEmail(String email);
}
