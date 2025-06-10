package org.arai.Model.JwtClaim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@RequiredArgsConstructor
public class JwtClaims {
    private final String userId;
    private final Instant issueAt;
    private final Instant expiration;
    private String ip_address;
    private String user_agent;

}
