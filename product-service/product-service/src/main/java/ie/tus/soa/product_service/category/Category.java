package ie.tus.soa.product_service.category;

public class Category {
	
    private Long id;
    private String name;
    private String description;
	
	public Category() {
		super();
	}
	
    public Category(String name, String description) { 
    	this.name = name; 
    	this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
    
}
