package com.poly.admin.specification;

import com.poly.admin.enums.OrderStatus;
import com.poly.admin.model.Orders;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public class OrderSpecification {

    public static Specification<Orders> hasStatus(OrderStatus status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) return null;
            try {
                return criteriaBuilder.equal(root.get("orderStatus"), status);
            } catch (IllegalArgumentException e) {
                return null;
            }
        };
    }

    public static Specification<Orders> createdBetween(Instant startDate, Instant endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null || endDate == null) return null;
            return criteriaBuilder.between(root.get("orderDate"), startDate, endDate);
        };
    }

    public static Specification<Orders> hasMonthAndYear(Integer year, Integer month) {
        return (root, query, criteriaBuilder) -> {
            if (year == null) return null;

            Predicate yearPredicate = criteriaBuilder.equal(
                    criteriaBuilder.function("YEAR", Integer.class, root.get("orderDate")), year);

            if (month == null) {
                return yearPredicate;
            }

            if (month < 1 || month > 12) return null;

            Predicate monthPredicate = criteriaBuilder.equal(
                    criteriaBuilder.function("MONTH", Integer.class, root.get("orderDate")), month);

            return criteriaBuilder.and(yearPredicate, monthPredicate);
        };
    }

}
