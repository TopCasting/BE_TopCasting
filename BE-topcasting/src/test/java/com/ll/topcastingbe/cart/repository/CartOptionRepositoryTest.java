package com.ll.topcastingbe.cart.repository;

import com.ll.topcastingbe.domain.cart.repository.CartOptionRepository;
import com.ll.topcastingbe.domain.cart.repository.CartRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartOptionRepositoryTest {

	@Autowired
	private CartOptionRepository cartOptionRepository;

	@Autowired
	private CartRepository cartRepository;

	@Test
	void findByCartIdTest() {
		Long cartId = 1L;
		cartOptionRepository.findByCartId(cartId);
	}
}
