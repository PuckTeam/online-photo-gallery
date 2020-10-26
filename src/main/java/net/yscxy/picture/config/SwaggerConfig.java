package net.yscxy.picture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 描述：
 * <p>
 * Author: WangFuKun
 * Date: 2020/3/14
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 设置默认TOKEN，方便测试
    private static final String TOKEN = "Bearer-eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMi0xIiwiZXhwIjoxNjAxMDExNzQxfQ.NpmFQRNaNeIuVbtp5tSZGmnp7q0w8yjvb_wwFWBMBe4-4gVdV5uWo6PuTrrX6zTNSaNWSXAwnHkvM4iASxE0nQ";

    @Bean
    public Docket api() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("令牌").defaultValue(TOKEN).modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.yscxy.picture"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(500).message("500 queue").responseModel(new ModelRef("Error")).build(), new ResponseMessageBuilder().code(403).message("Forbidden!!!!!").build()));
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "MICAI-SPRINGMVC-SWAGGER REST API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                new Contact("Wang Fu Kun", "dlmsc.cn", "1095652242@qq.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
