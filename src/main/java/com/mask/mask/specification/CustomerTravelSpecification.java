package com.mask.mask.specification;

import com.mask.mask.dto.CustomerTravelSearchDTO;
import com.mask.mask.entity.CustomerTravel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CustomerTravelSpecification {
    public static Specification<CustomerTravel> getSpecification(CustomerTravelSearchDTO customerTravelSearchDTO){
        return new Specification<CustomerTravel>() {
            @Override
            public Predicate toPredicate(Root<CustomerTravel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(customerTravelSearchDTO.getSearchName()==null)){
                    Predicate namePredicate = criteriaBuilder.like(root.get("name"),"%"+customerTravelSearchDTO.getSearchName()+"%");
                    predicates.add(namePredicate);
                }
                if (!(customerTravelSearchDTO.getSearchAddress()==null)){
                    Predicate addressPredicate = criteriaBuilder.like(root.get("address"), "%"+customerTravelSearchDTO.getSearchAddress()+"%");
                    predicates.add(addressPredicate);
                }
                if (!(customerTravelSearchDTO.getSearchIdentityType()==null)){
                    Predicate identityTypePredicate = criteriaBuilder.like(root.get("identityType"), "%"+customerTravelSearchDTO.getSearchIdentityType()+"%");
                    predicates.add(identityTypePredicate);
                }
                if (!(customerTravelSearchDTO.getSearchIdentityNo()==null)){
                    Predicate identityNoPredicate = criteriaBuilder.like(root.get("identityNo"), "%"+customerTravelSearchDTO.getSearchIdentityNo()+"%");
                    predicates.add(identityNoPredicate);
                }
                if (!(customerTravelSearchDTO.getSearchPhoneNumber()==null)){
                    Predicate phoneNumberPredicate = criteriaBuilder.like(root.get("phoneNumber"), "%"+customerTravelSearchDTO.getSearchPhoneNumber()+"%");
                    predicates.add(phoneNumberPredicate);
                }
                if (!(customerTravelSearchDTO.getSearchEmail()==null)){
                    Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%"+customerTravelSearchDTO.getSearchEmail()+"%");
                    predicates.add(emailPredicate);
                }
                if (!(customerTravelSearchDTO.getSearchHeirName()==null)){
                    Predicate heirNamePredicate = criteriaBuilder.like(root.get("heirName"), "%"+customerTravelSearchDTO.getSearchHeirName()+"%");
                    predicates.add(heirNamePredicate);
                }
                if (!(customerTravelSearchDTO.getSearchHeirPhoneNumber()==null)){
                    Predicate heirPhoneNumberPredicate = criteriaBuilder.like(root.get("heirPhoneNumber"), "%"+customerTravelSearchDTO.getSearchHeirPhoneNumber()+"%");
                    predicates.add(heirPhoneNumberPredicate);
                }
                if (!(customerTravelSearchDTO.getSearchRelationship()==null)){
                    Predicate relationshipPredicate = criteriaBuilder.like(root.get("relationship"), "%"+customerTravelSearchDTO.getSearchRelationship()+"%");
                    predicates.add(relationshipPredicate);
                }
                if (!(customerTravelSearchDTO.getSearchHeirEmail()==null)){
                    Predicate heirEmailPredicate = criteriaBuilder.like(root.get("heirEmail"), customerTravelSearchDTO.getSearchHeirEmail());
                    predicates.add(heirEmailPredicate);
                }
                if (!(customerTravelSearchDTO.getSearchTravelKind()==null)){
                    Predicate travelKindPredicate = criteriaBuilder.like(root.get("travelKind"), "%"+customerTravelSearchDTO.getSearchTravelKind()+"%");
                    predicates.add(travelKindPredicate);
                }
                if (!(customerTravelSearchDTO.getSearchDepartureCity()==null)){
                    Predicate departureCityPredicate = criteriaBuilder.like(root.get("departureCity"), "%"+customerTravelSearchDTO.getSearchDepartureCity()+"%");
                    predicates.add(departureCityPredicate);
                }
                if (!(customerTravelSearchDTO.getSearchDestinationCity()==null)){
                    Predicate destinationCityPredicate = criteriaBuilder.like(root.get("destinationCity"), "%"+customerTravelSearchDTO.getSearchDestinationCity()+"%");
                    predicates.add(destinationCityPredicate);
                }
                if (!(customerTravelSearchDTO.getSearchJourneyLength()==null)){
                    Predicate journeyLengthPredicate = criteriaBuilder.like(root.get("journeyLength"), "%"+customerTravelSearchDTO.getSearchJourneyLength()+"%");
                    predicates.add(journeyLengthPredicate);
                }
                if (!(customerTravelSearchDTO.getSearchDeparturePurpose()==null)){
                    Predicate departurePurposePredicate = criteriaBuilder.like(root.get("departurePurpose"), "%"+customerTravelSearchDTO.getSearchDeparturePurpose()+"%");
                    predicates.add(departurePurposePredicate);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
