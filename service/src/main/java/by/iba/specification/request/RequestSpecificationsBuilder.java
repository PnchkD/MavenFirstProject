package by.iba.specification.request;

import by.iba.entity.request.Request;
import by.iba.specification.SearchOperation;
import by.iba.specification.SpecSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.iba.specification.request.RequestSpecification.*;

public final class RequestSpecificationsBuilder {

    private final List<SpecSearchCriteria> params;

    public RequestSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public RequestSpecificationsBuilder with(final String key, final String operation, final Object value) {
        return with(null, key, operation, value);
    }

    public RequestSpecificationsBuilder with(final String orPredicate, final String key, final String operation, final Object value) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (Objects.nonNull(op)) {
            if (op == SearchOperation.LIKE) { // the operation may be complex operation
                op = SearchOperation.CONTAINS;
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<Request> build() {
        if (params.size() == 0)
            return null;

        Specification<Request> result = null;

        for (int i = 0; i < params.size(); i++) {
            if (params.get(i).getKey().equals("brand")) {
                result = params.get(i).isOrPredicate()
                        ? Specification.where(result).or(findAllByRequestBrand(params.get(i).getValue().toString()))
                        : Specification.where(result).and(findAllByRequestBrand(params.get(i).getValue().toString()));
            } else if(params.get(i).getKey().equals("driveUnit")) {
                result = params.get(i).isOrPredicate()
                        ? Specification.where(result).or(findAllByRequestDriveUnit(params.get(i).getValue().toString()))
                        : Specification.where(result).and(findAllByRequestDriveUnit(params.get(i).getValue().toString()));
            } else if(params.get(i).getKey().equals("bodyType")) {
                result = params.get(i).isOrPredicate()
                        ? Specification.where(result).or(findAllByRequestBodyType(params.get(i).getValue().toString()))
                        : Specification.where(result).and(findAllByRequestBodyType(params.get(i).getValue().toString()));
            } else {
                result = params.get(i).isOrPredicate()
                        ? Specification.where(result).or(new RequestSpecification(params.get(i)))
                        : Specification.where(result).and(new RequestSpecification(params.get(i)));
            }

        }

        return result;
    }

}
