package br.com.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.entity.Veiculo;
import br.com.repository.VeiculoRepository;
import br.com.response.ResponseRest;
import br.com.service.VeiculoService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/garagem")
public class VeiculoController {
	
	VeiculoRepository carroRepository;
	
	VeiculoService serviceCarro;
	
	
	// cadastra um novo registro
	@PostMapping
	public ResponseEntity<Veiculo> salvaRegistroVeiculo(@RequestBody @Valid Veiculo veiculo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(carroRepository.save(veiculo));
	}
	
	// atualiza registro pelo ID de um veiculo cadastrado.
	@PutMapping("atualizaPorId/{id}")
	public ResponseEntity<Veiculo> atualizaRegistroVeiculo(@PathVariable Long id, @RequestBody @Valid Veiculo veiculo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(serviceCarro.updatePorId(id, veiculo));
	}
	
	// busca um registro especifico pelo ID
	@GetMapping("buscaPorID/{id}")
	public ResponseEntity<Veiculo> buscaPorID(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(serviceCarro.findById(id));	
	}
	
	// busca um registro pela placa do veiculo 
	@GetMapping("/{placa}")
	@RequestMapping( value = "/buscaPorPlaca")
	public List<Veiculo> listaPelaPlaca(@RequestParam (name = "placa", required = true) String placa){
		List<Veiculo> buscaPlaca = serviceCarro.findByPlaca(placa);
		return buscaPlaca;
		
	}
	
	// busca veiculo por marca e modelo 
	@GetMapping("/{marca&modelo}")
	@RequestMapping( value = "/buscaMarcaModelo")
	public List<Veiculo> listaVeiculo(@RequestParam ( required = true) String marca,String modelo){
		List<Veiculo> buscaVeiculo = carroRepository.findByMarcaAndModelo(marca, modelo);
		if(buscaVeiculo.isEmpty() || buscaVeiculo == null) {
		}
		return buscaVeiculo;
		
	}
	
	// busca um registro pela marca do veiculo 
	@GetMapping("/{marca}")
	@RequestMapping( value = "/buscaMarcas")
	public List<Veiculo> listaPelaMarca(@RequestParam (name = "marca", required = true) String marca){
		List<Veiculo> buscaMarca = serviceCarro.findByMarca(marca);
		return buscaMarca;
		
	}
	
	// busca todos os registros de todos os carros.
	@GetMapping
	public List<Veiculo> listaTodosCarros(){
		return carroRepository.findAll();	
		
	}
	
	// deleta registro do banco de dados
	@DeleteMapping("/{id}")
	public void deletaCarro(@PathVariable Long id) {
		carroRepository.deleteById(id);
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ResponseRest response = new ResponseRest();
		List<String> erros = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());	
		
		for (String listaErro : erros) {
			response.setMessage(listaErro);
		}

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
