package br.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.entity.Veiculo;
import br.com.model.response.ResponseRest;
import br.com.repository.VeiculoRepository;
import br.com.service.VeiculoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
@RequestMapping("garagem")
@Api(tags = { "Veículo" })
public class VeiculoController {

	VeiculoRepository carroRepository;

	VeiculoService serviceCarro;

	@PostMapping
	@ApiOperation(
			value = "Cadastra um veículo.", 
			notes = "Cadastra um veículo vinculado a um cliente .")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> salvaRegistroVeiculo(@RequestBody @Valid Veiculo veiculo, @ApiIgnore ResponseRest response) {
		return serviceCarro.salvaRegistroVeiculo(veiculo, response);
	}

	@PutMapping("atualizaPorId/{id}")
	@ApiOperation(
			value = "Atualiza cadastro de um veículo.", 
			notes = "Atualiza de um veículo vinculado a um cliente.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> atualizaRegistroVeiculo(@RequestBody @Valid Veiculo veiculo, @ApiIgnore ResponseRest response) {
		return serviceCarro.atualizaRegistroVeiculo(veiculo, response);
	}

	@DeleteMapping("deletaVeiculo/{id}")
	@ApiOperation(
			value = "Deleta registro.", 
			notes = "Deleta registro de veículos cadastrados.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deletaVeiculo(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		return serviceCarro.deletaVeiculo(id, response);
	}

	@GetMapping("buscaPorID/{id}")
	@ApiOperation(
			value = "Busca por ID.", 
			notes = "Busca cadastro de um veículo vinculado a um cliente.")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> buscaPorID(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		return serviceCarro.buscaPorID(id, response);
	}

	@GetMapping("buscaPorPlaca/{placa}")
	@ApiOperation(
			value = "Busca por placa.", 
			notes = "Busca cadastro de um veículo baseado na placa.")
	@ResponseStatus(HttpStatus.OK)
	public List<Veiculo> listaPelaPlaca(String placa) {
		return serviceCarro.findByPlaca(placa);
	}

	@GetMapping("buscaMarca&Modelo/{marca&modelo}")
	@ApiOperation(
			value = "Busca por marca e modelo.", 
			notes = "Busca cadastro de um veículo baseado na merca e modelo.")
	@ResponseStatus(HttpStatus.OK)
	public List<Veiculo> listaVeiculoMarcaModelo(String marca, String modelo) {
		return serviceCarro.findByMarcaModelo(marca, modelo);
	}

	@GetMapping("buscaMarcas/{marca}")
	@ApiOperation(
			value = "Busca por marca.", 
			notes = "Busca cadastro de um veículo baseado na merca.")
	@ResponseStatus(HttpStatus.OK)
	public List<Veiculo> listaPelaMarca(String marca) {
		return serviceCarro.findByMarca(marca);
	}

	@GetMapping("buscaVeiculos")
	@ApiOperation(
			value = "Busca por veículos cadastrados.", 
			notes = "Busca cadastro de um veículo baseado na marca.")
	@ResponseStatus(HttpStatus.OK)
	public List<Veiculo> listaTodosVeiculos() {
		return serviceCarro.buscaVeiculos();

	}
}
