package com.jinuspace.jinu.Repository;

import com.jinuspace.jinu.Entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRedisRepository extends CrudRepository<Person, String> {
}

