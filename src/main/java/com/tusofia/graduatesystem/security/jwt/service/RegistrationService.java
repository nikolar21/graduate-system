package com.tusofia.graduatesystem.security.jwt.service;

import com.tusofia.graduatesystem.model.request.RegistrationRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<MessageResponse> userRegistration(RegistrationRequest signUpRequest);
}
