package com.orcohen.blogrestapi.service.impl;

import com.orcohen.blogrestapi.payload.*;
import com.orcohen.blogrestapi.entity.Post;
import com.orcohen.blogrestapi.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    @Mock
    private PostRepository postRepository;
    private PostServiceImpl underTest;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        underTest = new PostServiceImpl(postRepository, modelMapper);
    }


    @Test
    @Disabled
    void createPost() {
        //when
        PostResponse postResponse = underTest.createPost(new PostRequest(
                "titleTest",
                "descriptionTest",
                "contentTest"
        ));
        Post post = Post.builder()
                .title("titleTest")
                .description("descriptionTest")
                .content("contentTest")
                .build();
        //then
        verify(postRepository).save(post);

    }

    @Test
    @Disabled
    void canGetAllPosts() {
        // when
        underTest.getAllPosts(0, 10, "id", "asc");
        // then
        verify(postRepository).findAll();
    }

    @Test
    @Disabled
    void getPostById() {
    }

    @Test
    @Disabled
    void updatePost() {
    }

    @Test
    @Disabled
    void deletePost() {
    }
}