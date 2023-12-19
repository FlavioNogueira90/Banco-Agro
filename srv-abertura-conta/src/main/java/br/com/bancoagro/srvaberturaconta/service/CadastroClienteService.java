package br.com.bancoagro.srvaberturaconta.service;

import br.com.bancoagro.srvaberturaconta.service.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {
    @Autowired
    CadastroClienteApiService cadastroClienteApiService;

    public Cliente getByCpf(String cpf){
            return cadastroClienteApiService.getByCpf(cpf);
    }
}
