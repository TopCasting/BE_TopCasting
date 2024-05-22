package com.ll.topcastingbe.domain.order.service.order_product;

import com.ll.topcastingbe.domain.cart.entity.CartOption;
import com.ll.topcastingbe.domain.cart.repository.CartOptionRepository;
import com.ll.topcastingbe.domain.image.repository.ImageRepository;
import com.ll.topcastingbe.domain.member.entity.Member;
import com.ll.topcastingbe.domain.option.entity.Option;
import com.ll.topcastingbe.domain.option.repository.OptionRepository;
import com.ll.topcastingbe.domain.order.dto.order_item.request.AddOrderProductRequest;
import com.ll.topcastingbe.domain.order.dto.order_item.request.ModifyOrderProductRequest;
import com.ll.topcastingbe.domain.order.dto.order_item.response.FindOrderProductResponseDto;
import com.ll.topcastingbe.domain.order.entity.OrderOption;
import com.ll.topcastingbe.domain.order.entity.Orders;
import com.ll.topcastingbe.domain.order.repository.order.OrderRepository;
import com.ll.topcastingbe.domain.order.repository.order_product.OrderProductRepository;
import com.ll.topcastingbe.global.exception.order.OrderNotFoundException;
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
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    //todo save가 두 번 호출되므로 정상적인지는 잘 모르겠음
    public void addOrderProduct(Orders order, AddOrderProductRequest addOrderProductRequest) {
        final CartOption cartOption = cartOptionRepository.findById(addOrderProductRequest.cartProductId())
                .orElseThrow(() -> new OrderNotFoundException());

        final OrderOption orderOption = OrderOption.builder()
                .order(order)
                .option(cartOption.getOption())
                .productQuantity(addOrderProductRequest.productQuantity())
                .totalPrice(getTotalPrice(cartOption.getOption(), addOrderProductRequest)).build();

        orderProductRepository.save(orderOption);
    }

    private Long getTotalPrice(final Option option, final AddOrderProductRequest addOrderProductRequest) {
        //todo 코드 더러움 수정 필요
        final Long totalPrice =
                option.getProduct().getProductPrice().longValue() *
                        addOrderProductRequest.productQuantity();

        return totalPrice;
    }

    @Override
    public List<FindOrderProductResponseDto> findAllByOrderId(UUID orderId, Member member) {
        final Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException());

        List<OrderOption> orderOptions = orderProductRepository.findAllByOrder(order);

        return orderOptions.stream()
                .map(orderOption -> FindOrderProductResponseDto.of(orderOption,
                        imageRepository.findMainImageByProductId(orderOption.getOption().getProduct().getId())))
                .toList();
    }

    @Override
    public List<FindOrderProductResponseDto> findAllByOrderIdForAdmin(final UUID orderId) {
        final Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException());

        List<OrderOption> orderOptions = orderProductRepository.findAllByOrder(order);

        return orderOptions.stream()
                .map(orderOption -> FindOrderProductResponseDto.of(orderOption,
                        imageRepository.findMainImageByProductId(orderOption.getOption().getProduct().getId())))
                .toList();
    }

    public List<OrderOption> findOrderProducts(final Orders order) {
        final List<OrderOption> orderOptions = orderProductRepository.findAllByOrder(order);
        return orderOptions;
    }

    public List<OrderOption> findOrderProductsWithPessimisticWriteLock(final Orders order) {
        final List<OrderOption> orderOptions = orderProductRepository.findAllByOrderWithPessimisticWriteLock(order);
        return orderOptions;
    }

    @Override
    @Transactional
    public void updateOrderItem(Long orderProductId, ModifyOrderProductRequest modifyOrderProductRequest,
                                Member member) {

    }

    @Override
    @Transactional
    public void removeAllByOrder(final Orders order) {
        orderProductRepository.removeAllByOrder(order);
    }


}
