package com.ll.topcastingbe.domain.order.repository.order_product;

import com.ll.topcastingbe.domain.order.entity.OrderProduct;
import com.ll.topcastingbe.domain.order.entity.Orders;
import jakarta.persistence.LockModeType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findAllByOrder(final Orders order);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT oi FROM OrderProduct oi WHERE oi.order = :order")
    List<OrderProduct> findAllByOrderWithPessimisticWriteLock(final Orders order);

    void removeAllByOrder(final Orders order);

    List<OrderProduct> findByOrder(Orders orders);


}
