package cn.ihongka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: WebFluxHttpApplication.java
 * @Package package cn.ihongka;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-05 14:13:29
 */
@SpringBootApplication
@EnableWebFlux
public class WebFluxHttpApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebFluxHttpApplication.class, args);
    }
}
