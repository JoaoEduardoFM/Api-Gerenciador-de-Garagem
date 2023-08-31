package br.com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.model.entity.Veiculo;
import br.com.model.response.ResponseRest;
import br.com.model.response.ResponseRest.messageType;
import br.com.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Service
@AllArgsConstructor
public class VeiculoService {

	@Autowired
	VeiculoRepository carroRepository;

	public ResponseEntity<?> salvaRegistroVeiculo(@Valid Veiculo veiculo, @ApiIgnore ResponseRest response) {
		if (validaSeExisteId(veiculo.getId())) {
			response.setMessage("Id já cadastrado.");
			response.setType(messageType.ATENCAO);
			return new ResponseEntity<ResponseRest>(response, HttpStatus.BAD_REQUEST);

		}
		return ResponseEntity.status(HttpStatus.OK).body(carroRepository.save(veiculo));
	}

	public ResponseEntity<?> atualizaRegistroVeiculo(@Valid Veiculo veiculo, @ApiIgnore ResponseRest response) {
		if (!validaSeExisteId(veiculo.getId()) || veiculo.getId() == null) {
			response.setMessage("Id não existente.");
			response.setType(messageType.ATENCAO);
			return new ResponseEntity<ResponseRest>(response, HttpStatus.BAD_REQUEST);

		}
		return ResponseEntity.status(HttpStatus.OK).body(carroRepository.save(veiculo));
	}

	public ResponseEntity<ResponseRest> deletaVeiculo(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		if (!validaSeExisteId(id)) {
			response.setMessage("Id não existente.");
			response.setType(messageType.ATENCAO);
			return new ResponseEntity<ResponseRest>(response, HttpStatus.BAD_REQUEST);

		}
		carroRepository.deleteById(id);
		response.setMessage("Registro excluído com sucesso.");
		response.setType(messageType.SUCESSO);
		return new ResponseEntity<ResponseRest>(response, HttpStatus.OK);

	}

	public ResponseEntity<?> buscaPorID(@PathVariable Long id, @ApiIgnore ResponseRest response) {
		if (!validaSeExisteId(id)) {
			response.setMessage("Id não existente.");
			response.setType(messageType.ATENCAO);
			return new ResponseEntity<ResponseRest>(response, HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.status(HttpStatus.OK).body(carroRepository.findById(id));
	}

	public ResponseEntity<Veiculo> create(Veiculo carro) {
		return ResponseEntity.status(HttpStatus.CREATED).body(carroRepository.save(carro));
	}

	public Veiculo updatePorId(Long id, Veiculo carro) {
		return carroRepository.save(carro);
	}

	public List<Veiculo> buscaVeiculos() {
		return carroRepository.findAll();
	}

	public List<Veiculo> findByPlaca(String placa) {
		List<Veiculo> bucaPlaca = carroRepository.findByPlacaContainingIgnoreCase(placa);
		return bucaPlaca;
	}

	public List<Veiculo> findByCheck(Boolean checkInOut) {
		List<Veiculo> bucaCheckInOut = carroRepository.findByCheckInOut(checkInOut);
		return bucaCheckInOut;
	}

	public List<Veiculo> findByMarca(String marca) {
		List<Veiculo> bucaPlaca = carroRepository.findByMarcaContainingIgnoreCase(marca);
		return bucaPlaca;
	}

	public List<Veiculo> findByMarcaModelo(String marca, String Modelo) {
		return carroRepository.findByMarcaContainingIgnoreCaseAndModeloContainingIgnoreCase(marca, Modelo);
	}

	public Boolean validaSeExisteId(Long id) {
		Optional<Veiculo> buscaPorID = carroRepository.findById(id);
		try {
			if (buscaPorID.get().getId() != null) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
