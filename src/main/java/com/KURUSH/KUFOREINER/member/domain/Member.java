package com.KURUSH.KUFOREINER.member.domain;


import com.KURUSH.KUFOREINER.bookmark.Bookmark;
import com.KURUSH.KUFOREINER.comment.Comment;
import com.KURUSH.KUFOREINER.matching.Matching;
import com.KURUSH.KUFOREINER.post.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Member")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String colleage;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private Long studentnumber;

    @Column(nullable = false)
    private String nation;

    @Column(nullable = false)
    private String language;

    @Column
    private String singularity;

    // Getters and Setters

    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    @OneToMany(mappedBy = "member")
    private List<Matching> matchings;

    @OneToMany(mappedBy = "member")
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;
}
