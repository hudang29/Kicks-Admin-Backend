package com.poly.admin.repository;

import com.poly.admin.dto.*;
import com.poly.admin.enums.OrderStatus;
import com.poly.admin.model.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo
        extends JpaRepository<Orders, Integer>,
        JpaSpecificationExecutor<Orders>,
        DashboardRepo {
//    @Query("""
//            SELECT new com.poly.admin.dto.SaleGraphData(MONTH(o.orderDate), SUM(o.totalAmount))
//            FROM Orders o WHERE YEAR(o.orderDate) = :year
//            AND o.orderStatus = com.poly.admin.enums.OrderStatus.COMPLETED
//            GROUP BY MONTH(o.orderDate) ORDER BY MONTH(o.orderDate)
//            """)
//    List<SaleGraphData> getSalesDataEachYear(@Param("year") Integer year);

    // Doanh thu theo tháng
    @Query("""
            SELECT new com.poly.admin.dto.SaleGraphData(MONTH(o.orderDate), SUM(o.totalAmount))
            FROM Orders o WHERE YEAR(o.orderDate) = YEAR(CURRENT_DATE)
            AND o.orderStatus = com.poly.admin.enums.OrderStatus.COMPLETED
            GROUP BY MONTH(o.orderDate) ORDER BY MONTH(o.orderDate)
            """)
    List<SaleGraphData> getSalesDataByMonth();

    // Doanh thu theo năm
//    @Query("""
//            SELECT new com.poly.admin.dto.SaleGraphData(YEAR(o.orderDate), SUM(o.totalAmount))
//            FROM Orders o WHERE o.orderStatus = com.poly.admin.enums.OrderStatus.COMPLETED
//            GROUP BY YEAR(o.orderDate) ORDER BY YEAR(o.orderDate)
//            """)
//    List<SaleGraphData> getSalesDataByYear();

    // Lấy tổng doanh thu trong tháng hiện tại
//    @Query("""
//            SELECT SUM(o.totalAmount)
//            FROM Orders o
//            WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE)
//            AND YEAR(o.orderDate) = YEAR(CURRENT_DATE)
//            AND o.orderStatus = com.poly.admin.enums.OrderStatus.COMPLETED
//            """)
//    Double getTotalRevenueThisMonth();

    // Lấy tổng doanh thu trong tháng hiện tại
    @Query("""
            SELECT SUM(o.totalAmount)
            FROM Orders o
            WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE)
            AND YEAR(o.orderDate) = YEAR(CURRENT_DATE)
            AND o.orderStatus <> com.poly.admin.enums.OrderStatus.CANCELLED
            """)
    Double getTotalRevenueByOrdersThisMonth();

    // Lấy tổng doanh thu theo từng trạng thái trong tháng
    @Query("""
            SELECT new com.poly.admin.dto.SalesSummaryDTO(o.orderStatus , SUM(o.totalAmount))
            FROM Orders o
            WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE)
            AND YEAR(o.orderDate) = YEAR(CURRENT_DATE)
            GROUP BY o.orderStatus
            """)
    List<SalesSummaryDTO> getTotalRevenueByStatus();

    // Lấy tổng doanh thu theo trạng thái canceled trong tháng
    @Query("""
            SELECT SUM(o.totalAmount)
            FROM Orders o
            WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE)
            AND YEAR(o.orderDate) = YEAR(CURRENT_DATE)
            AND o.orderStatus = com.poly.admin.enums.OrderStatus.CANCELLED
            GROUP BY o.orderStatus
            """)
    Double getTotalRevenueByStatusCanceled();

    @Query("""
            SELECT new com.poly.admin.dto.OrderDTO(
            o.id, o.orderDate, o.payment.paymentMethod, c.name, o.orderStatus, o.totalAmount)
            FROM Orders o
            JOIN o.customer c
            ORDER BY o.orderDate DESC
            """)
    List<OrderDTO> findLatestOrders(Pageable pageable);

    @Query("""
                SELECT new com.poly.admin.dto.BestSellerDTO(
                    p.id, p.name, p.price, pd.color, g.image, SUM(od.quantity))
                FROM OrderDetail od
                JOIN od.orders o
                JOIN od.product p
                JOIN ProductDetail pd ON p.id = pd.product.id AND od.color = pd.color
                LEFT JOIN Gallery g ON pd.id = g.productDetail.id AND g.isDefault = true
                WHERE
                    FUNCTION('MONTH', o.orderDate) = FUNCTION('MONTH', CURRENT_DATE)
                    AND FUNCTION('YEAR', o.orderDate) = FUNCTION('YEAR', CURRENT_DATE)
                GROUP BY p.id, p.name, p.price, pd.color, g.image
                ORDER BY SUM(od.quantity) DESC
            """)
    List<BestSellerDTO> findTopBestSellers(Pageable pageable);

    @Query("""
                SELECT new com.poly.admin.dto.LowStockProductDTO(
                    pd.id, p.name, p.price, pd.color, ps.size, g.image, SUM(ps.stock))
                FROM ProductSize ps
                JOIN ps.productDetail pd
                JOIN pd.product p
                LEFT JOIN Gallery g ON pd.id = g.productDetail.id AND g.isDefault = true
                WHERE ps.stock <= :threshold
                GROUP BY pd.id, p.name, p.price, pd.color, ps.size, g.image
                ORDER BY SUM(ps.stock) ASC
            """)
    List<LowStockProductDTO> findLowStockProducts(@Param("threshold") Integer threshold);

    //Lấy danh sách hóa đơn (ưu tiên chưa xưr lý)
//    @Query("""
//                SELECT new com.poly.admin.dto.OrderDTO(
//                    o.id,
//                    cp.id,
//                    c.name,
//                    o.shippingAddress,
//                    o.orderDate,
//                    o.orderStatus,
//                    o.totalAmount
//                )
//                FROM OrderDetail od
//                JOIN od.order o
//                JOIN o.customer c
//                LEFT JOIN o.coupon cp
//                ORDER BY
//                    CASE
//                        WHEN o.orderStatus = 'Chờ xử lý' THEN 1
//                        ELSE 2
//                    END,
//                    o.orderDate DESC
//            """)
//    Page<OrderDTO> findAllOrders(Pageable pageable);

    //@Override
    //Page<Orders> findAll(Pageable pageable);

    //Lấy danh sách hóa đơn (ưu tiên chưa xưr lý)
//    @Query("""
//                SELECT o
//                FROM Orders o
//                WHEN o.orderStatus = :status
//                o.orderDate DESC
//            """)
    List<Orders> findAllByOrderStatusOrderByOrderDateAsc(OrderStatus orderStatus);


}
