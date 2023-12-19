package contaCorrenteApplication.controller;

import java.util.List;
import java.util.Map;

import contaCorrenteApplication.exceptions.ContaCorrenteNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import contaCorrenteApplication.model.ContaCorrente;
import contaCorrenteApplication.service.ContaCorrenteService;
import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/")
public class ContaCorrenteController {
	Logger LOGGER = (Logger) LoggerFactory.getLogger(ContaCorrenteController.class);
	@Autowired
	ContaCorrenteService contaCorrenteService;
	@GetMapping
	public List<ContaCorrente> getAll() {
		List<ContaCorrente> contas = contaCorrenteService.getAll();
		LOGGER.info("GET ALL:" + contas);
		return contas;
	}
	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable long id) {
		try {
			LOGGER.info("GET BY ID: " + id);
			return ResponseEntity.ok(contaCorrenteService.getById(id));
		}catch (ContaCorrenteNotFoundException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		LOGGER.info("DELETE: " + id);
		try {
			contaCorrenteService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(id);
		}catch (ContaCorrenteNotFoundException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody ContaCorrente contaCorrente) {
		contaCorrente.setId(id);
		LOGGER.info("UPDATE: " + contaCorrente);
		try {
			contaCorrenteService.update(contaCorrente);
			return ResponseEntity.status(HttpStatus.OK).body(contaCorrente);
		}catch (ContaCorrenteNotFoundException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
		}
	}
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ContaCorrente contaCorrente) {
		contaCorrente.setConta(contaCorrenteService.getMaxConta()+1);
		contaCorrenteService.create(contaCorrente);
		LOGGER.info("CREATE: " + contaCorrente);
		return ResponseEntity.status(HttpStatus.CREATED).body(contaCorrente);
	}
}