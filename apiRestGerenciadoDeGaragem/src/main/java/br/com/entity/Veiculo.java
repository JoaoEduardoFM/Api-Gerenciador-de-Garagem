package br.com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // a classe é criada como uma entidade do banco de dados.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

	@Id
	@ApiModelProperty(value = "Número id", required = true)
	Long id;

	@NotBlank(message = "O campo marca deve ser informado")
	@NotNull(message = "O campo marca deve ser informado")
	@ApiModelProperty(value = "Marca do veículo", required = true)
	private String marca;

	@NotBlank(message = "O campo modelo deve ser informado")
	@NotNull(message = "O campo modelo deve ser informado")
	@ApiModelProperty(value = "Modelo veículo", required = true)
	private String modelo;

	@NotBlank(message = "O campo placa deve ser informado")
	@NotNull(message = "O campo placa deve ser informado")
	@ApiModelProperty(value = "Placa veiculo", required = true)
	private String placa;

	@NotBlank(message = "O campo ano deve ser informado")
	@NotNull(message = "O campo ano deve ser informado")
	@ApiModelProperty(value = "Ano veículo", required = true)
	private String ano;
		
	@ApiModelProperty(value = "Controle fluxo na garagem. true (Check-in) ou false (Check-out)", required = false)
	private Boolean checkInOut;
}
