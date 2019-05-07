package cn.ihongka.test;

import org.junit.Test;
import reactor.core.publisher.Flux;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: DemoTest.java
 * @Package package cn.ihongka.test;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-07 16:00:57
 */
public class DemoTest {

    @Test
    public void test1(){
        Flux.just("tom","jack","allen")
                .filter(s->s.length()>3)
                .map(s->s.concat("@qq.com"))
                .subscribe(System.out::println);
    }
}
