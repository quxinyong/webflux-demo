package cn.ihongka.config;

import cn.ihongka.handler.ExampleHanlder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: ExampleRouter.java
 * @Package package cn.ihongka.config;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-05 09:49:20
 */
@Configuration
public class ExampleRouter {
    @Bean
    public RouterFunction<ServerResponse> route(ExampleHanlder exampleHandler){
        return RouterFunctions.route(RequestPredicates.GET("/example").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),exampleHandler::hello);
    }
}
