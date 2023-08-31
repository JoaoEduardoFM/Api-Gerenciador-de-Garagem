package br.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.model.entity.Veiculo;
import br.com.model.response.ResponseRest;
import br.com.model.response.ResponseRest.messageType;
import br.com.repository.VeiculoRepository;
import springfox.documentation.annotations.ApiIgnore;

@Service
public class CheckService {
	
	@Autowired
	VeiculoService serviceCarro;
	
	@Autowired
	VeiculoRepository carroRepository;
	
	public ResponseEntity<ResponseRest> AlteraCheck(@PathVariable("id") Long id, Boolean checkInOut, @ApiIgnore Veiculo veiculo, @ApiIgnore ResponseRest response) {
		if(checkInOut == null) {
			response.setMessage("O campo referente ao Tipo de conta do checkInOut, deve ser preenchido com true (Check-in) ou false (Check-out)");
	    	response.setType(messageType.ERRO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
		}
		if(!validaSeExisteId(id)){
			response.setMessage("Id não existente.");
	    	response.setType(messageType.ATENCAO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
			
		}
		alteraCheckInOut(veiculo, checkInOut, id);
		if(veiculo.getCheckInOut().equals(true)) {
		response.setMessage("Check-in efetuado com sucesso");
    	response.setType(messageType.SUCESSO);
		} else if(veiculo.getCheckInOut().equals(false)){
		response.setMessage("Check-out efetuado com sucesso");
	    response.setType(messageType.SUCESSO);
    	}
		return new ResponseEntity<ResponseRest>(response,HttpStatus.OK);

	}
	
	public ResponseEntity<List<Veiculo>> listaCheckInOut(@RequestParam (name = "checkInOut", required = true) Boolean checkIn){
		return ResponseEntity.status(HttpStatus.OK).body(serviceCarro.findByCheck(checkIn));			
	}	
	
	public ResponseEntity<Veiculo> alteraCheckInOut(Veiculo veiculo, Boolean checkInOut, Long id){
		Optional<Veiculo> veiculoCadastrado = carroRepository.findById(id);
		veiculo.setId(id);
		veiculo.setAno(veiculoCadastrado.get().getAno());
		veiculo.setCheckInOut(checkInOut);
		veiculo.setMarca(veiculoCadastrado.get().getMarca());
		veiculo.setModelo(veiculoCadastrado.get().getModelo());
		veiculo.setPlaca(veiculoCadastrado.get().getPlaca());
        return serviceCarro.create(veiculo);
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
