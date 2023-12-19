package cadastroClienteApplication.service.impl;

import java.util.List;

import cadastroClienteApplication.exceptions.CadastroClienteNotFoundException;
import cadastroClienteApplication.repository.cadastroClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import cadastroClienteApplication.model.Cliente;
import cadastroClienteApplication.service.CadastroClienteService;

@Service
public class CadastroClienteServiceImpl implements CadastroClienteService {
	@Autowired
	cadastroClienteRepository repository;
	@Override
	public List<Cliente> getAll() {
		return repository.findAll();
	}
	@Override
	public Cliente create(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}
	@Override
	public Cliente getById(long id) {
		return repository.findById(id).orElseThrow(CadastroClienteNotFoundException::new);
	}
	@Override
	public Cliente update(Cliente cliente) {
		return repository.save(cliente);
	}
	@Override
	public Cliente getByCpf(String cpf) {
		Cliente cliente = repository.findByCpf(cpf);
		if (cliente == null){
			throw new CadastroClienteNotFoundException("Cliente não encontrado para o cpf: " + cpf);
		}
		return cliente;
	}
}
