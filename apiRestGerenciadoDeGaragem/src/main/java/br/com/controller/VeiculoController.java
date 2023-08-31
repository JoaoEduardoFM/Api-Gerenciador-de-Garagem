package br.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	// cadastra um novo registro
	@PostMapping
	@ApiOperation(
			value = "Cadastra um veículo.", 
			notes = "Cadastra um veículo vinculado a um cliente .")
	public ResponseEntity<?> salvaRegistroVeiculo(@RequestBody @Valid Veiculo veiculo, @ApiIgnore ResponseRest response) {
		return serviceCarro.salvaRegistroVeiculo(veiculo, response);
	}

	// atualiza registro pelo ID de um veiculo cadastrado.
	@PutMapping("atualizaPorId/{id}")
	@ApiOperation(
			value = "Atualiza cadastro de um veículo.", 
			notes = "Atualiza de um veículo vinculado a um cliente.")
	public ResponseEntity<?> atualizaRegistroVeiculo(@RequestBody @Valid Veiculo veiculo, @ApiIgnore ResponseRest response) {
		return serviceCarro.atualizaRegistroVeiculo(veiculo, response);
	}

	// deleta registro do banco de dados
	@DeleteMapping("deletaVeiculo/{id}")
	@ApiOperation(
			value = "Deleta registro.", 
			notes = "Deleta registro de veículos cadastrados.")
	public ResponseEntity<?> deletaVeiculo(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		return serviceCarro.deletaVeiculo(id, response);
	}

	// busca um registro especifico pelo ID
	@GetMapping("buscaPorID/{id}")
	@ApiOperation(
			value = "Busca por ID.", 
			notes = "Busca cadastro de um veículo vinculado a um cliente.")
	public ResponseEntity<?> buscaPorID(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		return serviceCarro.buscaPorID(id, response);
	}

	// busca um registro pela placa do veiculo
	@GetMapping("buscaPorPlaca/{placa}")
	@ApiOperation(
			value = "Busca por placa.", 
			notes = "Busca cadastro de um veículo baseado na placa.")
	public List<Veiculo> listaPelaPlaca(String placa) {
		return serviceCarro.findByPlaca(placa);
	}

	// busca veiculo por marca e modelo
	@GetMapping("buscaMarca&Modelo/{marca&modelo}")
	@ApiOperation(
			value = "Busca por marca e modelo.", 
			notes = "Busca cadastro de um veículo baseado na merca e modelo.")
	public List<Veiculo> listaVeiculoMarcaModelo(String marca, String modelo) {
		return serviceCarro.findByMarcaModelo(marca, modelo);
	}

	// busca um registro pela marca do veiculo
	@GetMapping("buscaMarcas/{marca}")
	@ApiOperation(
			value = "Busca por marca.", 
			notes = "Busca cadastro de um veículo baseado na merca.")
	public List<Veiculo> listaPelaMarca(String marca) {
		return serviceCarro.findByMarca(marca);
	}

	// busca todos os registros de todos os carros.
	@GetMapping("buscaVeiculos")
	@ApiOperation(
			value = "Busca por veículos cadastrados.", 
			notes = "Busca cadastro de um veículo baseado na marca.")
	public List<Veiculo> listaTodosVeiculos() {
		return serviceCarro.buscaVeiculos();

	}
}
