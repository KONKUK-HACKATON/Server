package com.KURUSH.KUFOREINER.matching;

import com.KURUSH.KUFOREINER.matching.domain.Matching;
import com.KURUSH.KUFOREINER.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingRepository extends JpaRepository<Matching, Long> {
    List<Matching> findByMember(Member member);
}
