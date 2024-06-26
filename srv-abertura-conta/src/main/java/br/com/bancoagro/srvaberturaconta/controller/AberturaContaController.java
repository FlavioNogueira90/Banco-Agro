package br.com.bancoagro.srvaberturaconta.controller;

import br.com.bancoagro.srvaberturaconta.exceptions.AberturaContaNotFoundException;
import br.com.bancoagro.srvaberturaconta.service.CadastroClienteService;
import br.com.bancoagro.srvaberturaconta.service.ContaCorrenteService;
import br.com.bancoagro.srvaberturaconta.service.dto.AberturaContaDto;
import br.com.bancoagro.srvaberturaconta.service.model.Cliente;
import br.com.bancoagro.srvaberturaconta.service.model.ContaCorrente;
import ch.qos.logback.classic.Logger;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class AberturaContaController {
	Logger LOGGER = (Logger) LoggerFactory.getLogger(AberturaContaController.class);
	@Autowired
	ContaCorrenteService contaCorrenteService;
	@Autowired
	ContaCorrente contaCorrente;
	@Autowired
	CadastroClienteService cadastroClienteService;
	@Autowired
	Cliente cliente;


	public final MeterRegistry meterRegistry;

	public AberturaContaController(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable long id) {
		try {
			LOGGER.info("GET BY ID: " + id);
			return ResponseEntity.ok(contaCorrenteService.getById(id));
		}catch (AberturaContaNotFoundException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
		}
	}
	@PostMapping
	public ResponseEntity<?> create(@RequestBody AberturaContaDto dados) {
		LOGGER.info("CREATE: Cliente" + dados.getCliente());
		LOGGER.info("CREATE: contaCorrente" + dados.getContaCorrente());
		try {
			Cliente cliente = validaCliente(dados.getCliente().getCpf());
			dados.getContaCorrente().setClienteId(cliente.getId());
			contaCorrenteService.create(dados.getContaCorrente());
			Counter counter = Counter.builder("Quantidade_de_contas_abertas")
							.tag("contas_abertas", "sucesso")
							.register(meterRegistry);
			counter.increment();
			return ResponseEntity.status(HttpStatus.CREATED).body(dados.getContaCorrente());
		}catch (AberturaContaNotFoundException ex){
			Counter counter = Counter.builder("Quantidade_de_erros_na_abertura_de_contas")
					.tag("Nao_abertas", "erros")
					.register(meterRegistry);
			counter.increment();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
		}
	}

	private Cliente validaCliente(String cpf){
		return cadastroClienteService.getByCpf(cpf);
	}
}