package by.iba.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class AbstractException extends RuntimeException {

    @NonNull
    private final Integer httpCode;

    @NonNull
    private final String error; //for locale message

    @NonNull
    private final String errorDescription; //for debugging

    private Throwable cause;

}
