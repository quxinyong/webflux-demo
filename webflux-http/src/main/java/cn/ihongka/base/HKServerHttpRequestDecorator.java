package cn.ihongka.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static reactor.core.scheduler.Schedulers.single;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: HKServerHttpRequestDecorator.java
 * @Package package cn.ihongka.base;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-05 14:44:09
 */
@Slf4j
public class HKServerHttpRequestDecorator extends ServerHttpRequestDecorator {
    private final StringWriter cachedCopy = new StringWriter();
    private InputStream dataBuffer;
    private DataBuffer bodyDataBuffer;
    private int getBufferTime = 0;
    private byte[] bytes=new byte[]{};

    public HKServerHttpRequestDecorator(ServerHttpRequest delegate) {
        super(delegate);
    }

    @Override
    public Flux<DataBuffer> getBody() {
        if (getBufferTime == 0) {
            getBufferTime++;
            Flux<DataBuffer> flux = super.getBody();
            NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
            return flux
                    .publishOn(single())
                    .defaultIfEmpty(nettyDataBufferFactory.wrap("{}".getBytes()))
                    .map(this::cache)
                    .doOnComplete(() -> trace(getDelegate(), cachedCopy.toString()));
        } else {
//            return Flux.just(dataBuffer);
            return Flux.just(getBodyMore());
        }
    }

    private DataBuffer getBodyMore() {
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
        DataBuffer dataBuffer = nettyDataBufferFactory.wrap(bytes);
        bodyDataBuffer = dataBuffer;
        return bodyDataBuffer;
    }

    private DataBuffer cache(DataBuffer buffer) {
//        ObjectMapper mapper = new ObjectMapper();
        try {
            dataBuffer = buffer.asInputStream();
//         Map<String,Object> json = mapper.readValue(dataBuffer, HashMap.class);
            bytes = IOUtils.toByteArray(dataBuffer);
            NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
//            cachedCopy.write(IOUtils.toString(dataBuffer, StandardCharsets.UTF_8.name()));
            bodyDataBuffer = nettyDataBufferFactory.wrap(bytes);
            return bodyDataBuffer;
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }

    private void trace(ServerHttpRequest request, String requestBody) {
        log.info(requestBody);
    }
}