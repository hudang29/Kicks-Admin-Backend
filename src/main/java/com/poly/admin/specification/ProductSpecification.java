package com.poly.admin.specification;

import com.poly.admin.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<Product> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return null;
            }
            if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            }
            if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    public static Specification<Product> hasBrand(String brand) {
        return (root, query, criteriaBuilder) -> {
            if (brand == null || brand.isEmpty()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("brand"), brand);
        };
    }

//    public static Specification<Product> getOldOrNew(String oldOrNew) {
//        return (root, query, criteriaBuilder) -> {
//            if (oldOrNew == null || oldOrNew.isEmpty()) {
//                return criteriaBuilder.conjunction();
//            }
//
//            if ("old".equalsIgnoreCase(oldOrNew)) {
//                query.orderBy(criteriaBuilder.asc(root.get("createAt")));
//            } else if ("new".equalsIgnoreCase(oldOrNew)) {
//                query.orderBy(criteriaBuilder.desc(root.get("createAt")));
//            }
//
//            return criteriaBuilder.conjunction(); // Không có điều kiện WHERE, chỉ sắp xếp
//        };
//    }
}
