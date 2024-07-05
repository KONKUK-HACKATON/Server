package com.KURUSH.KUFOREINER.post;


import com.KURUSH.KUFOREINER.bookmark.Bookmark;
import com.KURUSH.KUFOREINER.comment.Comment;
import com.KURUSH.KUFOREINER.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Post")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isOpen;

    @Column(nullable = false)
    private Boolean isInfo;

    @Column(nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    // Getters and Setters

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<Bookmark> bookmarks;
}
