package com.poly.admin.repository;

import com.poly.admin.dto.OrderDetailDTO;
import com.poly.admin.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {
    //Lấy danh sách chi tiết hóa đơn theo id
    @Query("""
    SELECT new com.poly.admin.dto.OrderDetailDTO(
        od.id,
        pd.id,
        p.name,
        g.image,
        od.size,
        od.color,
        od.quantity,
        od.price)
    FROM OrderDetail od
    JOIN od.orders o
    JOIN od.product p
    JOIN ProductDetail pd ON p.id = pd.product.id AND od.color = pd.color
    JOIN Gallery g ON pd.id = g.productDetail.id AND g.isDefault = true
    WHERE o.id = :id
""")
    List<OrderDetailDTO> findOrderDetailByID(@Param("id") Integer id);

    // Lấy tổng doanh thu theo trạng thái canceled trong tháng
    @Query("""
            SELECT SUM(od.quantity)
            FROM OrderDetail od
            JOIN od.orders o
            WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE)
            AND YEAR(o.orderDate) = YEAR(CURRENT_DATE)
            AND o.orderStatus <> com.poly.admin.enums.OrderStatus.CANCELLED
            """)
    Integer getTotalShoesSale();

}
