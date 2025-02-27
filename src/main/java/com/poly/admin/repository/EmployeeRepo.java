package com.poly.admin.repository;

import com.poly.admin.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    @Modifying
    @Query("UPDATE Employee e SET e.status = :status WHERE e.id = :id")
    void updateStatusById(@Param("id") Integer id, @Param("status") Boolean status);
}
