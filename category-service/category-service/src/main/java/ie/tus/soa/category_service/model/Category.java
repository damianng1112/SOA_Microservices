package ie.tus.soa.category_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Category {
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(name="name")
    private String name;
	
	@Column(name = "description")
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
