package com.ll.topcastingbe.domain.order.service.order_product;

import com.ll.topcastingbe.domain.cart.entity.CartOption;
import com.ll.topcastingbe.domain.cart.repository.CartOptionRepository;
import com.ll.topcastingbe.domain.member.entity.Member;
import com.ll.topcastingbe.domain.option.entity.Option;
import com.ll.topcastingbe.domain.option.repository.OptionRepository;
import com.ll.topcastingbe.domain.order.dto.order_item.request.AddOrderProductRequest;
import com.ll.topcastingbe.domain.order.dto.order_item.request.ModifyOrderProductRequest;
import com.ll.topcastingbe.domain.order.dto.order_item.response.FindOrderProductResponse;
import com.ll.topcastingbe.domain.order.entity.OrderProduct;
import com.ll.topcastingbe.domain.order.entity.Orders;
import com.ll.topcastingbe.domain.order.exception.EntityNotFoundException;
import com.ll.topcastingbe.domain.order.exception.ErrorMessage;
import com.ll.topcastingbe.domain.order.repository.order.OrderRepository;
import com.ll.topcastingbe.domain.order.repository.order_product.OrderProductRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderProductServiceImpl implements OrderProductService {
    private final OptionRepository optionRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderRepository orderRepository;
    private final CartOptionRepository cartOptionRepository;

    @Override
    @Transactional
    //todo save가 두 번 호출되므로 정상적인지는 잘 모르겠음
    public void addOrderProduct(Orders order, AddOrderProductRequest addOrderProductRequest) {
        final CartOption cartOption = cartOptionRepository.findById(addOrderProductRequest.cartProductId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND));

        final OrderProduct orderProduct = OrderProduct.builder()
                .order(order)
                .option(cartOption.getOption())
                .productQuantity(addOrderProductRequest.productQuantity())
                .totalPrice(getTotalPrice(cartOption.getOption(), addOrderProductRequest)).build();

        orderProductRepository.save(orderProduct);
    }

    private Long getTotalPrice(final Option option, final AddOrderProductRequest addOrderProductRequest) {
        //todo 코드 더러움 수정 필요
        final Long totalPrice =
                option.getProduct().getProductPrice().longValue() *
                        addOrderProductRequest.productQuantity();

        return totalPrice;
    }

    @Override
    public List<FindOrderProductResponse> findAllByOrderId(final UUID orderId, final Member member) {
        final Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND));

        List<OrderProduct> orderProducts = orderProductRepository.findAllByOrder(order);
        List<FindOrderProductResponse> orderProductResponses = FindOrderProductResponse.ofList(orderProducts);

        return orderProductResponses;
    }

    @Override
    public List<FindOrderProductResponse> findAllByOrderIdForAdmin(final UUID orderId) {
        final Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND));

        List<OrderProduct> orderProducts = orderProductRepository.findAllByOrder(order);
        List<FindOrderProductResponse> orderProductResponses = FindOrderProductResponse.ofList(orderProducts);

        return orderProductResponses;
    }

    public List<OrderProduct> findOrderProducts(final Orders order) {
        final List<OrderProduct> orderProducts = orderProductRepository.findAllByOrder(order);
        return orderProducts;
    }

    public List<OrderProduct> findOrderProductsWithPessimisticWriteLock(final Orders order) {
        final List<OrderProduct> orderProducts = orderProductRepository.findAllByOrderWithPessimisticWriteLock(order);
        return orderProducts;
    }

    @Override
    @Transactional
    public void updateOrderItem(Long orderProductId, ModifyOrderProductRequest modifyOrderProductRequest, Member member) {

    }

    @Override
    @Transactional
    public void removeAllByOrder(final Orders order) {
        orderProductRepository.removeAllByOrder(order);
    }
}
