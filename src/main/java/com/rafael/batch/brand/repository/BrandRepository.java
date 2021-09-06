package com.rafael.batch.brand.repository;

import com.rafael.batch.brand.modal.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
