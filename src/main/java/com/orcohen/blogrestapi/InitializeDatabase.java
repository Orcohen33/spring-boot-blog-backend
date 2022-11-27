package com.orcohen.blogrestapi;

import com.orcohen.blogrestapi.entity.Comment;
import com.orcohen.blogrestapi.entity.Post;
import com.orcohen.blogrestapi.entity.Role;
import com.orcohen.blogrestapi.entity.User;
import com.orcohen.blogrestapi.exception.ResourceNotFoundException;
import com.orcohen.blogrestapi.repository.CommentRepository;
import com.orcohen.blogrestapi.repository.PostRepository;
import com.orcohen.blogrestapi.repository.RoleRepository;
import com.orcohen.blogrestapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


/**
 * Initialize the database with some data
 * @author Or Cohen
 */
@Component
@Slf4j
public class InitializeDatabase implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public InitializeDatabase(UserRepository userRepository,
                              RoleRepository roleRepository,
                              CommentRepository commentRepository,
                              PostRepository postRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

//    @Override
    public void run(String... args) throws Exception {
        // Create ADMIN, USER roles
        if (roleRepository.count() == 0) {
            log.info("Initializing roles");
            Role adminRole = Role.builder()
                    .name("ADMIN")
                    .build();
            Role userRole =  Role.builder()
                    .name("USER")
                    .build();
            roleRepository.saveAll(List.of(adminRole, userRole));
        }
        // Create ADMIN, USER users
        if (userRepository.count() == 0) {
            log.info("Initializing users");
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "name", "ADMIN"));
            Role userRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "name", "USER"));
            User admin = User.builder()
                    .email("admin@admin.com")
                    .name("Admin")
                    .username("admin")
                    .password("admin")
                    .build();
            userRepository.save(admin);
            admin.setRoles(Collections.singleton(adminRole));
            userRepository.save(admin);
            User user = User.builder()
                    .email("user@user.com")
                    .name("User")
                    .username("user")
                    .password("user")
                    .build();
            userRepository.save(user);
            user.setRoles(Collections.singleton(userRole));
            userRepository.save(user);
        }
        // Create 2 posts
        if (postRepository.count() == 0) {
            log.info("Initializing posts");
            Post post1 = Post.builder()
                    .title("Post 1")
                    .content("Content 1")
                    .description("Description 1")
                    .build();
            postRepository.save(post1);
            // Create 2 comments for post 1
            Comment comment1 = Comment.builder()
                    .name("Comment 1")
                    .email("Comment 1 email")
                    .body("Comment 1 body")
                    .post(post1)
                    .build();
            Comment comment2 = Comment.builder()
                    .name("Comment 2")
                    .email("Comment 2 email")
                    .body("Comment 2 body")
                    .post(post1)
                    .build();
            commentRepository.saveAll(List.of(comment1, comment2));

            Post post2 = Post.builder()
                    .title("Post 2")
                    .content("Content 2")
                    .description("Description 2")
                    .build();
            postRepository.save(post2);
            // Create 1 comment for post 2
            Comment comment3 = Comment.builder()
                    .name("Comment 3")
                    .email("Comment 3 email")
                    .body("Comment 3 body")
                    .post(post2)
                    .build();
            commentRepository.save(comment3);
        }
        log.info("The sample data has been generated");
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
