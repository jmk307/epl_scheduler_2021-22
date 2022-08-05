package com.football.epl_scheduler_202122.dto.Member;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestDto {

    private String refreshToken;
}
