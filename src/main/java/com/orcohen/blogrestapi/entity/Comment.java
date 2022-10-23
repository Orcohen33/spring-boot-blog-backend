package com.orcohen.blogrestapi.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "comments")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String body;


    @ManyToOne(fetch = FetchType.LAZY)     // one post can have many comments
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

}
