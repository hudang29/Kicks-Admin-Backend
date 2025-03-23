package com.poly.admin.repository;

import com.poly.admin.dto.SaleGraphData;
import com.poly.admin.model.Orders;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

public class DashboardRepoImpl implements DashboardRepo {

    @PersistenceContext
    private EntityManager entityManager; //

    @Override
    public BigDecimal getTotalAmountByStatus(Specification<Orders> spec) {

        // ✅ Sử dụng Criteria API để tính tổng `totalAmount`
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> query = cb.createQuery(BigDecimal.class);
        Root<Orders> root = query.from(Orders.class);

        Expression<BigDecimal> totalSum = cb.sum(root.get("totalAmount"));
        query.select(totalSum);
        query.where(spec.toPredicate(root, query, cb));

        // Xử lý null (tránh lỗi khi không có dữ liệu)
        BigDecimal result = entityManager.createQuery(query).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }

    @Override
    public List<SaleGraphData> getSalesDataEachYear(Specification<Orders> spec) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SaleGraphData> query = cb.createQuery(SaleGraphData.class);
        Root<Orders> root = query.from(Orders.class);

        // Lấy tháng từ orderDate
        Expression<Integer> monthExpr = cb.function("MONTH", Integer.class, root.get("orderDate"));
        // Tính tổng totalAmount
        Expression<BigDecimal> totalSum = cb.sum(root.get("totalAmount"));


        // Nhóm theo tháng
        query.select(cb.construct(SaleGraphData.class, monthExpr, totalSum))
                .where(spec.toPredicate(root, query, cb))
                .groupBy(monthExpr)
                .orderBy(cb.asc(monthExpr));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<SaleGraphData> getSalesDataByYear(Specification<Orders> spec) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SaleGraphData> query = cb.createQuery(SaleGraphData.class);
        Root<Orders> root = query.from(Orders.class);

        // Lấy tháng từ orderDate
        Expression<Integer> yearExpr = cb.function("YEAR", Integer.class, root.get("orderDate"));
        // Tính tổng totalAmount
        Expression<BigDecimal> totalSum = cb.sum(root.get("totalAmount"));


        // Nhóm theo tháng
        query.select(cb.construct(SaleGraphData.class, yearExpr, totalSum))
                .where(spec.toPredicate(root, query, cb))
                .groupBy(yearExpr)
                .orderBy(cb.asc(yearExpr));

        return entityManager.createQuery(query).getResultList();
    }

}
