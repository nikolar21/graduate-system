package com.tusofia.scienceblog.security.jwt.service;

import com.tusofia.scienceblog.model.request.SignupRequest;
import com.tusofia.scienceblog.model.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<MessageResponse> userRegistration(SignupRequest signUpRequest);
}
