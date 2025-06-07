package org.arai.Model.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class JwtClaims {
    private String userId;
    private Instant issueAt;
    private Instant expiration;
    private String ip_address;
    private String user_agent;

}
