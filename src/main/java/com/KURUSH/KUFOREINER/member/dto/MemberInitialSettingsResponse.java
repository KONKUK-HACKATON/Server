package com.KURUSH.KUFOREINER.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInitialSettingsResponse {
    private String message;
    private MemberInitialSettingsDTO settings;

    public static MemberInitialSettingsResponse of(MemberInitialSettingsDTO settings, String message) {
        MemberInitialSettingsResponse response = new MemberInitialSettingsResponse();
        response.setSettings(settings);
        response.setMessage(message);
        return response;
    }
}