package com.ll.topcastingbe.domain.cart.repository;

import com.ll.topcastingbe.domain.cart.entity.CartOption;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartOptionRepository extends JpaRepository<CartOption, Long> {

    @Query("select co from CartOption co join fetch co.option op join fetch op.item it join fetch it.image i where co.cart.id = :cartId")
    List<CartOption> findByCartId(@Param("cartId") Long cartId);

    //장바구니에 해당 아이템+옵션이 있는지 확인하는 용도
    @Query("select co from CartOption co where co.cart.id =:cartId and co.option.id = :optionId ")
    CartOption findByCartIdAndOptionId(@Param("cartId") Long cartId, @Param("optionId") Long optionId);

    @Query("select co from CartOption co join fetch co.cart c where co.id = :cartItemId")
    Optional<CartOption> findByIdWithMember(@Param("cartItemId") Long cartItemId);

    @Modifying(clearAutomatically = true) //벌크연산수행 & 수행후 영속성 컨텍스트 초기화
    @Query("delete from CartOption co where co.cart.id = :cartId")
    int deleteCartItemByCartId(@Param("cartId") Long cartId);
}
