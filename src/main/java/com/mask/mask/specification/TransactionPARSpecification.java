package com.mask.mask.specification;

import com.mask.mask.dto.TransactionPARSearchDTO;
import com.mask.mask.entity.TransactionPAR;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TransactionPARSpecification {
    public static Specification<TransactionPAR> getSpecification(TransactionPARSearchDTO transactionPARSearchDTO){
        return new Specification<TransactionPAR>() {
            @Override
            public Predicate toPredicate(Root<TransactionPAR> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(transactionPARSearchDTO.getSearchIsPayment()==null)){
                    Predicate isPaymentPredicate = criteriaBuilder.like(root.get("isPayment"),"%"+transactionPARSearchDTO.getSearchIsPayment()+"%");
                    predicates.add(isPaymentPredicate);
                }
                if (!(transactionPARSearchDTO.getSearchIsClaim()==null)){
                    Predicate isClaimPredicate = criteriaBuilder.like(root.get("isClaim"),"%"+transactionPARSearchDTO.getSearchIsClaim()+"%");
                    predicates.add(isClaimPredicate);
                }
                if (!(transactionPARSearchDTO.getSearchPremi()==null)){
                    Predicate premiPredicate = criteriaBuilder.equal(root.get("premi"), transactionPARSearchDTO.getSearchPremi());
                    predicates.add(premiPredicate);
                }
                if (!(transactionPARSearchDTO.getSearchStatusPolis()==null)){
                    Predicate statusPolisPredicate = criteriaBuilder.like(root.get("statusPolis"),"%"+transactionPARSearchDTO.getSearchStatusPolis()+"%");
                    predicates.add(statusPolisPredicate);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
