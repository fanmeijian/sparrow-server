package cn.sparrowmini.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.sparrowmini.pem.service.exception.DenyPermissionException;
import cn.sparrowmini.pem.service.exception.NoPermissionException;

@ControllerAdvice
public class GlobalExceptionHandler {
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Object> defaultExceptionHandler(Exception ex) {
//		ex.printStackTrace();
//		return ResponseEntity.badRequest().body(ex.getMessage());
//	}

	@ExceptionHandler(NoPermissionException.class)
	public ResponseEntity<Object> noPermissionExceptionHandler(NoPermissionException ex) {
		ex.printStackTrace();
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(DenyPermissionException.class)
	public ResponseEntity<Object> denyPermissionExceptionHandler(DenyPermissionException ex) {
		ex.printStackTrace();
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
