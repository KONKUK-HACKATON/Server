package com.KURUSH.KUFOREINER.post.service;

import com.KURUSH.KUFOREINER.member.MemberRepository;
import com.KURUSH.KUFOREINER.member.domain.Member;
import com.KURUSH.KUFOREINER.member.exception.MemberExistException;
import com.KURUSH.KUFOREINER.member.exception.MemberNotExistException;
import com.KURUSH.KUFOREINER.post.PostRepository;
import com.KURUSH.KUFOREINER.post.domain.Post;
import com.KURUSH.KUFOREINER.post.dto.InfoPostCreateRequest;
import com.KURUSH.KUFOREINER.post.dto.MatchingPostCreateRequest;
import com.KURUSH.KUFOREINER.post.dto.PostReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

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

    public void createMatchingPost(MatchingPostCreateRequest request) {
        String userId = getUsernameBySecurityContext();

        // Member를 조회
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new MemberNotExistException());

        // Post를 생성하고 Member를 설정
        Post post = Post.builder()
                .nickname(request.getNickname())
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .isOpen(request.getIsOpen())
                .isInfo(request.getIsInfo() != null ? request.getIsInfo() : false)
                .member(member)  // Member를 설정
                .build();

        postRepository.save(post);
    }

    public void createInfoPost(InfoPostCreateRequest request) {
        Post post = Post.builder()
                .nickname(request.getNickname())
                .title(request.getTitle())
                .content(request.getContent())
                .isInfo(true) // Information board posts are always info
                .build();

        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        String loggedInUsername = getUsernameBySecurityContext();
        Member loggedInMember = memberRepository.findByUserId(loggedInUsername)
                .orElseThrow(MemberNotExistException::new);

        if (!post.getMember().getUserId().equals(loggedInMember.getUserId())) {
            throw new RuntimeException("You are not authorized to delete this post.");
        }

        postRepository.delete(post);
    }

    public String getUsernameBySecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getName();
    }

}
