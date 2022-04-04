package by.iba;

import by.iba.exception.IllegalRequestException;
import org.springframework.validation.BindingResult;

import java.util.Objects;

public final class ControllerHelper {

    private ControllerHelper() {
    }

    public static void checkBindingResultAndThrowExceptionIfInvalid(final BindingResult result) {
        if (Objects.nonNull(result)
                && result.hasErrors()) {
            throw new IllegalRequestException(result.getFieldErrors());
        }
    }

}

