package com.jinuspace.jinu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisRepositoryTest {

//    @Autowired
//    private PersonRedisRepository repo;
//
//    @Test
//    void test() {
//        Person person = new Person("Choi", 32);
//
//        // 저장
//        repo.save(person);
//
//        // `keyspace:id` 값을 가져옴
//        repo.findById(person.getId());
//
//        // Person Entity 의 @RedisHash 에 정의되어 있는 keyspace (people) 에 속한 키의 갯수를 구함
//        repo.count();
//
//        // 삭제
//        //repo.delete(person);
//    }
}