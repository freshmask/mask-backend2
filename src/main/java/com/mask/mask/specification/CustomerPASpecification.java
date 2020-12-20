package com.mask.mask.specification;

import com.mask.mask.dto.CustomerPASearchDTO;
import com.mask.mask.entity.CustomerPA;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CustomerPASpecification {
    public static Specification<CustomerPA> getSpecification(CustomerPASearchDTO customerPASearchDTO){
        return new Specification<CustomerPA>() {
            @Override
            public Predicate toPredicate(Root<CustomerPA> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(customerPASearchDTO.getSearchName()==null)){
                    Predicate namePredicate = criteriaBuilder.like(root.get("name"),"%"+customerPASearchDTO.getSearchName()+"%");
                    predicates.add(namePredicate);
                }
                if (!(customerPASearchDTO.getSearchIdentityNo()==null)){
                    Predicate identityNoPredicate = criteriaBuilder.like(root.get("identityNo"), "%"+customerPASearchDTO.getSearchIdentityNo()+"%");
                    predicates.add(identityNoPredicate);
                }
                if (!(customerPASearchDTO.getSearchIdentityType()==null)){
                    Predicate identityTypePredicate = criteriaBuilder.like(root.get("identityType"), "%"+customerPASearchDTO.getSearchIdentityType()+"%");
                    predicates.add(identityTypePredicate);
                }
                if (!(customerPASearchDTO.getSearchPhoneNumber()==null)){
                    Predicate phoneNumberPredicate = criteriaBuilder.like(root.get("phoneNumber"), "%"+customerPASearchDTO.getSearchPhoneNumber()+"%");
                    predicates.add(phoneNumberPredicate);
                }
                if (!(customerPASearchDTO.getSearchEmail()==null)){
                    Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%"+customerPASearchDTO.getSearchEmail()+"%");
                    predicates.add(emailPredicate);
                }
                if (!(customerPASearchDTO.getSearchAddress()==null)){
                    Predicate addressPredicate = criteriaBuilder.like(root.get("address"), "%"+customerPASearchDTO.getSearchAddress()+"%");
                    predicates.add(addressPredicate);
                }
                if (!(customerPASearchDTO.getSearchHeirName()==null)){
                    Predicate heirNamePredicate = criteriaBuilder.like(root.get("heirName"), "%"+customerPASearchDTO.getSearchHeirName()+"%");
                    predicates.add(heirNamePredicate);
                }
                if (!(customerPASearchDTO.getSearchRelationship()==null)){
                    Predicate relationshipPredicate = criteriaBuilder.like(root.get("relationship"), "%"+customerPASearchDTO.getSearchRelationship()+"%");
                    predicates.add(relationshipPredicate);
                }
                if (!(customerPASearchDTO.getSearchHeirPhoneNumber()==null)){
                    Predicate heirPhoneNumberPredicate = criteriaBuilder.like(root.get("heirPhoneNumber"), "%"+customerPASearchDTO.getSearchHeirPhoneNumber()+"%");
                    predicates.add(heirPhoneNumberPredicate);
                }
                if (!(customerPASearchDTO.getSearchHeirEmail()==null)){
                    Predicate heirEmailPredicate = criteriaBuilder.like(root.get("heirEmail"), "%"+customerPASearchDTO.getSearchHeirEmail()+"%");
                    predicates.add(heirEmailPredicate);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
