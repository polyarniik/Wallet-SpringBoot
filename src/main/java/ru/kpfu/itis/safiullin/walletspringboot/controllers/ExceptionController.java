package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kpfu.itis.safiullin.walletspringboot.exceptions.WalletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(WalletException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String walletException() {
        return "error_page";
    }

    @ExceptionHandler(Exception.class)
    public String exception(HttpServletResponse response) {
        response.setStatus(500);
        return "error_page";
    }
}