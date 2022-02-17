package br.com.alura.forum.exception;


import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
//Para interceptar as exceptions que forem lançadas nos métodos das classes controller, é necessária tal anotação;
@RestControllerAdvice
public class ValidationErrorHandler {

    //Ajuda a pegar mensagem de erro de acordo com idioma que estiver configurado
    private final MessageSource messageSource;


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    //Handler é tipo um interceptador. Logo, após confugurar o Spring, sempre que estiver uma exception em algum
    //método de qualquer controller o Handler é acionado para tratar tal erro.

    //MethodArgumentNotValidException: Erro de formulário lançado pelo Spring, quando é usado o bean validation;
    @ExceptionHandler(MethodArgumentNotValidException.class)

    //O método [handle] será encarregado de pegar a exception que aconteceu e, cosequentemente, a mensagem p/ fazer o tratamento;
    public List<FormularioErrorDto> handle(MethodArgumentNotValidException exception) {

        //Lista de erros de formulário DTOs que serão devolvidos
        List<FormularioErrorDto> dto = new ArrayList<>();

        //getBindingResult(): contém todos os resultados das validações feitas pelo @Valid;
        //getFieldErrors(): contém todos os erros de formulários que aconteceram;
        // fieldErrors: Logo, contém a lista de erros no formulário;
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            //Buscando a mensagem de erro;
            //O método getMessage recebe dois parâmetros: o erro (e) e a mensagem com o idioma correto(LocaleContextHolder.getLocale());
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());

            //passar pelo construtor o campo que deu erro(e.getField()) e a mensagem de erro;
            FormularioErrorDto error = new FormularioErrorDto(e.getField(), mensagem);

            //Adcionando cada erro de campo à List<FormularioErrorDto> dto;
            dto.add(error);
        });

        //Retornandp a lista de erros de FormularioErrorDto
        return dto;
    }
}
