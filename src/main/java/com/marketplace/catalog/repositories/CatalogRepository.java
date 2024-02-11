package com.marketplace.catalog.repositories;

import com.marketplace.catalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CatalogRepository extends JpaRepository<Category, Long> {
}
