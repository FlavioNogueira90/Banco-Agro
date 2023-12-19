package cadastroClienteApplication.repository;

import cadastroClienteApplication.exceptions.CadastroClienteNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cadastroClienteApplication.model.Cliente;

@Repository
public interface cadastroClienteRepository extends JpaRepository<Cliente, Long>{

    @Query(nativeQuery = true, value = "SELECT MAX(conta) FROM conta_corrente")
    Integer findMaxConta();

    @Query(nativeQuery = true,  value = "SELECT * FROM CLIENTE WHERE CPF = :cpf")
    Cliente findByCpf(String cpf);


}
