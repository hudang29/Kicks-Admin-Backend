package com.poly.admin.utils;

import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.poly.admin.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.nimbusds.jose.*;

import java.util.Date;

@Component
public class JwtUtil {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private static final long EXPIRATION_TIME = 7200000; // 1 ngày

    public String generateToken(String username, String role) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .claim("role", role)
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String extractExistsEmail(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            boolean existsEmail = employeeRepo.existsByEmail(signedJWT.getJWTClaimsSet().getSubject());
            if (!existsEmail) {
                return null;
            }
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (Exception e) {
            return null;
        }
    }
    public String getRole(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getClaim("role").toString();
        } catch (Exception e){
            return null;
        }
    }

    public boolean validateToken(String token, String email) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET_KEY);
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            return signedJWT.verify(verifier)
                    && expirationTime.after(new Date())
                    && email.equals(extractExistsEmail(token))
                    && employeeRepo.existsByEmail(email);
        } catch (Exception e) {
            return false;
        }
    }
}
