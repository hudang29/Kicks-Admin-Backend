package com.poly.admin.specification;

import com.poly.admin.model.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSepcification {

    public static Specification<Employee> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }
}
