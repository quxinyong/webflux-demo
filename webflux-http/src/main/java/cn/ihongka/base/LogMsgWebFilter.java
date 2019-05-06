package cn.ihongka.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeDataBinder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static reactor.core.scheduler.Schedulers.single;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: LogMsgWebFilter.java
 * @Package package cn.ihongka.base;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-05 15:39:06
 */
@Slf4j
@Component
@Order(2)
public class LogMsgWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        exchange.getResponse().beforeCommit(() -> sendLogMsg(exchange));
        return chain.filter(exchange);
    }

    private Mono<Void> sendLogMsg(ServerWebExchange exchange) {
        HttpMethod method = exchange.getRequest().getMethod();
        HttpHeaders headers = exchange.getRequest().getHeaders();
        MediaType contentType = headers.getContentType();

        if (MediaType.APPLICATION_JSON.equals(contentType) || MediaType.APPLICATION_JSON_UTF8.equals(contentType)) {
            // body-json：application/json，POST 请求
            Flux<DataBuffer> jsonFlux = exchange.getRequest().getBody();
//            DataBuffer dataBuffer=null;
//            byte[] bytes=new byte[dataBuffer.readableByteCount()];
//            dataBuffer.read(bytes);
//            DataBufferUtils.release(dataBuffer);
            jsonFlux
                    .publishOn(single())
                    .map(this::jsonObject);
        } else {
            //query-data：GET 请求
            // body-multipart-data：multipart/form-data，POST 请求
            // body-json：application/json，POST 请求
            Mono<Map<String, Object>> datas = WebExchangeDataBinder.extractValuesToBind(exchange);
        }
        return Mono.empty();
    }

    private Map<String, Object> jsonObject(DataBuffer buffer) {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = null;
        try {
            json = mapper.readValue(buffer.asInputStream(), HashMap.class);
            System.out.println(json);
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }
}