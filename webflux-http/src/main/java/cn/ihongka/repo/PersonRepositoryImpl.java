package cn.ihongka.repo;

import cn.ihongka.model.Person;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zshk
 * @version: V4.1
 * @describe:
 * @Title: PersonRepositoryImpl.java
 * @Package package cn.ihongka.repo;
 * @Copyright: Copyright @2017 中少红卡(北京)教育科技有限公司
 * @date 2019-05-05 14:15:45
 */
@Service
public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public Flux<Person> findAll() {
        List<Person> list=new ArrayList<>();
        for(int i=0;i<2;i++){
            Person person=new Person();
            person.setAge(10+i);
            person.setName("李华"+(i+1));
            list.add(person);
        }
        return Flux.fromArray(list.toArray(new Person[]{}));
    }

    @Override
    public Mono<Person> findOne(String id) {
        Person person=new Person();
        person.setName("大名");
        person.setAge(12);
        return Mono.just(person);
    }
}
