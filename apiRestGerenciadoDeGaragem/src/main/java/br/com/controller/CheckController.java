package br.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.entity.Veiculo;
import br.com.model.response.ResponseRest;
import br.com.repository.VeiculoRepository;
import br.com.service.CheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
@RequestMapping("garagem/check")
@Api(value = "Entrada e saída de veiculos", tags = "Entrada e saída de veículos")
public class CheckController {

	@Autowired
	CheckService checkService;

	@Autowired
	VeiculoRepository carroRepository;

	@PatchMapping("/entradaSaida/{id}")
	@ApiOperation(
			value = "efetua a entrada e saída de veículo.", 
			notes = "Controle fluxo na garagem. true (Check-in) ou false (Check-out).")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ResponseRest> AlteraCheck(@PathVariable("id") Long id, Boolean checkInOut,
			@ApiIgnore Veiculo veiculo, @ApiIgnore ResponseRest response) {
		return checkService.AlteraCheck(id, checkInOut, veiculo, response);
	}

	@GetMapping("/verificaCheckin/{checkInOut}")
	@ApiOperation(
			value = "Busca veículo em (Check-in) ou (Check-out).", 
			notes = "Busca controle de fluxo na garagem. true (Check-in) ou false (Check-out).")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Veiculo>> listaCheckInOut(Boolean checkIn) {
		return checkService.listaCheckInOut(checkIn);
	}
}
