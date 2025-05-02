package io.github.paulooosf.gameboxed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeracaoTokenException.class)
    public ResponseEntity<?> handleGeracaoToken(GeracaoTokenException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar token", ex.getMessage());
    }

    @ExceptionHandler(TokenInvalidoException.class)
    public ResponseEntity<?> handleTokenInvalido(TokenInvalidoException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Token inválido", ex.getMessage());
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<?> handleEmail(EmailException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao enviar e-mail", ex.getMessage());
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<?> handleNaoEncontrado(NaoEncontradoException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Recurso não encontrado", ex.getMessage());
    }

    @ExceptionHandler(UsuarioJaExisteException.class)
    public ResponseEntity<?> handleUsuarioJaExiste(UsuarioJaExisteException ex) {
        return buildResponse(HttpStatus.CONFLICT, "Usuário já existe", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String mensagens = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return buildResponse(HttpStatus.BAD_REQUEST, "Argumentos inválidos", mensagens);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExcecaoGenerica(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado", ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("Status", status.value());
        body.put("Erro", error);
        body.put("Mensagem", message);

        return ResponseEntity.status(status).body(body);
    }
}
