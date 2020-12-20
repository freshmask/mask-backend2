package com.mask.mask.specification;

import com.mask.mask.dto.ClaimPARSearchDTO;
import com.mask.mask.entity.ClaimPAR;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ClaimPARSpecification {
    public static Specification<ClaimPAR> getSpecification(ClaimPARSearchDTO claimPARSearchDTO){
        return new Specification<ClaimPAR>() {
            @Override
            public Predicate toPredicate(Root<ClaimPAR> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(claimPARSearchDTO.getSearchName()==null)){
                    Predicate namePredicate = criteriaBuilder.like(root.get("name"),"%"+claimPARSearchDTO.getSearchName()+"%");
                    predicates.add(namePredicate);
                }
                if (!(claimPARSearchDTO.getSearchEmail()==null)){
                    Predicate emailPredicate = criteriaBuilder.like(root.get("email"),"%"+claimPARSearchDTO.getSearchEmail()+"%");
                    predicates.add(emailPredicate);
                }
                if (!(claimPARSearchDTO.getSearchLossCause()==null)){
                    Predicate lossCausePredicate = criteriaBuilder.like(root.get("lossCause"),"%"+claimPARSearchDTO.getSearchLossCause()+"%");
                    predicates.add(lossCausePredicate);
                }
                if (!(claimPARSearchDTO.getSearchIncidentLocation()==null)){
                    Predicate incidentLocationPredicate = criteriaBuilder.like(root.get("incidentLocation"),"%"+claimPARSearchDTO.getSearchIncidentLocation()+"%");
                    predicates.add(incidentLocationPredicate);
                }
                if (!(claimPARSearchDTO.getSearchProductType()==null)){
                    Predicate productTypePredicate = criteriaBuilder.like(root.get("productType"),"%"+claimPARSearchDTO.getSearchProductType()+"%");
                    predicates.add(productTypePredicate);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
