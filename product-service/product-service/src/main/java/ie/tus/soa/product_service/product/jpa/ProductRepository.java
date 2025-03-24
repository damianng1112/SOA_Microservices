package ie.tus.soa.product_service.product.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tus.soa.product_service.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}