package ie.tus.soa.category_service.model.jpa;

import ie.tus.soa.category_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}