package org.leoschmittk.swaggertest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


// acessar http://localhost:8000/swagger-ui.html
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(metaData())
                .tags(new Tag("Produto", "Gerencia os produtos"),
                        new Tag("Categoria", "Gerencia as categorias"));
    }

    private  ApiInfoBuilder apiInformation(){
        return new ApiInfoBuilder().title("FUTURODEV Project ")
                .description("API REST spring boot");
    }

    private ApiInfo metaData(){
        return new ApiInfoBuilder().title("Projeto final FUTURO DEV")
                .description("API REST spring boot")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }


    @Override
    protected  void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

//    @Bean
//    public Docket customImplementation(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.leoschmittk")).build().apiInfo(apiInfo());
//
//
//    }
//
//    public ApiInfo apiInfo(){
//        return new ApiInfoBuilder().title("Example API")
//                .description("Estrutura").version("1.0.0").contact(new Contact("","","leoeyeschmittk@gmail.com")).build();
//    }

}
