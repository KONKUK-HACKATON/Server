package com.KURUSH.KUFOREINER.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostDTO> getPostsByCategory(String category) {
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PostDTO> getInfoPosts() {
        List<Post> posts = postRepository.findByIsInfo(true);
        return posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PostDTO convertToDTO(Post post) {
        return PostDTO.builder()
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
