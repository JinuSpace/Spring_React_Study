package com.jinuspace.jinu.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Table(name = "qqasdasdqq")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private  String name;
}
