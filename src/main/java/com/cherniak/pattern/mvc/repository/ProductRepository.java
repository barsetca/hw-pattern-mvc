package com.cherniak.pattern.mvc.repository;

import com.cherniak.pattern.mvc.model.Product;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


  @Query("SELECT p FROM Product p WHERE p. cost = (SELECT MIN (p.cost) FROM Product p)")
  List<Product> findAllByCostIsMin();

  @Query("SELECT p FROM Product p WHERE p. cost = (SELECT MAX (p.cost) FROM Product p)")
  List<Product> findAllByCostIsMax();

  @Query("SELECT p FROM Product p WHERE p. cost = (SELECT MIN (p.cost) FROM Product p) " +
      "OR p.cost = (SELECT MAX (p.cost) FROM Product p) ORDER BY p.cost")
  List<Product> findAllByCostIsMinMax();

  List<Product> getProductByCostGreaterThanEqualAndCostLessThanEqual(Integer min, Integer max,
      Sort sort);

}
