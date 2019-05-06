package cn.ihongka.repo;

import cn.ihongka.model.Person;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {

    Flux<Person> findAll();

    Mono<Person> findOne(String id);
}
