package ie.tus.soa.product_service.category;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category-service", url = "http://localhost:8081")
public interface CategoryClient {
    @GetMapping("/categories/{id}")
    Category getCategoryById(@PathVariable("id") Long id);

    @GetMapping("/categories")
    List<Category> getAllCategories();
}