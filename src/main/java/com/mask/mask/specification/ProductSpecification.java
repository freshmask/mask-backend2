package com.mask.mask.specification;

import com.mask.mask.dto.ProductSearchDTO;
import com.mask.mask.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> getSpecification(ProductSearchDTO productSearchDTO){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(productSearchDTO.getSearchProductName()==null)){
                    Predicate productNamePredicate = criteriaBuilder.like(root.get("productName"),"%"+productSearchDTO.getSearchProductName()+"%");
                    predicates.add(productNamePredicate);
                }
                if (!(productSearchDTO.getSearchIsActive()==null)){
                    Predicate isActivePredicate = criteriaBuilder.like(root.get("isActive"),"%"+productSearchDTO.getSearchIsActive()+"%");
                    predicates.add(isActivePredicate);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
