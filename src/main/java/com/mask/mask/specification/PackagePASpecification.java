package com.mask.mask.specification;

import com.mask.mask.dto.PackagePASearchDTO;
import com.mask.mask.entity.PackagePA;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PackagePASpecification {
    public static Specification<PackagePA> getSpecification(PackagePASearchDTO packagePASearchDTO){
        return new Specification<PackagePA>() {
            @Override
            public Predicate toPredicate(Root<PackagePA> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(packagePASearchDTO.getSearchName()==null)){
                    Predicate namePredicate = criteriaBuilder.like(root.get("name"),"%"+packagePASearchDTO.getSearchName()+"%");
                    predicates.add(namePredicate);
                }
                if (!(packagePASearchDTO.getSearchCompensation()==null)){
                    Predicate compensationPredicate = criteriaBuilder.equal(root.get("compensation"), packagePASearchDTO.getSearchCompensation());
                    predicates.add(compensationPredicate);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
