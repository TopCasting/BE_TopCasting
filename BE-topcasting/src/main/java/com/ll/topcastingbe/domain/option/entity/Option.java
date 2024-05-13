package com.ll.topcastingbe.domain.option.entity;

import com.ll.topcastingbe.domain.product.entity.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "options")
@Builder
@Getter
public class Option {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	private String colorName;
	private int stock;

	public void deductionStock(Long productQuantity) {
		this.stock -= productQuantity;
	}

	public void change(String colorName, int stock) {
		if(stock < 0) {
			return;
		}
		this.colorName = colorName;
		this.stock = stock;
	}
}
