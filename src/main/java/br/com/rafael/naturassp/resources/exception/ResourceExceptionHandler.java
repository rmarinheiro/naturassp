package br.com.rafael.naturassp.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.naturassp.services.exceptions.FileNotFoundException;
import br.com.naturassp.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e , HttpServletRequest request){
		StandardError error = new StandardError();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Object Not Found");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<StandardError> fileNotFoundException(FileNotFoundException e, HttpServletRequest request){
		StandardError error = new StandardError();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("File Exception");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError error = new ValidationError();
		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		error.setError("MethodArgumentException");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		 for (FieldError f : e.getBindingResult().getFieldErrors()) {
			 	error.addError(f.getField(), f.getDefaultMessage());
		 }
		 
		 
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
		
	}


}
