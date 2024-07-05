package com.KURUSH.KUFOREINER.matching.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchingReadResponse {
    private Long matchingId;
    private String nickname;
    private String nation;
    private String gender;
    private String college;
    private String major;
    private Long memberId; // Adding memberId for reference
}
