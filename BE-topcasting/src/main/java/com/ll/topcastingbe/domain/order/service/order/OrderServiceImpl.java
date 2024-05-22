package com.ll.topcastingbe.domain.order.service.order;

import static com.ll.topcastingbe.domain.order.entity.OrderStatus.ORDER_EXCHANGE_REQUESTED;
import static com.ll.topcastingbe.domain.order.entity.OrderStatus.ORDER_REFUND_REQUESTED;

import com.ll.topcastingbe.domain.member.entity.Member;
import com.ll.topcastingbe.domain.order.dto.order.request.AddOrderRequest;
import com.ll.topcastingbe.domain.order.dto.order.request.ModifyOrderRequest;
import com.ll.topcastingbe.domain.order.dto.order.request.RequestCancelOrderRequest;
import com.ll.topcastingbe.domain.order.dto.order.response.AddOrderResponse;
import com.ll.topcastingbe.domain.order.dto.order.response.FindOrderResponse;
import com.ll.topcastingbe.domain.order.dto.order_item.response.FindOrderProductResponseDto;
import com.ll.topcastingbe.domain.order.entity.OrderOption;
import com.ll.topcastingbe.domain.order.entity.OrderStatus;
import com.ll.topcastingbe.domain.order.entity.Orders;
import com.ll.topcastingbe.domain.order.repository.order.OrderRepository;
import com.ll.topcastingbe.domain.order.service.order_product.OrderProductService;
import com.ll.topcastingbe.global.exception.order.OrderErrorMessage;
import com.ll.topcastingbe.global.exception.order.OrderNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
@EnableAsync
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductService orderProductService;

    @Override
    @Transactional
    public AddOrderResponse addOrder(final AddOrderRequest addOrderRequest, final Member member) {
        final Orders order = addOrderRequest.toOrder(member);
        orderRepository.save(order);

        addOrderProduct(order, addOrderRequest);
        final AddOrderResponse addOrderResponse = AddOrderResponse.of(order);
        return addOrderResponse;
    }

    //todo 나중에 리팩토링 해야함
    @Override
    public FindOrderResponse findOrder(final UUID orderId, final Member member) {
        final Orders order = findByOrderId(orderId);
        order.checkAuthorizedMember(member);

        List<FindOrderProductResponseDto> findOrderProductRespons = orderProductService.findAllByOrderId(orderId,
                member);
        final FindOrderResponse findOrderResponse = FindOrderResponse.of(order, findOrderProductRespons);

        return findOrderResponse;
    }

    @Override
    public List<FindOrderResponse> findOrderList(final Member member) {
        final List<Orders> orders = orderRepository.findAllByMember(member);
        List<FindOrderResponse> findOrderResponses = addOrderResponse(orders);
        return findOrderResponses;
    }

    @Override
    @Transactional
    public void modifyOrder(final UUID orderId, final ModifyOrderRequest modifyOrderRequest, final Member member) {
        final Orders order = findByOrderId(orderId);

        order.checkAuthorizedMember(member);

        order.modifyOrder(modifyOrderRequest);
    }


    //todo 주문 삭제 기능 보류
    @Override
    @Transactional
    public void removeOrder(final UUID orderId, final Member member) {
        final Orders order = findByOrderId(orderId);
        order.checkAuthorizedMember(member);
        orderProductService.removeAllByOrder(order);
        orderRepository.delete(order);
    }

    @Override
    public Orders findByOrderId(final UUID orderId) {
        final Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException());
        return order;
    }

    @Override
    public void checkAuthorizedMemberList(List<Orders> orders, Member member) {
        orders.stream()
                .forEach(order -> order.checkAuthorizedMember(member));
    }

    @Override
    @Transactional
    public void requestCancelOrder(final UUID orderId,
                                   final RequestCancelOrderRequest requestCancelOrderRequest,
                                   final Member member) {
        final OrderStatus orderStatus = OrderStatus.checkOrderStatus(requestCancelOrderRequest.orderStatus());
        if (orderStatus == ORDER_REFUND_REQUESTED ||
                orderStatus == ORDER_EXCHANGE_REQUESTED) {
            final Orders order = findByOrderId(orderId);
            order.checkAuthorizedMember(member);
            order.modifyOrderStatus(orderStatus);
        }
    }

    public List<FindOrderResponse> addOrderResponse(final List<Orders> orders) {
        List<FindOrderResponse> findOrderResponses = new ArrayList<>();

        for (Orders order : orders) {
            final List<FindOrderProductResponseDto> findOrderProductRespons =
                    orderProductService.findAllByOrderIdForAdmin(order.getId());
            final FindOrderResponse findOrderResponse = FindOrderResponse.of(order, findOrderProductRespons);
            findOrderResponses.add(findOrderResponse);
        }
        return findOrderResponses;
    }

    public Long getTotalProductPrice(final Orders order) {
        final List<OrderOption> orderOptions = orderProductService.findOrderProducts(order);
        Long totalProductPrice = orderOptions.stream()
                .mapToLong(OrderOption::getTotalPrice)
                .sum();
        return totalProductPrice;
    }

    @Transactional
    @Async("threadPoolTaskExecutor")
    @Retryable
    public CompletableFuture<String> deductStockForOrder(final Orders order) {

        List<OrderOption> orderOptions = orderProductService.findOrderProductsWithPessimisticWriteLock(order);
        for (OrderOption orderOption : orderOptions) {
            long newStock = orderOption.getOption().getStock() - orderOption.getProductQuantity();
            if (newStock < 0) {
                log.info("{}", "error");
                //Todo: 예외 수정
                throw new IllegalArgumentException(OrderErrorMessage.INVALID_INPUT_VALUE.getMessage());
            }
            orderOption.getOption().deductionStock(orderOption.getProductQuantity());
        }
        return CompletableFuture.completedFuture(null);
    }

    private void checkTotalProductPrice(final Orders order) {
        Long totalProductPrice = getTotalProductPrice(order);
        if (!Objects.equals(totalProductPrice, order.getTotalProductPrice())) {
            //Todo: 예외 수정
            throw new IllegalArgumentException(OrderErrorMessage.INVALID_INPUT_VALUE.getMessage());
        }
    }

    private void addOrderProduct(final Orders order, final AddOrderRequest addOrderRequest) {
        addOrderRequest.addOrderProductRequest()
                .forEach(addOrderProductRequest -> {
                    orderProductService.addOrderProduct(order, addOrderProductRequest);
                });
    }
}
