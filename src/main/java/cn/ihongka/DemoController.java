package cn.ihongka;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: DemoController.java
 * @Package package cn.ihongka;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-03 17:25:28
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/hello")
    public Map<String, String> hello(String name) {
        Map<String, String> result = new HashMap<>();
        result.put("name", name.isEmpty() ? "李四" : name);
        result.put("city", "北京市");
        return result;
    }
}