package com.jinuspace.jinu.domain.user.repository;

import com.jinuspace.jinu.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User,Long> {

}
