package com.football.epl_scheduler_202122.dto.Member;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String token;

    private String refreshToken;
}
