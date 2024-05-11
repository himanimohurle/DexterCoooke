package com.dc.DexterCooke.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dc.DexterCooke.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
