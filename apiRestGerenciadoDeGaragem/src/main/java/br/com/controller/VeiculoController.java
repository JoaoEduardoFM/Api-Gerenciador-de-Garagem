package br.com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.entity.Veiculo;
import br.com.repository.VeiculoRepository;
import br.com.response.ResponseRest;
import br.com.response.ResponseRest.messageType;
import br.com.service.VeiculoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
@RequestMapping("garagem")
@Api(tags = { "veículo" })
public class VeiculoController {
	
	VeiculoRepository carroRepository;
	
	VeiculoService serviceCarro;
	
	
	// cadastra um novo registro
	@PostMapping 
	@ApiOperation (
      value = "Cadastra um veículo.",
      notes = "Cadastra um veículo vinculado a um cliente ."
    )
	public ResponseEntity<?> salvaRegistroVeiculo(@Valid Veiculo veiculo, @ApiIgnore ResponseRest response) {
		if(validaSeExisteId(veiculo.getId())){
			response.setMessage("Id já cadastrado.");
	    	response.setType(messageType.ATENCAO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
			
		}
		;
		return ResponseEntity.status(HttpStatus.OK).body(carroRepository.save(veiculo));
	}
	
	// atualiza registro pelo ID de um veiculo cadastrado.
	@PutMapping("atualizaPorId/{id}")
	@ApiOperation (
      value = "Atualiza cadastro de um veículo.",
      notes = "Atualiza de um veículo vinculado a um cliente ."
    )
	public ResponseEntity<?> atualizaRegistroVeiculo(@Valid Veiculo veiculo, @ApiIgnore ResponseRest response) {
		if(!validaSeExisteId(veiculo.getId()) || veiculo.getId() == null){
			response.setMessage("Id não existente.");
	    	response.setType(messageType.ATENCAO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
			
		}
		return ResponseEntity.status(HttpStatus.OK).body(serviceCarro.updatePorId(veiculo.getId(), veiculo));	
	}
	
	// deleta registro do banco de dados
	@DeleteMapping("deletaVeiculo/{id}")
	@ApiOperation (
      value = "Deleta registro.",
      notes = "Deleta registro de veículos cadastrados."
    )
	public ResponseEntity<ResponseRest> deletaCarro(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		if(!validaSeExisteId(id)){
			response.setMessage("Id não existente.");
	    	response.setType(messageType.ATENCAO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
			
		}
		carroRepository.deleteById(id);
		response.setMessage("Registro excluído com sucesso.");
    	response.setType(messageType.SUCESSO);
    	return new ResponseEntity<ResponseRest>(response,HttpStatus.OK);
		
	}
	
	// busca um registro especifico pelo ID
	@GetMapping("buscaPorID/{id}")
	@ApiOperation (
      value = "Busca por ID.",
      notes = "Busca cadastro de um veículo vinculado a um cliente ."
    )
	public ResponseEntity<?> buscaPorID(@PathVariable Long id, @ApiIgnore ResponseRest response){
		if(!validaSeExisteId(id)){
			response.setMessage("Id não existente.");
	    	response.setType(messageType.ATENCAO);	    	
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
	    	}
		
		return ResponseEntity.status(HttpStatus.OK).body(serviceCarro.findById(id));	
	}
	
	// busca um registro pela placa do veiculo 
	@GetMapping("buscaPorPlaca/{placa}")
	@ApiOperation (
      value = "Busca por placa.",
      notes = "Busca cadastro de um veículo baseado na placa."
    )
	public List<Veiculo> listaPelaPlaca(String placa){
		List<Veiculo> buscaPlaca = serviceCarro.findByPlaca(placa);
		return buscaPlaca;
		
	}
	
	// busca veiculo por marca e modelo 
	@GetMapping("buscaMarca&Modelo/{marca&modelo}")
	@ApiOperation (
      value = "Busca por marca e modelo.",
      notes = "Busca cadastro de um veículo baseado na merca e modelo."
    )
	public List<Veiculo> listaVeiculo(String marca,String modelo){
		List<Veiculo> buscaVeiculo = carroRepository.findByMarcaAndModelo(marca, modelo);
		return buscaVeiculo;
		
	}
	
	// busca um registro pela marca do veiculo 
	@GetMapping("buscaMarcas/{marca}")
	@ApiOperation (
      value = "Busca por marca.",
      notes = "Busca cadastro de um veículo baseado na merca."
    )
	public List<Veiculo> listaPelaMarca(String marca){
		List<Veiculo> buscaMarca = serviceCarro.findByMarca(marca);
		return buscaMarca;
		
	}
	
	// busca todos os registros de todos os carros.
	@GetMapping("buscaVeiculos")
	@ApiOperation (
      value = "Busca por veículos cadastrados.",
      notes = "Busca cadastro de um veículo baseado na marca."
    )
	public List<Veiculo> listaTodosCarros(){
		return carroRepository.findAll();	
		
	}
	
	public Boolean validaSeExisteId(Long id) {
		Optional<Veiculo> buscaPorID = carroRepository.findById(id);
		try {
		if(buscaPorID.get().getId() != null) {
	     return true;
		}
		}catch(Exception e) {
		return false;
		}
		return false;
	}
}
