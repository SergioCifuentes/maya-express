package com.mayaexpress.exception;

import com.mayaexpress.dto.response.ResponseDTO;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(false);
        responseDTO.setStatus_code(-1);
        responseDTO.setMessage(exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleAPIException(APIException customException,
                                                    WebRequest webRequest) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(false);
        responseDTO.setStatus_code(-1);
        responseDTO.setMessage(customException.getMessage());
        return ResponseEntity.status(customException.getStatus()).body(responseDTO);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception,
                                                              WebRequest webRequest){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(false);
        responseDTO.setStatus_code(-1);
        responseDTO.setMessage(exception.getMessage());

        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(Exception ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(false);
        responseDTO.setStatus_code(200);
        responseDTO.setMessage("Authentication failed at controller advice");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
    }


}