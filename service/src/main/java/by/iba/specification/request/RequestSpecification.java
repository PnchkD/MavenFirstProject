package by.iba.specification.request;

import by.iba.entity.car.CarBodyType;
import by.iba.entity.car.CarDriveUnit;
import by.iba.entity.request.Request;
import by.iba.specification.SpecSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RequestSpecification implements Specification<Request> {

    private SpecSearchCriteria criteria;

    public RequestSpecification(final SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SpecSearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(final Root<Request> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH:
                return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            default:
                return null;
        }
    }

    public static Specification<Request> findAllByRequestBrand(String brand) {
        return (Specification<Request>) (root, query, builder) -> {
            List<Predicate> predicates = buildPredicates(root, builder, brand);
            return
                    builder.or(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Request> findAllByRequestDriveUnit(String driveUnit) {
        return (Specification<Request>) (root, query, builder) ->
                builder
                    .equal(root.get("driveUnit"), CarDriveUnit.valueOf(driveUnit));
    }

    public static Specification<Request> findAllByRequestBodyType(String bodyType) {
        return (Specification<Request>) (root, query, builder) ->
                builder
                    .equal(root.get("bodyType"), CarBodyType.valueOf(bodyType));
    }

    private static List<Predicate> buildPredicates(Root<Request> root,
                                                   CriteriaBuilder criteriaBuilder,
                                                   String brand) {
        List<Predicate> predicates = new ArrayList<>();
        System.out.println(root.getClass());
        predicates.add(
                criteriaBuilder.equal(root.join("brand").get("name"), brand));

        return predicates;
    }
}
