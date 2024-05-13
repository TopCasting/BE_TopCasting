package com.ll.topcastingbe.domain.cart.entity;

import com.ll.topcastingbe.domain.option.entity.Option;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CartOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	private Option option;

	private int productQuantity;

	public CartOption(Cart cart, Option option, int productQuantity) {
		this.cart = cart;
		this.option = option;
		this.productQuantity = productQuantity;
	}

	public void changeProductQuantity(int quantity) {
		productQuantity = quantity;
	}

	public boolean isNotCartOwner(Long memberId) {
		return !this.cart.isMatchingMemberId(memberId);
	}
}
