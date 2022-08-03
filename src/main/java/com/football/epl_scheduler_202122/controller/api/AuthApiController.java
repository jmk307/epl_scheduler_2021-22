package com.football.epl_scheduler_202122.controller.api;

import com.football.epl_scheduler_202122.domain.RefreshToken;
import com.football.epl_scheduler_202122.dto.Member.LoginDto;
import com.football.epl_scheduler_202122.dto.Member.TokenDto;
import com.football.epl_scheduler_202122.jwt.JwtFilter;
import com.football.epl_scheduler_202122.jwt.TokenProvider;
import com.football.epl_scheduler_202122.repository.RefreshTokenRepository;
import com.football.epl_scheduler_202122.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/authenticate")
public class AuthApiController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;

    // 로그인 및 토큰 발급
    @PostMapping
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String refreshToken = tokenProvider.createRefreshToken(authentication);
        RefreshToken token = RefreshToken.createToken(loginDto.getUsername(), refreshToken);

        refreshTokenRepository.findByUserId(loginDto.getUsername())
                .ifPresentOrElse(
                        tokenEntity -> tokenEntity.changeToken(refreshToken),
                        () -> refreshTokenRepository.save(RefreshToken.createToken(loginDto.getUsername(), refreshToken))
                );

        HttpHeaders httpHeaders = new HttpHeaders();

        String accessToken = tokenProvider.createToken(authentication);
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        return new ResponseEntity<>(new TokenDto(accessToken, refreshToken), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/accessToken")
    public TokenDto reissueAccessToken(@RequestParam String token) {
        return memberService.reissueAccessToken(token);
    }
}
