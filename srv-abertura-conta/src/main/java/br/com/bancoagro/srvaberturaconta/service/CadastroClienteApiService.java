package br.com.bancoagro.srvaberturaconta.service;

import br.com.bancoagro.srvaberturaconta.service.model.Cliente;
import br.com.bancoagro.srvaberturaconta.service.model.ContaCorrente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
@FeignClient(name = "srv-cadastro-cliente", url = "http://localhost:9090/api/srv-cadastro-cliente")
public interface CadastroClienteApiService {
    @RequestMapping(method = RequestMethod.GET, value = "/cpf/{cpf}")
    Cliente getByCpf(@PathVariable String cpf);
}
