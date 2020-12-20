package com.mask.mask.specification;

import com.mask.mask.dto.PackageTravelSearchDTO;
import com.mask.mask.entity.PackageTravel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PackageTravelSpecification {
    public static Specification<PackageTravel> getSpecification(PackageTravelSearchDTO packageTravelSearchDTO){
        return new Specification<PackageTravel>() {
            @Override
            public Predicate toPredicate(Root<PackageTravel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(packageTravelSearchDTO.getSearchName()==null)){
                    Predicate namePredicate = criteriaBuilder.like(root.get("name"),"%"+packageTravelSearchDTO.getSearchName()+"%");
                    predicates.add(namePredicate);
                }
                if (!(packageTravelSearchDTO.getSearchDays()==null)){
                    Predicate daysPredicate = criteriaBuilder.equal(root.get("days"), packageTravelSearchDTO.getSearchDays());
                    predicates.add(daysPredicate);
                }
                if (!(packageTravelSearchDTO.getSearchPrice()==null)){
                    Predicate pricePredicate = criteriaBuilder.equal(root.get("price"), packageTravelSearchDTO.getSearchPrice());
                    predicates.add(pricePredicate);
                }
                if (!(packageTravelSearchDTO.getSearchPricePromo()==null)){
                    Predicate pricePromoPredicate = criteriaBuilder.equal(root.get("pricePromo"), packageTravelSearchDTO.getSearchPricePromo());
                    predicates.add(pricePromoPredicate);
                }
                if (!(packageTravelSearchDTO.getSearchVoucher()==null)){
                    Predicate voucherPredicate = criteriaBuilder.like(root.get("voucher"),"%"+packageTravelSearchDTO.getSearchVoucher()+"%");
                    predicates.add(voucherPredicate);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
