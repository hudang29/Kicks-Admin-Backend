package com.poly.admin.repository;

import com.poly.admin.dto.BestSellerDTO;
import com.poly.admin.dto.SaleGraphData;
import com.poly.admin.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<BestSellerDTO> findTopBestSellers(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BestSellerDTO> cq = cb.createQuery(BestSellerDTO.class);

        // Root của OrderDetail
        Root<OrderDetail> od = cq.from(OrderDetail.class);

        // JOIN với Product
        Join<OrderDetail, Product> p = od.join("product");

        // Subquery để lấy ProductDetail đúng màu sắc
        Subquery<ProductDetail> subqueryPD = cq.subquery(ProductDetail.class);
        Root<ProductDetail> pd = subqueryPD.from(ProductDetail.class);
        subqueryPD.select(pd)
                .where(cb.equal(pd.get("product"), p));
//
//        // Tạo subquery để JOIN Gallery vì không có @OneToMany trong ProductDetail
        Subquery<String> subqueryG = cq.subquery(String.class);
        Root<Gallery> g = subqueryG.from(Gallery.class);
        subqueryG.select(g.get("image"))
                .where(cb.equal(g.get("productDetail"), pd),
                        cb.equal(g.get("isDefault"), true));


        // Chọn các cột cần lấy
        cq.select(cb.construct(
                BestSellerDTO.class,
                p.get("id"),
                p.get("name"),
                p.get("price"),
                pd.get("color"),
                pd.get("color"),  // Lấy ảnh theo điều kiện
                cb.sum(od.get("quantity"))
        ));

        // GROUP BY các cột cần thiết
        cq.groupBy(p.get("id"), p.get("name"), p.get("price"), pd.get("color"), pd.get("color"));

        // ORDER BY tổng số lượng bán
        cq.orderBy(cb.desc(cb.sum(od.get("quantity"))));

        // Thực thi query với pagination
        TypedQuery<BestSellerDTO> query = entityManager.createQuery(cq);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return query.getResultList();
    }



}
