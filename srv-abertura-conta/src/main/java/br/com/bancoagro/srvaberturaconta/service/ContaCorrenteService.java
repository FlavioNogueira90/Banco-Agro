package br.com.bancoagro.srvaberturaconta.service;

import br.com.bancoagro.srvaberturaconta.exceptions.AberturaContaNotFoundException;
import br.com.bancoagro.srvaberturaconta.service.model.ContaCorrente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ContaCorrenteService {
    @Autowired
    ContaCorrenteApiService contaCorrenteApiService;

    public ResponseEntity<?> getById(Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(contaCorrenteApiService.getById(id));
        }catch (AberturaContaNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not found"));
        }
    }
    public ResponseEntity<?> create(ContaCorrente contaCorrente){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(contaCorrenteApiService.create(contaCorrente));
        }catch (AberturaContaNotFoundException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Message", "Conta não aberta"));
        }
    }

}
