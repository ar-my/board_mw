package com.army.board.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice  //예외처리를 맡겠다. 
public class GlobalException {
	
//	@ExceptionHandler를 메써드 레벨에 부여하면 특정 예외가 발생했을 때의 처리 로직을 작성할 수 있다.
//    @ExceptionHandler(value = Exception.class) 모든 에러를받음   
//    @ExceptionHandler(value = Exception.class)  
//    public ResponseEntity<?> handleBaseException(Exception e){  
//    	log.info("error: "+e.getMessage());
//    
//        return ResponseEntity.ok(e);  
//    }  

	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<?> handleMethodArgumentNotValidException(DataIntegrityViolationException e) {
	    log.error("handleMethodArgumentNotValidException", e);
	    return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}
	

//    @ExceptionHandler(value = {DataIntegrityViolationException.class, SQLIntegrityConstraintViolationException.class})  
//    public ResponseEntity<?> handleException(Exception e) {
//    	log.info(e.getMessage());
//        return ResponseEntity.ok(e);
//    }


}
