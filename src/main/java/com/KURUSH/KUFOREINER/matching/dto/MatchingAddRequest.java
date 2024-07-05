package com.KURUSH.KUFOREINER.matching.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchingAddRequest {
    private String nickname;
    private String nation;
    private String gender;
    private String colleage;
    private String major;
}
