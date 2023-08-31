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
import br.com.service.CheckService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class CheckTeste {

	@Autowired
	CheckService checkService;

	Veiculo veiculo = new Veiculo(1111L, "Honda", "Cb150", "ABF6-8684", "2011", true);
	ResponseRest response = new ResponseRest();

	@Test
	@DisplayName("A")
	void initialize() {
		log.info("Iniciando Testes Unitários classe CheckTeste");
		response.setMessage("realizando testes");
		response.setType(messageType.SUCESSO);
	}

	@Test
	@DisplayName("B")
	void alteraCheck() {
		log.info("Alterando (Check-in) - (Check-out)");
		checkService.AlteraCheck(1111L, false, veiculo, response);
	}

	@Test
	@DisplayName("C")
	void listaCheckInOut() {
		log.info("Listando (Check-in) - (Check-out)");
		checkService.listaCheckInOut(true);
	}

}
