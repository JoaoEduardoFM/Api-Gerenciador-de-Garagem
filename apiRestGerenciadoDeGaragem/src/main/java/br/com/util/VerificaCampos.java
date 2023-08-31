package br.com.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.model.entity.Veiculo;
import br.com.repository.VeiculoRepository;
import br.com.service.VeiculoService;

public class VerificaCampos {

	@Autowired
	VeiculoService serviceCarro;

	@Autowired
	VeiculoRepository repository;

	public Boolean verificaId(Long id) {
		try {
			Optional<Veiculo> veiculoCadastrado = repository.findById(id);
			if (veiculoCadastrado.get().getId() != null) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
