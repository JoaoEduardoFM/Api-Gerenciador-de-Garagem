package br.com.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.entity.Veiculo;
import br.com.repository.VeiculoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VeiculoService {

	VeiculoRepository repository;

	public ResponseEntity<Veiculo> create( Veiculo carro) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(carro));
	}

	public Veiculo updatePorId(Long id, Veiculo carro) {
		Veiculo carroSalvo = findById(id);
		return repository.save(carroSalvo);
	}

	public List<Veiculo> findAll() {
		return repository.findAll();
	}
	
	public List<Veiculo> findByPlaca(String placa) {
		List<Veiculo> bucaPlaca = repository.findByPlaca(placa);
		return bucaPlaca;
	}
	
	public List<Veiculo> findByCheck(Boolean checkInOut) {
		List<Veiculo> bucaCheckInOut = repository.findByCheckInOut(checkInOut);
		return bucaCheckInOut;
	}
	
	public List<Veiculo> findByMarca(String marca) {
		List<Veiculo> bucaPlaca = repository.findByMarca(marca);
		return bucaPlaca;
	}

	public Veiculo findById(Long id) {
		Veiculo buscaPorId = repository.findById(id).orElseThrow(() -> new RuntimeException());
		return buscaPorId;
	}

	public void delete(Long id) {
		repository.delete(repository.findById(id).orElseThrow(() -> new RuntimeException()));

	}
}
