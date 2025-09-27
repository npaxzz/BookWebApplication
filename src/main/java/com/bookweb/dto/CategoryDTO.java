package com.bookweb.dto;

import com.bookweb.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be at most 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must be at most 1000 characters")
    private String description;

    private Boolean isActive = true;

    // optional for view: item count
    private Integer itemCount;

    public CategoryDTO() {}

    public CategoryDTO(Long id, String name, String description, Boolean isActive, Integer itemCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.itemCount = itemCount;
    }

    public static CategoryDTO fromEntity(Category c) {
        if (c == null) return null;
        return new CategoryDTO(
                c.getId(),
                c.getName(),
                c.getDescription(),
                c.getIsActive(),
                c.getItemCount()
        );
    }

    public Category toEntity() {
        Category c = new Category();
        c.setName(this.name);
        c.setDescription(this.description);
        c.setIsActive(this.isActive == null ? Boolean.TRUE : this.isActive);
        return c;
    }

    public void updateEntity(Category existing) {
        if (existing == null) return;
        existing.setName(this.name);
        existing.setDescription(this.description);
        if (this.isActive != null) {
            existing.setIsActive(this.isActive);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    public Integer getItemCount() { return itemCount; }
    public void setItemCount(Integer itemCount) { this.itemCount = itemCount; }
}