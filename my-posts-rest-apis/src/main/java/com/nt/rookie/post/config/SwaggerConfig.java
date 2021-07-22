package com.nt.rookie.post.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@RestController
public class SwaggerConfig {

    @Value("${spring.application.name}")
    String name;

    @Value("${spring.application.description}")
    String description;

    @Bean
    public Docket apiV1() {
        String version = "v1.0";
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex(".*/" + version + "/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(name)
                .description(description)
                .build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView redirect() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public ModelAndView redirectApi() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }

    @RequestMapping(value = "/doc", method = RequestMethod.GET)
    public ModelAndView redirectDoc() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}
