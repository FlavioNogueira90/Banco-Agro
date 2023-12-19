package br.com.bancoagro.srvaberturaconta.service.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
public class ContaCorrente {
    private Long id;
    private int agencia;
    private int conta;
    private BigDecimal saldo;
    private BigDecimal limiteChequeEspecial;
    private BigDecimal taxaManutencao;
    private boolean talonarioCheque;
    private Long clienteId;
}
