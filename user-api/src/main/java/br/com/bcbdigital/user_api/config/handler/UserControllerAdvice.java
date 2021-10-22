package br.com.bcbdigital.user_api.config.handler;

import br.com.bcbdigital.backend.dtos.dto.DetalheRespostaDTO;
import br.com.bcbdigital.backend.dtos.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@ControllerAdvice(basePackages="br.com.bcbdigital.user_api.controller")
public class UserControllerAdvice {

        /**
         * Método responsavel por tratar a excessão quando ocorrer
         *
         * @param notfound tipo da excessão
         * @param request requisição feita
         *
         * @return retorna a excessão em formato ResponseEntity
         * */
        @ResponseBody
        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<DetalheRespostaDTO> handleNotFoundException(NoSuchElementException notfound, HttpServletRequest request) {
                var erro = new DetalheRespostaDTO();
                erro.setStatus(HttpStatus.NOT_FOUND.value());
                erro.setMensagem(notfound.getMessage());
                erro.setData(LocalDate.now());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
}
