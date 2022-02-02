package com.controlefinanceiro.config.validacao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDto> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
			dto.add(erro);
		});

		return dto;
	}


	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handle(HttpMessageNotReadableException ex) {

		Erro erro = new Erro(ex.getMessage());

		return ResponseEntity.internalServerError().body(erro);
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handle(MethodArgumentTypeMismatchException ex) {

		Erro erro = new Erro(ex.getMessage());

		return ResponseEntity.internalServerError().body(erro);
	}

	/*
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Object> handle(Exception ex, 
			HttpServletRequest request, HttpServletResponse response) {

		if (ex instanceof NullPointerException) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}*/

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Object> handle(JdbcSQLIntegrityConstraintViolationException ex, 
			HttpServletRequest request, HttpServletResponse response) {

		ErroDeBanco erro = new ErroDeBanco(ex.getSQL(), ex.getOriginalMessage());

		return ResponseEntity.internalServerError().body(erro);
	}


	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handle(DataIntegrityViolationException ex, 
			HttpServletRequest request, HttpServletResponse response) {

		Erro erro = new Erro(ex.getMessage());

		return ResponseEntity.internalServerError().body(erro);
	}


	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(java.time.DateTimeException.class)
	public ResponseEntity<Object> handle(java.time.DateTimeException ex, 
			HttpServletRequest request, HttpServletResponse response) {

		Erro erro = new Erro(ex.getMessage());

		return ResponseEntity.internalServerError().body(erro);
	}


	/*
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handle(AccessDeniedException ex) {

		Erro erro = new Erro(ex.getMessage());

		System.out.println("erro - nao tem acesso a este recurso verifique a sua autenticacao");

		return ResponseEntity.badRequest().body(erro);
	}*/
	
}
