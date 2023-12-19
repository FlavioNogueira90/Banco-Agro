package br.com.bancoagro.srvaberturaconta.service.dto;

import br.com.bancoagro.srvaberturaconta.service.model.Cliente;
import br.com.bancoagro.srvaberturaconta.service.model.ContaCorrente;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AberturaContaDto {
    private Cliente cliente;
    private ContaCorrente contaCorrente;
}
