package com.orcohen.blogrestapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@Table(name = "users",
        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {
//                        "username"
//                }),
//                @UniqueConstraint(columnNames = {
//                        "email"
//                })
        })
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}

