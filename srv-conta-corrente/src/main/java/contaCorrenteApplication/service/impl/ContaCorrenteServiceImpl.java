package contaCorrenteApplication.service.impl;

import java.util.List;

import contaCorrenteApplication.exceptions.ContaCorrenteNotFoundException;
import contaCorrenteApplication.repository.ContaCorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contaCorrenteApplication.model.ContaCorrente;
import contaCorrenteApplication.service.ContaCorrenteService;

@Service
public class ContaCorrenteServiceImpl implements ContaCorrenteService {
	@Autowired
	 ContaCorrenteRepository repository;
	@Override
	public List<ContaCorrente> getAll() {
		return repository.findAll();
	}
	@Override
	public ContaCorrente create(ContaCorrente contaCorrente) {
		return repository.save(contaCorrente);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}
	@Override
	public ContaCorrente getById(long id) {
		return repository.findById(id).orElseThrow(ContaCorrenteNotFoundException::new);
	}

	@Override
	public Integer getMaxConta() {
		return repository.findMaxConta();
	}

	@Override
	public ContaCorrente update(ContaCorrente contaCorrente) {
		return repository.save(contaCorrente);
	}


}
