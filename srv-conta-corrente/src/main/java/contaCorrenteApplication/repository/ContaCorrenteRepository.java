package contaCorrenteApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import contaCorrenteApplication.model.ContaCorrente;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long>{

    @Query(nativeQuery = true, value = "SELECT COALESCE(MAX(conta), 0) FROM conta_corrente")
    Integer findMaxConta();
}
