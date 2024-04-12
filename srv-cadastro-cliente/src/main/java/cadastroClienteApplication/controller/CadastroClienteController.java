package cadastroClienteApplication.controller;

import java.util.List;
import java.util.Map;

import cadastroClienteApplication.exceptions.CadastroClienteNotFoundException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	public final MeterRegistry meterRegistry;

	public CadastroClienteController(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	@GetMapping
	public List<Cliente> getAll() {

		List<Cliente> clientes = cadastroClienteService.getAll();
		LOGGER.info("GET ALL:" + clientes);

		Counter counter = Counter.builder("Lista_de_clientes_cadastrados")
				.tag("lista_clientes", "sucesso")
				.register(meterRegistry);
		counter.increment();

		return clientes;
	}
	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable long id) {
		try {
			LOGGER.info("GET BY ID: " + id);
			Counter counter = Counter.builder("Consulta_por_ID")
					.tag("Consulta_cliente", "sucesso")
					.register(meterRegistry);
			counter.increment();

			return ResponseEntity.ok(cadastroClienteService.getById(id));
		}catch (CadastroClienteNotFoundException ex){
			Counter counter = Counter.builder("Falha_consulta_por_ID")
					.tag("Consulta_cliente", "erro")
					.register(meterRegistry);
			counter.increment();

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
		}
	}@GetMapping("/cpf/{cpf}")
	public ResponseEntity getById(@PathVariable String cpf) {
		try {
			LOGGER.info("GET BY CPF: " + cpf);
			Counter counter = Counter.builder("Consulta_por_CPF")
					.tag("Consulta_cliente", "sucesso")
					.register(meterRegistry);
			counter.increment();

			return ResponseEntity.ok(cadastroClienteService.getByCpf(cpf));
		}catch (CadastroClienteNotFoundException ex){
			Counter counter = Counter.builder("Consulta_por_CPF")
					.tag("Consulta_cliente", "erro")
					.register(meterRegistry);
			counter.increment();

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
			Counter counter = Counter.builder("Quantidade_de_clientes_cadastrados")
					.tag("clientes_cadastrados", "sucesso")
					.register(meterRegistry);
			counter.increment();
			return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
		} catch (DataIntegrityViolationException e) {
			Counter counter = Counter.builder("Quantidade_de_erros_no_cadastro_de_clientes")
					.tag("Clientes_nao_cadastrados", "erros")
					.register(meterRegistry);
			counter.increment();
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("Message", "CPF JÁ CADASTRADO"));
		}
	}
}