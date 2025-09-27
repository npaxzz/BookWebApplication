// Category.java
package com.bookweb.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @ManyToMany(mappedBy = "categories", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Item> items = new HashSet<>();
    
    // Constructors
    public Category() {
        this.isActive = true;
    }
    
    public Category(String name) {
        this();
        this.name = name;
    }
    
    public Category(String name, String description) {
        this(name);
        this.description = description;
    }
        
    // Getters and Setters
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
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
       
    public Set<Item> getItems() {
        return items;
    }
    
    public void setItems(Set<Item> items) {
        this.items = items;
    }
    
    // Helper methods for all items
    public void addItem(Item item) {
        items.add(item);
        item.getCategories().add(this);
    }
    
    public void removeItem(Item item) {
        items.remove(item);
        item.getCategories().remove(this);
    }
    
    public int getItemCount() {
        return items.size();
    }
    
    public boolean hasItems() {
        return !items.isEmpty();
    }
    
    // Helper methods for specific item types
    public Set<Book> getBooks() {
        return items.stream()
            .filter(item -> item instanceof Book)
            .map(item -> (Book) item)
            .collect(HashSet::new, HashSet::add, HashSet::addAll);
    }
    
    public Set<Movie> getMovies() {
        return items.stream()
            .filter(item -> item instanceof Movie)
            .map(item -> (Movie) item)
            .collect(HashSet::new, HashSet::add, HashSet::addAll);
    }
    
    public Set<Cartoon> getCartoons() {
        return items.stream()
            .filter(item -> item instanceof Cartoon)
            .map(item -> (Cartoon) item)
            .collect(HashSet::new, HashSet::add, HashSet::addAll);
    }
    
    public Set<Game> getGames() {
        return items.stream()
            .filter(item -> item instanceof Game)
            .map(item -> (Game) item)
            .collect(HashSet::new, HashSet::add, HashSet::addAll);
    }
    
    // Count methods for each type
//    public long getBookCount() {
//        return items.stream()
//            .filter(item -> item instanceof Book)
//            .count();
//    }
//    
//    public long getMovieCount() {
//        return items.stream()
//            .filter(item -> item instanceof Movie)
//            .count();
//    }
//    
//    public long getCartoonCount() {
//        return items.stream()
//            .filter(item -> item instanceof Cartoon)
//            .count();
//    }
//    
//    public long getGameCount() {
//        return items.stream()
//            .filter(item -> item instanceof Game)
//            .count();
//    }
    
    // Get items by type
    public Set<Item> getItemsByType(ItemType itemType) {
        return items.stream()
            .filter(item -> item.getType() == itemType)
            .collect(HashSet::new, HashSet::add, HashSet::addAll);
    }
    
    // Check if category has specific item type
    public boolean hasItemType(ItemType itemType) {
        return items.stream()
            .anyMatch(item -> item.getType() == itemType);
    }
    
    
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", itemCount=" + getItemCount()+
                '}';
    }
}