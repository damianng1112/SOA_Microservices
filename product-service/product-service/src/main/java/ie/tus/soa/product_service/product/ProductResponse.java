package ie.tus.soa.product_service.product;

import ie.tus.soa.product_service.category.Category;

public class ProductResponse {
	private Long id;
    private String name;
    private Double price;
    private Long categoryId;
    private String categoryName;
    private String categoryDesc;
    
    public ProductResponse(Product product, Category category) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.categoryId = category.getId(); 
        this.categoryName = category.getName();
        this.categoryDesc = category.getDescription();
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
}
