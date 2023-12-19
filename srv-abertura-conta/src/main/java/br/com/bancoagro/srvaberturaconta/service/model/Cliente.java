package br.com.bancoagro.srvaberturaconta.service.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Cliente {
		private Long id;
		private String nome;
		private String cpf;
		private String email;
}
