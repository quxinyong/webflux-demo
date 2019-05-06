package cn.ihongka.base;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: MyWebFilter.java
 * @Package package cn.ihongka.base;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-05 14:52:09
 */
@Component
@Order(1)
public class CacheBodyWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        MediaType contentType = headers.getContentType();
        if (MediaType.APPLICATION_JSON.equals(contentType) || MediaType.APPLICATION_JSON_UTF8.equals(contentType)) {
            // body-json：application/json，POST 请求
            //用serverExchange包装类替换SerberWebExchange，以便后续可以进行扩展操作
            HKServerWebExchangeDecorator exchangeDecorator = new HKServerWebExchangeDecorator(exchange);
            return chain.filter(exchangeDecorator);
        }
        return chain.filter(exchange);
    }
}