package cadastroClienteApplication.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cadastroClienteApplication.exceptions.CadastroClienteNotFoundException;
import cadastroClienteApplication.exceptions.MySQLIntegrityConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cadastroClienteApplication.model.Cliente;
import cadastroClienteApplication.service.CadastroClienteService;
import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/")
public class CadastroClienteController {
	Logger LOGGER = (Logger) LoggerFactory.getLogger(CadastroClienteController.class);
	@Autowired
	CadastroClienteService cadastroClienteService;
	@GetMapping
	public List<Cliente> getAll() {
		List<Cliente> clientes = cadastroClienteService.getAll();
		LOGGER.info("GET ALL:" + clientes);
		return clientes;
	}
	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable long id) {
		try {
			LOGGER.info("GET BY ID: " + id);
			return ResponseEntity.ok(cadastroClienteService.getById(id));
		}catch (CadastroClienteNotFoundException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
		}
	}@GetMapping("/cpf/{cpf}")
	public ResponseEntity getById(@PathVariable String cpf) {
		try {
			LOGGER.info("GET BY CPF: " + cpf);
			return ResponseEntity.ok(cadastroClienteService.getByCpf(cpf));
		}catch (CadastroClienteNotFoundException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		LOGGER.info("DELETE: " + id);
		try {
			cadastroClienteService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(id);
		}catch (CadastroClienteNotFoundException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody Cliente cliente) {
		cliente.setId(id);
		LOGGER.info("UPDATE: " + cliente);
		try {
			cadastroClienteService.update(cliente);
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		}catch (CadastroClienteNotFoundException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "NOT FOUND" ));
		}
	}
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		LOGGER.info("CREATE: " + cliente);
		try {
			cadastroClienteService.create(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("Message", "CPF JÁ CADASTRADO"));
		}
	}
}