package com.critiqo.critiqo_backend.controller.auth;

import com.critiqo.critiqo_backend.request.auth.AuthenticationRequest;
import com.critiqo.critiqo_backend.response.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.prefix}/auth")
public class AuthController {

    @PostMapping(value = "/authentication")
    private ResponseEntity<ApiResponse> authentication(@RequestBody AuthenticationRequest authenticationRequest){

    }


}
