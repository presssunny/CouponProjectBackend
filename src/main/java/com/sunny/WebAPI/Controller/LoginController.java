package com.sunny.WebAPI.Controller;

import com.sunny.LogIn.ClientSession;
import com.sunny.LogIn.LoginCredentials;
import com.sunny.Service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Login API", description = "Operation related to login service")
public class LoginController {
    private final Map<String, ClientSession> tokens;
    private final LoginService service;

    @Operation(summary = "Login to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Sorry, there is not such customer in the system"),
            @ApiResponse(responseCode = "500", description = "Sorry, check the details you inserted."),
            @ApiResponse(responseCode = "409", description = "Sorry, there is conflict system")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCredentials credentials) {
        String email = credentials.email();
        String password = credentials.password();

        String token = service.generateToken();
        ClientSession session = service.createSession(email, password, credentials.type());
        tokens.put(token, session);

        return ResponseEntity.ok(token);
    }
}
