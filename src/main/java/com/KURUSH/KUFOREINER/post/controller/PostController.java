package com.KURUSH.KUFOREINER.post.controller;

import com.KURUSH.KUFOREINER.global.response.HttpResponse;
import com.KURUSH.KUFOREINER.post.dto.InfoPostCreateRequest;
import com.KURUSH.KUFOREINER.post.dto.MatchingPostCreateRequest;
import com.KURUSH.KUFOREINER.post.dto.PostReadResponse;
import com.KURUSH.KUFOREINER.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/communities")
@Slf4j
public class PostController {
    private final PostService postService;

    @GetMapping("/study")
    public HttpResponse<List<PostReadResponse>> getStudyPosts() {
        List<PostReadResponse> posts = postService.getPostsByCategory("study");
        return HttpResponse.okBuild(posts);
    }

    @GetMapping("/lifestyle")
    public HttpResponse<List<PostReadResponse>> getLifestylePosts() {
        List<PostReadResponse> posts = postService.getPostsByCategory("lifestyle");
        return HttpResponse.okBuild(posts);
    }

    @GetMapping("/nation")
    public HttpResponse<List<PostReadResponse>> getNationPosts() {
        List<PostReadResponse> posts = postService.getPostsByCategory("nation");
        return HttpResponse.okBuild(posts);
    }

    @GetMapping("/free")
    public HttpResponse<List<PostReadResponse>> getFreePosts() {
        List<PostReadResponse> posts = postService.getPostsByCategory("free");
        return HttpResponse.okBuild(posts);
    }

    @GetMapping("/info")
    public HttpResponse<List<PostReadResponse>> getInfoPosts() {
        List<PostReadResponse> posts = postService.getInfoPosts();
        return HttpResponse.okBuild(posts);
    }

    @GetMapping("/{postId}")
    public HttpResponse<PostReadResponse> getPostById(@PathVariable Long postId) {
        PostReadResponse postDetail = postService.getPostById(postId);
        return HttpResponse.okBuild(postDetail);
    }

    @PostMapping("/create")
    public HttpResponse<String> createPost(@RequestBody MatchingPostCreateRequest request) {
        // Determine if it's an info post or regular post
        if (isInfoPost(request.getCategory())) {
            InfoPostCreateRequest infoRequest = InfoPostCreateRequest.builder()
                    .nickname(request.getNickname())
                    .title(request.getTitle())
                    .content(request.getContent())
                    .build();

            postService.createInfoPost(infoRequest);
        } else {
            postService.createMatchingPost(request);
        }

        return HttpResponse.okBuild("Post created successfully");
    }

    private boolean isInfoPost(String category) {
        return "info".equalsIgnoreCase(category);
    }

}
