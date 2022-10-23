package com.orcohen.blogrestapi.service;

import com.orcohen.blogrestapi.dto.PostPageResponse;
import com.orcohen.blogrestapi.dto.PostRequest;
import com.orcohen.blogrestapi.dto.PostResponse;
public interface PostService {
    PostResponse createPost(PostRequest postRequest);


    PostPageResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostResponse getPostById(Long id);


    PostResponse updatePost(PostRequest postRequest, Long id);

    void deletePost(Long id);


}
