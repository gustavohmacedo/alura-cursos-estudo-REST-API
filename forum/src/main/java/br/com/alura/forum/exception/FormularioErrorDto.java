package br.com.alura.forum.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FormularioErrorDto {

    private String campo;
    private String mensagemErro;

}
