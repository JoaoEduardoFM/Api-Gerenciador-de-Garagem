package br.com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // a classe é criada como uma entidade do banco de dados.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // gera valor id de forma automatica
	Long id;

	@NotBlank(message = "O campo marca deve ser informado")
	@NotNull(message = "O campo marca deve ser informado")
	private String marca;

	@NotBlank(message = "O campo modelo deve ser informado")
	@NotNull(message = "O campo modelo deve ser informado")
	private String modelo;

	@NotBlank(message = "O campo placa deve ser informado")
	@NotNull(message = "O campo placa deve ser informado")
	private String placa;

	@NotBlank(message = "O campo ano deve ser informado")
	@NotNull(message = "O campo ano deve ser informado")
	private String ano;
		
	@NotNull(message = "O campo referente ao Tipo de conta do checkInOut, deve ser preenchido com true (Check-in) ou false (Check-out)")
	private Boolean checkInOut;
}
