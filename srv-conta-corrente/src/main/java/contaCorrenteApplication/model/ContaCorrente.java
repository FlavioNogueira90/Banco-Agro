package contaCorrenteApplication.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ContaCorrente implements Serializable {
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private int agencia;
		private int conta;
		private BigDecimal saldo;
		private BigDecimal limiteChequeEspecial;
		private BigDecimal taxaManutencao;
		private boolean talonarioCheque;
		private Long clienteId;




}
