package com.orcohen.blogrestapi.service.impl;

import com.orcohen.blogrestapi.dto.PostPageResponse;
import com.orcohen.blogrestapi.dto.PostRequest;
import com.orcohen.blogrestapi.dto.PostResponse;
import com.orcohen.blogrestapi.entity.Post;
import com.orcohen.blogrestapi.exception.ResourceNotFoundException;
import com.orcohen.blogrestapi.repository.PostRepository;
import com.orcohen.blogrestapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {

        //  Convert PostDto to Entity
        Post post = mapPostRequestToPost(postRequest);
        //  Save Post to DB
        postRepository.save(post);
        //  Convert Post to PostDto and return it to the client
        return mapPostToPostResponse(post);
    }

    @Override
    public PostPageResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> postList = posts.getContent();

        List<PostResponse> content = postList.stream()
                .map(this::mapPostToPostResponse)
                .toList();

        return PostPageResponse.builder()
                .posts(content)
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .last(posts.isLast())
                .build();
    }

    @Override
    public PostResponse getPostById(Long id) {
        return postRepository.findById(id)
                .map(this::mapPostToPostResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    @Override
    public PostResponse updatePost(PostRequest postRequest, Long id) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(postRequest.getTitle());
                    post.setDescription(postRequest.getDescription());
                    post.setContent(postRequest.getContent());
                    postRepository.save(post);
                    return mapPostToPostResponse(post);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


    /* ----------------------------- PRIVATE METHODS ----------------------------- */
    private PostResponse mapPostToPostResponse(Post post) {
        return modelMapper.map(post, PostResponse.class);
    }

    private Post mapPostRequestToPost(PostRequest postRequest) {
        return modelMapper.map(postRequest, Post.class);
    }
}
