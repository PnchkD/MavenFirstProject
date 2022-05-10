package by.iba.specification.car;

import by.iba.entity.car.Car;
import by.iba.specification.SearchOperation;
import by.iba.specification.SpecSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CarSpecificationsBuilder {

    private final List<SpecSearchCriteria> params;

    public CarSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public CarSpecificationsBuilder with(final String key, final String operation, final Object value) {
        return with(null, key, operation, value);
    }

    public CarSpecificationsBuilder with(final String orPredicate, final String key, final String operation, final Object value) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (Objects.nonNull(op)) {
            if (op == SearchOperation.LIKE) { // the operation may be complex operation
              op = SearchOperation.CONTAINS;
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<Car> build() {
        if (params.size() == 0)
            return null;

        Specification<Car> result = new CarSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new CarSpecification(params.get(i)))
                    : Specification.where(result).and(new CarSpecification(params.get(i)));
        }

        return result;
    }

}
