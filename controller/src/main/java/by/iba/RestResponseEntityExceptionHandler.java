package by.iba;

import by.iba.dto.ErrorDTO;
import by.iba.exception.AbstractInternalApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleInternalApplicationExceptionException(AbstractInternalApplicationException e){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(e.getCode());

        return errorDTO;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadCredentialsException(BadCredentialsException e){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode("FAILED_LOGIN");

        return errorDTO;
    }
}