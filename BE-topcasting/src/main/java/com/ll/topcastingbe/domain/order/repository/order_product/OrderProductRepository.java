package com.ll.topcastingbe.domain.order.repository.order_product;

import com.ll.topcastingbe.domain.order.entity.OrderOption;
import com.ll.topcastingbe.domain.order.entity.Orders;
import jakarta.persistence.LockModeType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface OrderProductRepository extends JpaRepository<OrderOption, Long> {
    List<OrderOption> findAllByOrder(final Orders order);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT oi FROM OrderOption oi WHERE oi.order = :order")
    List<OrderOption> findAllByOrderWithPessimisticWriteLock(final Orders order);

    void removeAllByOrder(final Orders order);

    List<OrderOption> findByOrder(Orders orders);


}
