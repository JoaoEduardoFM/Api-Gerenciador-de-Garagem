package br.com.util;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.entity.Veiculo;
import br.com.service.VeiculoService;

public class VerificaCampos {
	
	@Autowired
	VeiculoService serviceCarro;
	
	public Boolean verificaId(Long id, Veiculo veiculo){
		try {
			Veiculo veiculoCadastrado = serviceCarro.findById(id);
		if( veiculoCadastrado.getId() != null) {
			return true;
		}
		}catch(Exception e) {
			return false;
		}
		return false;
	}

}
