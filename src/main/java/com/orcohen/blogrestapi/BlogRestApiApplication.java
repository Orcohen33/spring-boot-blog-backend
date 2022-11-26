package com.orcohen.blogrestapi;

import com.orcohen.blogrestapi.utils.RsaKeyProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class BlogRestApiApplication {

    @Bean public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogRestApiApplication.class, args);
    }

//    Swagger bean
//    @Bean
//    public DocumentationPluginsBootstrapper swaggerBean() {
//        return new DocumentationPluginsBootstrapper(new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.orcohen.blogrestapi"))
//                .paths(PathSelectors.any())
//                .build());
//    }
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.orcohen.blogrestapi.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
}
