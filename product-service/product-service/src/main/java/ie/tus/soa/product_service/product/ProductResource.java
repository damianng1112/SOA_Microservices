package ie.tus.soa.product_service.product;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ie.tus.soa.product_service.category.Category;
import ie.tus.soa.product_service.category.CategoryClient;
import ie.tus.soa.product_service.product.jpa.ProductRepository;

@RestController
public class ProductResource {
	private ProductRepository repository;
	private CategoryClient categoryClient;

	public ProductResource(ProductRepository repository, CategoryClient categoryClient) {
		this.repository = repository;
		this.categoryClient = categoryClient;
	}

	@GetMapping("/products")
    public CompletableFuture<List<ProductResponse>> getAllProducts() {
        List<Product> products = repository.findAll();
        List<CompletableFuture<ProductResponse>> futures = products.stream()
            .map(product -> CompletableFuture.supplyAsync(() -> categoryClient.getCategoryById(product.getCategoryId()))
                .thenApply(category -> new ProductResponse(product, category)))
            .collect(Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
    }

	@GetMapping("/products/{id}")
	public CompletableFuture<ResponseEntity<ProductResponse>> getProductById(@PathVariable Long id) {
	    Optional<Product> productOptional = repository.findById(id);
	    if (productOptional.isEmpty()) {
	        return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
	    }
	    Product product = productOptional.get();
	    return CompletableFuture.supplyAsync(() -> categoryClient.getCategoryById(product.getCategoryId()))
	        .thenApply(category -> {
	            ProductResponse response = new ProductResponse(product, category);
	            return ResponseEntity.ok(response);
	        });
	}

	@PostMapping("/products")
	public ResponseEntity<Void> createProduct(@RequestBody Product product) {
		Product savedProduct = repository.save(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedProduct.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		Optional<Product> existingProduct = repository.findById(id);
		if (existingProduct.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		product.setId(id);
		repository.save(product);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/products/categories")
	public CompletableFuture<List<Category>> getAllCategories() {
		return CompletableFuture.supplyAsync(() -> categoryClient.getAllCategories());
	}
}
