package br.com.livet.application.controller;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter
public class User {
    private String usuario;

}
