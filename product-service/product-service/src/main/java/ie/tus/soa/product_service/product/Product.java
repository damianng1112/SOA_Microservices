package ie.tus.soa.product_service.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
    private Double price;
	
	@Column(name="categoryId")
    private Long categoryId;

    public Product() {
    	super();
    }
    
	public Product(String name, Double price, Long categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
    @Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", categoryId=" + categoryId + "]";
	}
}
