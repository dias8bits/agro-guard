package guard.agro.controller;

import guard.agro.dto.request.LoginRequest;
import guard.agro.dto.response.LoginResponse;
import guard.agro.service.AuthService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    @ApiResponse(responseCode = "200", description = "Login successful")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        log.debug("request to login");

        return ResponseEntity.ok(authService.login(request));
    }

}