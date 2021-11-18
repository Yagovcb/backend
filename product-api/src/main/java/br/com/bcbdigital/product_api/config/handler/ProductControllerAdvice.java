package br.com.bcbdigital.product_api.config.handler;

import br.com.bcbdigital.backend.dtos.dto.DetalheRespostaDTO;
import br.com.bcbdigital.backend.dtos.exceptions.CategoryNotFoundException;
import br.com.bcbdigital.backend.dtos.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@ControllerAdvice(basePackages = "br.com.bcbdigital.product_api.controller")
public class ProductControllerAdvice {

    private static final String WHITE_SPACE = "	";
    private static final String VALOR_INVALIDO = "Valor	inválido para o(s) campo(s):";

    /**
     * Método responsavel por tratar a excessão quando ocorrer
     *
     * @param notfound tipo da excessão
     * @param request  requisição feita
     * @return retorna a excessão em formato ResponseEntity
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<DetalheRespostaDTO> handleProductNotFoundException(ProductNotFoundException notfound, HttpServletRequest request) {
        var erro = new DetalheRespostaDTO();
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setMensagem("Produto não encontrado");
        erro.setData(LocalDate.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    /**
     * Método responsavel por tratar a excessão quando ocorrer
     *
     * @param notfound tipo da excessão
     * @param request  requisição feita
     * @return retorna a excessão em formato ResponseEntity
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<DetalheRespostaDTO> handleCategoryNotFoundException(CategoryNotFoundException notfound, HttpServletRequest request) {
        var erro = new DetalheRespostaDTO();
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setMensagem("Categoria não encontrada");
        erro.setData(LocalDate.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DetalheRespostaDTO> processValidationError(MethodArgumentNotValidException ex) {
        var erro = new DetalheRespostaDTO();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder sb = new StringBuilder(VALOR_INVALIDO);

        for (FieldError fieldError : fieldErrors) {
            sb.append(WHITE_SPACE);
            sb.append(fieldError.getField());
        }
        erro.setMensagem(sb.toString());
        erro.setData(LocalDate.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
