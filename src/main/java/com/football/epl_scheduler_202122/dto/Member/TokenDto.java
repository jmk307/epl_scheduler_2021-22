package com.football.epl_scheduler_202122.dto.Member;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    // accessToken
    private String token;

    // refreshToken
    private String refreshToken;
}
