package com.KURUSH.KUFOREINER.matching;

import com.KURUSH.KUFOREINER.member.domain.Member;
import jakarta.persistence.Column;
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
@Table(name = "Matching")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String nation;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String major;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    // Getters and Setters
}