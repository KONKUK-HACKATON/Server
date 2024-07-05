package com.KURUSH.KUFOREINER.bookmark;

import com.KURUSH.KUFOREINER.member.Member;
import com.KURUSH.KUFOREINER.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Bookmark")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    // Getters and Setters
}