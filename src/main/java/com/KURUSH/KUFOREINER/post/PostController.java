package com.KURUSH.KUFOREINER.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/communities")
@Slf4j
public class PostController {
    private final PostService postService;

    @GetMapping("/study")
    public List<PostDTO> getStudyPosts() {
        return postService.getPostsByCategory("study");
    }

    @GetMapping("/lifestyle")
    public List<PostDTO> getLifestylePosts() {
        return postService.getPostsByCategory("lifestyle");
    }

    @GetMapping("/nation")
    public List<PostDTO> getNationPosts() {
        return postService.getPostsByCategory("nation");
    }

    @GetMapping("/free")
    public List<PostDTO> getFreePosts() {
        return postService.getPostsByCategory("free");
    }

    @GetMapping("/info")
    public List<PostDTO> getInfoPosts() {
        return postService.getInfoPosts();
    }


}
