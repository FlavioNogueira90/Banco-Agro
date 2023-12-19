package br.com.bancoagro.srvaberturaconta.service;

import br.com.bancoagro.srvaberturaconta.service.model.ContaCorrente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
@FeignClient(name = "srv-conta-corrente", url = "http://localhost:9090/api/srv-conta-corrente")
public interface ContaCorrenteApiService {
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ContaCorrente getById(@PathVariable Long id);
    @RequestMapping(method = RequestMethod.POST)
    ContaCorrente create(@RequestBody ContaCorrente contaCorrente);
}
