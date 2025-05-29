package org.arai.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class JwtClaims {
    private String userId;
    private Instant issueAt;
    private Instant expiration;

}
