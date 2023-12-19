package cadastroClienteApplication.service;

import java.util.List;

import cadastroClienteApplication.model.Cliente;

public interface CadastroClienteService {

	Cliente create(Cliente cliente);
	List<Cliente> getAll();
	void delete(Long id);
	Cliente getById(long id);
	Cliente update(Cliente cliente);
	Cliente getByCpf(String cpf);
}
