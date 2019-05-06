package cn.ihongka.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: ExampleHanlder.java
 * @Package package cn.ihongka.config;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-05 09:53:49
 */
@Component
public class ExampleHanlder {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello,Spring Webflux Example!"));
    }

    public Mono<ServerResponse> helloFurther1(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello，Spring Webflux Example 1!"));
    }
    public Mono<ServerResponse> helloFurther2(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello，Spring Webflux Example 2!"));
    }

}