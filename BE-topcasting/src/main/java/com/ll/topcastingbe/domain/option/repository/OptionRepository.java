package com.ll.topcastingbe.domain.option.repository;

import com.ll.topcastingbe.domain.option.entity.Option;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OptionRepository extends JpaRepository<Option, Long> {

    @Query("select op from Option op where op.product.id = :productId")
    List<Option> findByProductId(@Param("productId") Long productId);
}
