package cn.ihongka.base;

import org.springframework.http.codec.multipart.Part;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import reactor.core.publisher.Mono;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: HKServerWebExchangeDecorator.java
 * @Package package cn.ihongka.base;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-05 14:48:54
 */
public class HKServerWebExchangeDecorator extends ServerWebExchangeDecorator {
    private final ServerHttpRequestDecorator requestDecorator;
    protected HKServerWebExchangeDecorator(ServerWebExchange delegate) {
        super(delegate);
        this.requestDecorator=new HKServerHttpRequestDecorator(delegate.getRequest());
    }

    @Override
    public ServerHttpRequest getRequest() {
        return requestDecorator;
    }


    @Override
    public Mono<MultiValueMap<String, String>> getFormData() {
        Mono<MultiValueMap<String, String>> formData=super.getFormData();
        formData.map(k->{
            System.out.println(k.size());
            System.out.println(k);
            return k;
        });
        return super.getFormData();
    }

    @Override
    public Mono<MultiValueMap<String, Part>> getMultipartData() {
        return super.getMultipartData();
    }
}
