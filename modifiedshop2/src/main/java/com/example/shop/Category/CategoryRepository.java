package com.example.shop.Category;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//JPA repository
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    @EntityGraph(attributePaths = {"items"})
    List<CategoryEntity> findAll();

}
