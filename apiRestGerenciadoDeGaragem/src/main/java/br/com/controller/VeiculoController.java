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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.entity.Veiculo;
import br.com.repository.VeiculoRepository;
import br.com.service.VeiculoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("garagem")
@Api(tags = { "veículo" })
public class VeiculoController {
	
	VeiculoRepository carroRepository;
	
	VeiculoService serviceCarro;
	
	
	// cadastra um novo registro
	@PostMapping
	@ResponseBody 
	@ApiOperation (
      value = "Cadastra um veículo.",
      notes = "Cadastra um veículo vinculado a um cliente ."
    )
	public ResponseEntity<Veiculo> salvaRegistroVeiculo(@RequestBody @Valid Veiculo veiculo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(carroRepository.save(veiculo));
	}
	
	// atualiza registro pelo ID de um veiculo cadastrado.
	@PutMapping("atualizaPorId/{id}")
	@ResponseBody 
	@ApiOperation (
      value = "Atualiza cadastro de um veículo.",
      notes = "Atualiza de um veículo vinculado a um cliente ."
    )
	public ResponseEntity<Veiculo> atualizaRegistroVeiculo(@PathVariable Long id, @RequestBody @Valid Veiculo veiculo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(serviceCarro.updatePorId(id, veiculo));
	}
	
	// busca um registro especifico pelo ID
	@GetMapping("buscaPorID/{id}")
	@ApiOperation (
      value = "Busca por ID.",
      notes = "Busca cadastro de um veículo vinculado a um cliente ."
    )
	public ResponseEntity<Veiculo> buscaPorID(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(serviceCarro.findById(id));	
	}
	
	// busca um registro pela placa do veiculo 
	@GetMapping("buscaPorPlaca/{placa}")
	@ApiOperation (
      value = "Busca por placa.",
      notes = "Busca cadastro de um veículo baseado na placa."
    )
	public List<Veiculo> listaPelaPlaca(@RequestParam (name = "placa", required = true) String placa){
		List<Veiculo> buscaPlaca = serviceCarro.findByPlaca(placa);
		return buscaPlaca;
		
	}
	
	// busca veiculo por marca e modelo 
	@GetMapping("buscaMarcaModelo/{marca&modelo}")
	@ApiOperation (
      value = "Busca por marca e modelo.",
      notes = "Busca cadastro de um veículo baseado na merca e modelo."
    )
	public List<Veiculo> listaVeiculo(@RequestParam ( required = true) String marca,String modelo){
		List<Veiculo> buscaVeiculo = carroRepository.findByMarcaAndModelo(marca, modelo);
		return buscaVeiculo;
		
	}
	
	// busca um registro pela marca do veiculo 
	@GetMapping("buscaMarcas/{marca}")
	@ApiOperation (
      value = "Busca por marca.",
      notes = "Busca cadastro de um veículo baseado na merca."
    )
	public List<Veiculo> listaPelaMarca(@RequestParam (name = "marca", required = true) String marca){
		List<Veiculo> buscaMarca = serviceCarro.findByMarca(marca);
		return buscaMarca;
		
	}
	
	// busca todos os registros de todos os carros.
	@GetMapping
	@ApiOperation (
      value = "Busca por veículos cadastrados.",
      notes = "Busca cadastro de um veículo baseado na merca."
    )
	public List<Veiculo> listaTodosCarros(){
		return carroRepository.findAll();	
		
	}
	
	// deleta registro do banco de dados
	@DeleteMapping("/{id}")
	@ResponseBody 
	@ApiOperation (
      value = "Deleta registro.",
      notes = "Deleta registro de veículos cadastrados."
    )
	public void deletaCarro(@PathVariable Long id) {
		carroRepository.deleteById(id);
		
	}
}
