package me.hiramchavez.backend.repository;

import me.hiramchavez.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  boolean existsByName(String name);

  Category getCategoryByName(String name);
}
