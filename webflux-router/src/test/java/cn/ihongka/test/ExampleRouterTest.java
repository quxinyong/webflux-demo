package cn.ihongka.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: ExampleRouterTest.java
 * @Package package cn.ihongka.test;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-05 13:23:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleRouterTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testExampleOneStepFurther(){
        webTestClient
                .get().uri("/exampleFurther1")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello，Spring Webflux Example 1!");

        webTestClient
                .get().uri("/exampleFurther2")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello，Spring Webflux Example 2!");
    }

    @Test
    public void testExampleWithError(){
        webTestClient
                .get().uri("/example/something")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isNotFound();
    }
}
