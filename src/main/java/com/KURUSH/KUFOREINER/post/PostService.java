package com.KURUSH.KUFOREINER.post;

import com.KURUSH.KUFOREINER.post.domain.Post;
import com.KURUSH.KUFOREINER.post.dto.PostReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostReadResponse> getPostsByCategory(String category) {
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream()
                .map(this::convertToReadResponse)
                .collect(Collectors.toList());
    }

    public List<PostReadResponse> getInfoPosts() {
        List<Post> posts = postRepository.findByIsInfo(true);
        return posts.stream()
                .map(this::convertToReadResponse)
                .collect(Collectors.toList());
    }

    public PostReadResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        return convertToReadResponse(post);
    }

    private PostReadResponse convertToReadResponse(Post post) {
        return PostReadResponse.builder()
                .postId(post.getPostId())
                .nickname(post.getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .isOpen(post.getIsOpen())
                .isInfo(post.getIsInfo())
                .category(post.getCategory())
//                .member(MemberDTO.builder()
//                        .memberId(post.getMember().getMemberId())
//                        .username(post.getMember().getUsername())
//                        .build())
                .build();
    }
}
