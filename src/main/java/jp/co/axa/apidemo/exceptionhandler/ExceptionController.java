package jp.co.axa.apidemo.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jp.co.axa.apidemo.customexception.EmployeeNotFoundException;

@RestControllerAdvice
public class ExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getConstraintViolations().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmployeeNotFoundException.class)
	public Map<String,String> handleBusinessException (EmployeeNotFoundException ex){
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}
}	