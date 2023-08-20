package me.beary.jwt.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Collection;

@RequestMapping("users")
@RestController
public class UserController {
    final private JwtEncoder encoder;

    public UserController(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping("login")
    public String login() {
        Instant now = Instant.now();
        final long expiry = 36000L;
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .id("ThisIsUserId")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .claim("scope", "USER")
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @PostMapping("protect")
    public String protect(Authentication authentication) {
        Jwt principal = (Jwt) authentication.getPrincipal();
        String id = principal.getId();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println(authorities);
        System.out.println(id);
        return "protect data";
    }
}
