package br.com.config.swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Gerenciador de garagem.")
                .description(
                		  "Bem-vindo à nossa API de gerenciamento de garagem! Estamos animados em ter você como nosso cliente e estamos aqui para ajudá-lo a gerenciar suas vagas de estacionamento de forma eficiente e eficaz. \r\n"
                		+ "Nossa API oferece uma variedade de recursos para facilitar o gerenciamento de sua garagem. Você pode facilmente cadastrar registros de veículos, buscar por placa, fazer check-in e check-out de veículos, e muito mais. \r\n"
                		+ "Para começar, você pode fazer uma solicitação para cadastrar um novo registro de veículo, fornecendo as informações necessárias, como a placa do veículo e modelo. Depois de cadastrar, você pode facilmente buscar um registro específico usando a placa do veículo como referência. \r\n"
                		+ "Além disso, você pode fazer check-in e check-out de veículos. Isso ajudará você a rastrear quem esta ou não na garagem.")
                .version("1.0.0")
                .build();
    }
}
