package com.homecommerce.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homecommerce.entity.Cart;
import com.homecommerce.entity.Customer;
import com.homecommerce.entity.Product;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByCustomer(Customer cust);
	Cart findByCustomerAndProduct(Customer customer,Product product);
	void deleteByCustomer(Customer cust);
}
