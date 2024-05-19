package org.example.marketplacebackend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.transaction.Transactional;
import org.example.marketplacebackend.model.Account;
import org.example.marketplacebackend.model.Product;
import org.example.marketplacebackend.model.ProductCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, UUID> {

  @Modifying
  @Transactional
  @Query("""
    UPDATE Product SET status=2, buyer=:deleted WHERE id = :id
    """)
  void updateProductByStatusAndBuyer(UUID id, Account deleted);

  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  List<Product> getAllByProductCategory(ProductCategory categoryId);

  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  @Query("""
      SELECT p from Product p WHERE p.productCategory = :productCategory ORDER BY p.createdAt ASC
      """)
  List<Product> getAllByProductCategoryAndAsc(ProductCategory productCategory);

  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  @Query("""
      SELECT p from Product p WHERE p.productCategory = :productCategory ORDER BY p.createdAt DESC
      """)
  List<Product> getAllByProductCategoryAndDesc(ProductCategory productCategory);

  @EntityGraph(attributePaths = {"productImages"})
  Optional<Product> findProductByIdAndSeller(UUID id, Account seller);

  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.id in (:productIds) ORDER BY p.createdAt DESC
      """)
  List<Product> findTop40Products(List<UUID> productIds);

  @Query("""
        SELECT p.id FROM Product p ORDER BY p.createdAt DESC LIMIT 40
        """)
  List<UUID> findIdTop40ByOrderByCreatedAtDesc();

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice
      """)
  List<Product> getProductsByPrice(Integer minPrice, Integer maxPrice);

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.price ASC
      """)
  List<Product> getProductsByPriceAndAsc(Integer minPrice, Integer maxPrice);

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.price DESC
      """)
  List<Product> getProductsByPriceAndDesc(Integer minPrice, Integer maxPrice);

  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition
      """)
  List<Product> getProductsByCondition(Integer condition);

  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition ORDER BY p.createdAt ASC
      """)
  List<Product> getProductsByConditionAndAsc(Integer condition);

  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition ORDER BY p.createdAt DESC
      """)
  List<Product> getProductsByConditionAndDesc(Integer condition);

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice AND p.productCategory.name = :category
      """)
  List<Product> getProductsByPriceAndCategory(String category, Integer minPrice, Integer maxPrice);

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice AND p.productCategory.name = :category ORDER BY p.createdAt ASC
      """)
  List<Product> getProductsByPriceAndCategoryASC(String category, Integer minPrice,
      Integer maxPrice);

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice AND p.productCategory.name = :category ORDER BY p.createdAt DESC
      """)
  List<Product> getProductsByPriceAndCategoryDESC(String category, Integer minPrice,
      Integer maxPrice);

  @EntityGraph(attributePaths = {"productCategory", "condition", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition AND p.productCategory.name = :category
      """)
  List<Product> getProductsByConditionAndCategory(Integer condition, String category);

  @EntityGraph(attributePaths = {"productCategory", "condition", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition AND p.productCategory.name = :category ORDER BY p.createdAt ASC
      """)
  List<Product> getProductsByConditionAndCategoryAndAsc(Integer condition, String category);

  @EntityGraph(attributePaths = {"productCategory", "condition", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition AND p.productCategory.name = :category ORDER BY p.createdAt DESC
      """)
  List<Product> getProductsByConditionAndCategoryAndDesc(Integer condition, String category);

  @EntityGraph(attributePaths = {"condition", "price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition AND p.price BETWEEN :minPrice AND :maxPrice
      """)
  List<Product> getProductsByConditionAndPrice(Integer condition, Integer minPrice,
      Integer maxPrice);

  @EntityGraph(attributePaths = {"condition", "price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition AND p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.createdAt ASC
      """)
  List<Product> getProductsByConditionAndPriceAndAsc(Integer condition, Integer minPrice,
      Integer maxPrice);

  @EntityGraph(attributePaths = {"condition", "price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition AND p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.createdAt DESC
      """)
  List<Product> getProductsByConditionAndPriceAndDesc(Integer condition, Integer minPrice,
      Integer maxPrice);

  @EntityGraph(attributePaths = {"productCategory", "condition", "price", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition AND p.productCategory.name = :category AND p.price BETWEEN :min AND :max
      """)
  List<Product> getProductsByConditionAndCategoryAndPrice(Integer condition, String category,
      Integer min, Integer max);

  @Query("""
      SELECT p FROM Product p WHERE p.seller = :seller AND p.status IN (0, 1)
      """)
  @EntityGraph(attributePaths = {"productCategory", "buyer", "productImages"})
  List<Product> getActiveListingsHydrateProductCategoryAndBuyer(Account seller);

  @Query("""
      SELECT p FROM Product p WHERE p.seller = :seller AND p.status IN (2)
      """)
  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  List<Product> getSoldProducts(Account seller);

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price >= :minPrice
      """)
  List<Product> getProductsByMinPrice(Integer minPrice);

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price <= :maxPrice
      """)
  List<Product> getProductsByMaxPrice(Integer maxPrice);

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price >= :minPrice ORDER BY p.price ASC
      """)
  List<Product> getAllByMinPriceAndSortAsc(Integer minPrice);

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price >= :minPrice ORDER BY p.price DESC
      """)
  List<Product> getAllByMinPriceAndSortDesc(Integer minPrice);

  @EntityGraph(attributePaths = {"price", "productCategory", "condition"})
  @Query("""
      SELECT p FROM Product p WHERE p.price >= :minPrice AND p.condition = :condition
      """)
  List<Product> getAllByMinPriceAndCondition(Integer minPrice, Integer condition);

  @EntityGraph(attributePaths = {"price", "productCategory", "condition"})
  @Query("""
      SELECT p FROM Product p WHERE p.price >= :minPrice AND p.condition = :condition ORDER BY p.createdAt ASC
      """)
  List<Product> getAllByMinPriceAndConditionAndSortAsc(Integer minPrice, Integer condition);

  @EntityGraph(attributePaths = {"price", "productCategory", "condition"})
  @Query("""
      SELECT p FROM Product p WHERE p.price >= :minPrice AND p.condition = :condition ORDER BY p.createdAt DESC
      """)
  List<Product> getAllByMinPriceAndConditionAndSortDesc(Integer minPrice, Integer condition);

  @EntityGraph(attributePaths = {"price", "productCategory", "condition"})
  @Query("""
      SELECT p FROM Product p WHERE p.price <= :maxPrice AND p.condition = :condition ORDER BY p.createdAt ASC
      """)
  List<Product> getAllByMaxPriceAndConditionAndSortAsc(Integer maxPrice, Integer condition);

  @EntityGraph(attributePaths = {"price", "productCategory", "condition"})
  @Query("""
      SELECT p FROM Product p WHERE p.price <= :maxPrice AND p.condition = :condition ORDER BY p.createdAt DESC
      """)
  List<Product> getAllByMaxPriceAndConditionAndSortDesc(Integer maxPrice, Integer condition);

  @EntityGraph(attributePaths = {"price", "productCategory", "condition"})
  @Query("""
      SELECT p FROM Product p WHERE p.price <= :maxPrice AND p.condition = :condition
      """)
  List<Product> getAllByMaxPriceAndCondition(Integer maxPrice, Integer condition);

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price <= :maxPrice ORDER BY p.price ASC
      """)
  List<Product> getAllByMaxPriceAndSortAsc(Integer maxPrice);

  @EntityGraph(attributePaths = {"price", "productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.price <= :maxPrice ORDER BY p.price DESC
      """)
  List<Product> getAllByMaxPriceAndSortDesc(Integer maxPrice);

  @EntityGraph(attributePaths = {"productCategory", "condition", "price", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition AND p.productCategory.name = :category AND p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.createdAt
      ASC
      """)
  List<Product> getProductsByConditionAndCategoryAndPriceAndAsc(Integer condition, String category,
      Integer minPrice, Integer maxPrice);

  @EntityGraph(attributePaths = {"productCategory", "condition", "price", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.condition = :condition AND p.productCategory.name = :category AND p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.createdAt
      DESC
      """)
  List<Product> getProductsByConditionAndCategoryAndPriceAndDesc(Integer condition, String category,
      Integer minPrice, Integer maxPrice);

  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p ORDER BY p.createdAt ASC
      """)
  List<Product> getProductsByAsc();

  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p ORDER BY p.createdAt DESC
      """)
  List<Product> getProductsByDesc();

  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE lower(p.name) LIKE lower(concat('%', :searchQuery, '%'))
      """)
  List<Product> findBySearchQuery(String searchQuery);

  @EntityGraph(attributePaths = {"productCategory", "productImages"})
  @Query("""
      SELECT p FROM Product p WHERE p.productCategory.id in (:categories) ORDER BY p.createdAt DESC
      """)
  List<Product> getAllProductsByProvidedCategories(List<UUID> categories);
}
