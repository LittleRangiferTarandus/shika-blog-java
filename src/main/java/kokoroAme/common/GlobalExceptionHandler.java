package kokoroAme.common;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import org.apache.shiro.ShiroException;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = RuntimeException.class)
	public Result handler(RuntimeException e) {
		log.error("运行时异常",e);
		return Result.fail(e.getMessage());
	}
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = ShiroException.class)
	public Result handler(ShiroException e) {
		log.error("运行时异常",e);
		return Result.fail(401,e.getMessage(),null);
	}
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Result handler(MethodArgumentNotValidException e) {
		log.error("实体校验异常",e);
		BindingResult bindingResult = e.getBindingResult();
		ObjectError error= bindingResult.getAllErrors().stream().findFirst().get(); 
		
		return Result.fail(401,error.getDefaultMessage(),null);
	}
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = IllegalArgumentException.class)
	public Result handler(IllegalArgumentException e) {
		log.error("assert校验异常",e);
		return Result.fail(401,e.getMessage(),null);
	}
}
