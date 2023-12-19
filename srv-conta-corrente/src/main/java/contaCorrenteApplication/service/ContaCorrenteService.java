package contaCorrenteApplication.service;

import java.util.List;

import contaCorrenteApplication.model.ContaCorrente;

public interface ContaCorrenteService {

	ContaCorrente create(ContaCorrente contaCorrente);
	List<ContaCorrente> getAll();
	void delete(Long id);
	ContaCorrente getById(long id);
	Integer getMaxConta();
	ContaCorrente update(ContaCorrente contaCorrente);
}
