package com.kamyczki.auth.feign;

import com.kamyczki.commons.security.UserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(path = "api/auth", name="auth-service")
public interface AuthFeignClient {

    @PostMapping("validate-token")
    UserDetails validateToken(String token);
}
