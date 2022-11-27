package com.orcohen.blogrestapi;

import com.orcohen.blogrestapi.entity.Comment;
import com.orcohen.blogrestapi.entity.Post;
import com.orcohen.blogrestapi.entity.Role;
import com.orcohen.blogrestapi.entity.User;
import com.orcohen.blogrestapi.repository.CommentRepository;
import com.orcohen.blogrestapi.repository.PostRepository;
import com.orcohen.blogrestapi.repository.RoleRepository;
import com.orcohen.blogrestapi.repository.UserRepository;
import com.orcohen.blogrestapi.utils.RsaKeyProperties;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

@EnableConfigurationProperties(RsaKeyProperties.class)
@EnableWebMvc
@SpringBootApplication
public class BlogRestApiApplication {

    @Bean public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogRestApiApplication.class, args);
    }
}
