package br.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.model.entity.Veiculo;
import br.com.model.response.ResponseRest;
import br.com.model.response.ResponseRest.messageType;
import br.com.service.VeiculoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class VeiculoTeste {

	@Autowired
	VeiculoService veiculoService;

	Veiculo veiculo = new Veiculo(1111L, "Honda", "Cb150", "ABF6-8684", "2011", true);
	ResponseRest response = new ResponseRest();

	@Test
	@DisplayName("A")
	void initialize() {
		log.info("Iniciando Testes Unitários classe VeiculoTeste");
		response.setMessage("realizando testes");
		response.setType(messageType.SUCESSO);
	}
	
	@Test
	@DisplayName("B")
	void salvaRegistroVeiculo() {
		log.info("Salvando Registro");
		veiculoService.salvaRegistroVeiculo(veiculo, response);
	}
	
	@Test
	@DisplayName("C")
	void atualizaRegistroVeiculo() {
		log.info("Atualizando Registro");
		veiculoService.atualizaRegistroVeiculo(veiculo, response);
	}
	
	@Test
	@DisplayName("D")
	void deletaVeiculo() {
		log.info("Deletando Registro");
		veiculoService.deletaVeiculo(1111L, response);
	}
	
	@Test
	@DisplayName("E")
	void buscaPorID() {
		log.info("Buscando por id");
		veiculoService.buscaPorID(1111L, response);
	}
	
	@Test
	@DisplayName("F")
	void listaPelaPlaca() {
		log.info("Buscando por placa");
		veiculoService.findByPlaca("ABF6-8684");
	}
	
	@Test
	@DisplayName("G")
	void listaVeiculoMarcaModelo() {
		log.info("Buscando por marca e modelo");
		veiculoService.findByMarcaModelo("Honda", "Cb150");
	}
	
	@Test
	@DisplayName("H")
	void listaPelaMarca() {
		log.info("Buscando por marca");
		veiculoService.findByMarca("Honda");
	}
	
	@Test
	@DisplayName("I")
	void listaTodosVeiculos() {
		log.info("Buscando por veiculos cadastrados");
		veiculoService.buscaVeiculos();
	}
}