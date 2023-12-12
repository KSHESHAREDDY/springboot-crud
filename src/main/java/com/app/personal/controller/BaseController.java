package com.app.personal.controller;

import com.app.personal.dto.ErrorDto;
import com.app.personal.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BaseController {
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ErrorDto generalError(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return  handleException(request, response, exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDto handleException(HttpServletRequest request, HttpServletResponse response, Exception exception, HttpStatus httpStatus) {
        response.setStatus(httpStatus.value());
        return new ErrorDto(exception.getMessage());
    }
}
