package br.com.alura.forum.dtos;

import lombok.Getter;

@Getter
public class TokenDto {

    private String token;
    private String tipo;

    public TokenDto(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;

    }
}
