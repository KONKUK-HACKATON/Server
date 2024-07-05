package com.KURUSH.KUFOREINER.member;

import com.KURUSH.KUFOREINER.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String username);

    Optional<Member> findByNickname(String nickname);


}
