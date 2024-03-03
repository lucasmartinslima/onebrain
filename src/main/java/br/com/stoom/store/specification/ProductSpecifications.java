package br.com.stoom.store.specification;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import java.util.List;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

public class ProductSpecifications {

    public static Specification<Product> hasNameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name").as(String.class), "%" + name.toUpperCase() + "%");
    }

    public static Specification<Product> hasBrand(String brand) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("brand").get("name").as(String.class), "%" + brand.toLowerCase() + "%");
    }

    public static Specification<Product> productIsActive() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), true);
    }

    public static Specification<Product> hasCategory(List<String> categories) {
        return (root, query, criteriaBuilder) ->{
            Join<Product, Category> categoryJoin = root.join("categories", JoinType.INNER);
            Expression<String> categoryNameExpression = categoryJoin.get("name");
            return categoryNameExpression.in(categories);
        } ;
    }
}
