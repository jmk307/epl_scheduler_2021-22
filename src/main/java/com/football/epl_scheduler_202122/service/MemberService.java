package com.football.epl_scheduler_202122.service;

import com.football.epl_scheduler_202122.domain.Authority;
import com.football.epl_scheduler_202122.domain.Member;
import com.football.epl_scheduler_202122.domain.RefreshToken;
import com.football.epl_scheduler_202122.dto.Member.MemberDto;
import com.football.epl_scheduler_202122.dto.Member.TokenDto;
import com.football.epl_scheduler_202122.exception.DuplicateMemberException;
import com.football.epl_scheduler_202122.jwt.TokenProvider;
import com.football.epl_scheduler_202122.repository.MemberRepository;
import com.football.epl_scheduler_202122.repository.RefreshTokenRepository;
import com.football.epl_scheduler_202122.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberDto signup(MemberDto memberDto) {
        if (memberRepository.findOneWithAuthoritiesByUsername(memberDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .nickname(memberDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return MemberDto.from(memberRepository.save(member));
    }

    @Transactional
    public TokenDto reissueAccessToken(String token) {
        String resolveToken = resolveToken(token);

        tokenProvider.validateToken(resolveToken);

        Authentication authentication = tokenProvider.getAuthentication(resolveToken);

        RefreshToken findTokenEntity = refreshTokenRepository.findByUserId(authentication.getName())
                .orElseThrow(() -> new RuntimeException("not find refresh Token"));

        if(!resolveToken.equals(findTokenEntity.getToken())) {
            throw new RuntimeException("not equals refresh token");
        }

        String newToken = tokenProvider.createRefreshToken(authentication);
        findTokenEntity.changeToken(newToken);

        return TokenDto.builder()
                .token("Bearer-" + tokenProvider.createToken(authentication))
                .refreshToken("Bearer-" + newToken)
                .build();
    }

    private String resolveToken(String token) {
        if (token.startsWith("Bearer-")) {
            return token.substring(7);
        }
        throw new RuntimeException("not valid refresh token!!");
    }

    @Transactional(readOnly = true)
    public MemberDto getUserWithAuthorities(String username) {
        return MemberDto.from(memberRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)
    public MemberDto getMyUserWithAuthorities() {
        return MemberDto.from(SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByUsername).orElse(null));
    }
}
