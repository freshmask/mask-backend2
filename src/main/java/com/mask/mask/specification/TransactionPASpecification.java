package com.mask.mask.specification;

import com.mask.mask.dto.TransactionPASearchDTO;
import com.mask.mask.entity.TransactionPA;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionPASpecification {
    public static Specification<TransactionPA> getSpecification(TransactionPASearchDTO transactionPASearchDTO){
        return new Specification<TransactionPA>() {
            @Override
            public Predicate toPredicate(Root<TransactionPA> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(transactionPASearchDTO.getSearchIsPayment()==null)){
                    Predicate isPaymentPredicate = criteriaBuilder.like(root.get("voucher"),"%"+transactionPASearchDTO.getSearchIsPayment()+"%");
                    predicates.add(isPaymentPredicate);
                }
                if (!(transactionPASearchDTO.getSearchIsClaim()==null)){
                    Predicate isClaimPredicate = criteriaBuilder.like(root.get("isClaim"),"%"+transactionPASearchDTO.getSearchIsClaim()+"%");
                    predicates.add(isClaimPredicate);
                }
                if (!(transactionPASearchDTO.getSearchPremi()==null)){
                    Predicate premiPredicate = criteriaBuilder.equal(root.get("premi"), transactionPASearchDTO.getSearchPremi());
                    predicates.add(premiPredicate);
                }
                if (!(transactionPASearchDTO.getSearchStatusPolis()==null)){
                    Predicate statusPolisPredicate = criteriaBuilder.like(root.get("statusPolis"),"%"+transactionPASearchDTO.getSearchStatusPolis()+"%");
                    predicates.add(statusPolisPredicate);
                }
                if (!(transactionPASearchDTO.getStartDate()==null)){
                    Predicate startsAfterBegin =  criteriaBuilder.greaterThan(root.get("startDate"), transactionPASearchDTO.getStartDate());
                    Predicate endsBeforeEnd =  criteriaBuilder.lessThan(root.get("endDate"), transactionPASearchDTO.getStartDate());
                    Predicate isContained = criteriaBuilder.and(startsAfterBegin, endsBeforeEnd);
                    predicates.add(isContained);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
