package com.mask.mask.specification;

import com.mask.mask.dto.CategoryPASearchDTO;
import com.mask.mask.entity.CategoryPA;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CategoryPASpecification {
    public static Specification<CategoryPA> getSpecification(CategoryPASearchDTO categoryPASearchDTO){
        return new Specification<CategoryPA>() {
            @Override
            public Predicate toPredicate(Root<CategoryPA> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(categoryPASearchDTO.getSearchDays()==null)){
                    Predicate daysPredicate = criteriaBuilder.equal(root.get("days"), categoryPASearchDTO.getSearchDays());
                    predicates.add(daysPredicate);
                }
                if (!(categoryPASearchDTO.getSearchPrice()==null)){
                    Predicate pricePredicate = criteriaBuilder.equal(root.get("price"), categoryPASearchDTO.getSearchPrice());
                    predicates.add(pricePredicate);
                }
                if (!(categoryPASearchDTO.getSearchPricePromo()==null)){
                    Predicate pricePromoPredicate = criteriaBuilder.equal(root.get("pricePromo"), categoryPASearchDTO.getSearchPricePromo());
                    predicates.add(pricePromoPredicate);
                }
                if (!(categoryPASearchDTO.getSearchVoucher()==null)){
                    Predicate voucherPredicate = criteriaBuilder.like(root.get("voucher"),"%"+categoryPASearchDTO.getSearchVoucher()+"%");
                    predicates.add(voucherPredicate);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
