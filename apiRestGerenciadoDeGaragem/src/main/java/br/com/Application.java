package br.com;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}


	public static void main(String[] args) {		  
		SpringApplication.run(Application.class, args);
		 System.out.println(" {Bem-vindo à nossa API de gerenciamento de garagem.! \n "
			   		+ "Para acessar as funcionalidades acesse o swagger no seguinte link. \n "
			   		+ "http://localhost:8080/swagger-ui.html#/}");
	}

}
