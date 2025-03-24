package ie.tus.soa.category_service.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ie.tus.soa.category_service.model.jpa.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryResource {
	private CategoryRepository repository;
	
	@Autowired
	public CategoryResource(CategoryRepository repository) {
        this.repository = repository;
    }
	
	@GetMapping("/categories")
    public List<Category> getAllCategories() {
        return repository.findAll();
    }
	
	@GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category =  repository.findById(id);
        if (category.isEmpty()) {
			System.out.println("Category not found");
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(category.get());
		}
    }
}
