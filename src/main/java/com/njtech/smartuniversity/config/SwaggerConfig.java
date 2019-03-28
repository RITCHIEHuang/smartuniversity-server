package com.njtech.smartuniversity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String VERSION = "1.0";
    private static final String TITLE = "智慧校园API接口文档";
    private static final String DESCRIPTION = "智能校园API接口文档说明";
    private static final String STUDENT_DESCRIPTION = "智能校园学生API接口文档说明";
    private static final String TEACHER_DESCRIPTION = "智能校园教师API接口文档说明";


    /**
     * 通用API
     *
     * @return 通用API
     */
    @Bean(value = "generalAPI")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.njtech.smartuniversity.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


//    /**
//     * 学生API
//     *
//     * @return 学生API
//     */
//    @Bean(value = "studentAPI")
//    public Docket studentAPI() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(studentApiInfo())
//                .groupName("学生")
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts());
//    }
//
//    /**
//     * 教师API
//     *
//     * @return 教师API
//     */
//    @Bean(value = "teacherAPI")
//    public Docket teacherAPI() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .groupName("教师")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.njtech.smartuniversity.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts());
//    }

    /**
     * 配置认证模式
     */
    private List<ApiKey> securitySchemes() {
        return newArrayList(new ApiKey("Authorization", "Authorization", "header"));
    }

    /**
     * 配置认证上下文
     */
    private List<SecurityContext> securityContexts() {
        return newArrayList(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build());
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(new SecurityReference("Authorization", authorizationScopes));
    }

    /**
     * 项目信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .version(VERSION)
                .description(DESCRIPTION)
//                .contact(new Contact("ritchie", "https://github.com/RITCHIEHuang",
//                        "ritchie445412@gmail.com"))
                .termsOfServiceUrl("http://localhost/")
                .build();
    }

    /**
     * student信息
     */
    private ApiInfo studentApiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .version(VERSION)
                .description(STUDENT_DESCRIPTION)
//                .contact(new Contact("ritchie", "https://github.com/RITCHIEHuang",
//                        "ritchie445412@gmail.com"))
                .termsOfServiceUrl("http://localhost/")
                .build();
    }


    /**
     * teacher信息
     */
    private ApiInfo teacherApiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .version(VERSION)
                .description(TEACHER_DESCRIPTION)
                .contact(new Contact("ritchie", "https://github.com/RITCHIEHuang",
                        "ritchie445412@gmail.com"))
                .termsOfServiceUrl("http://localhost/")
                .build();
    }
}
