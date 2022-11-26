package com.orcohen.blogrestapi.service;

import com.orcohen.blogrestapi.payload.PostPageResponse;
import com.orcohen.blogrestapi.payload.PostRequest;
import com.orcohen.blogrestapi.payload.PostResponse;
public interface PostService {
    PostResponse createPost(PostRequest postRequest);


    PostPageResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostResponse getPostById(Long id);


    PostResponse updatePost(PostRequest postRequest, Long id);

    void deletePost(Long id);


}
