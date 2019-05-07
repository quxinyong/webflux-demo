package cn.ihongka.controller;

import cn.ihongka.model.Person;
import cn.ihongka.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.Part;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeDataBinder;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: PersonController.java
 * @Package package cn.ihongka.controller;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-05 14:01:59
 */
@RestController
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @PostMapping("/person")
    public Flux<Void> create(ServerHttpRequest request, Person person) {
//        return this.repository.save(personStream).then();
//        return request.getBody();
        return Flux.empty();
    }

    @PostMapping("/data")
    public Mono<Map<String, Object>> data(ServerWebExchange exchange){
        Mono<Map<String, Object>> datas = WebExchangeDataBinder.extractValuesToBind(exchange);
        return datas;
    }

    @PostMapping("/urlencoded")
    public Mono<MultiValueMap<String, String>> urlencoded(ServerWebExchange exchange, Person person) {

        return exchange.getFormData();
    }

    @PostMapping("/formData")
    public Mono<String> multiValueData(ServerWebExchange exchange, Person person) {
        return Mono.just("北京欢迎你！");
//        return exchange.getMultipartData();
    }

    @GetMapping("/person")
    public Flux<Person> list() {
        return this.repository.findAll();
    }

    @GetMapping("/person/{id}")
    public Mono<Person> findById(@PathVariable String id) {
        return this.repository.findOne(id);
    }
}