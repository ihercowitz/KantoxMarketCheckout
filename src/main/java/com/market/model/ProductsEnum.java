package com.market.model;

public enum ProductsEnum {
	GR1(3.11) {
		@Override
		public Double validation(Long total) {
			Long totalCheckedItems = ((total / 2) + (total % 2));
			return this.getPrice() * totalCheckedItems;
		}
	},
	SR1(5.00) {
		@Override
		public Double validation(Long total) {
			Double price = (total >= 3 ? 4.5: this.getPrice());
			return price * total;
		}
	},
	CF1(11.23) {
		@Override
		public Double validation(Long total) {
			Double discount = ( total >= 3 ) ?  2.0/3 : 1;
			return (this.getPrice() * discount) * total;
		}
	};
	
	private Double price;
	
	ProductsEnum(Double price) {
		this.price = price;
	}
	
	public Double getPrice() {
		return price;
	}

	public abstract Double validation(Long total);
}
