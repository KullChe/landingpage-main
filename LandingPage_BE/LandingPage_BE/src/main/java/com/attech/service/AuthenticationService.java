package com.attech.service;

import com.attech.contain.AppConst;
import com.attech.model.dto.ResponseObject;
import com.attech.model.dto.authen.AuthenticationRequest;
import com.attech.model.dto.authen.AuthenticationResponse;
import com.attech.model.dto.authen.RegisterRequest;
import com.attech.model.entity.User;
import com.attech.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseObject register(RegisterRequest request) {
        ResponseObject responseObject = new ResponseObject();

        if (!repository.existsByUserNameOrEmail(request.getUsername(), request.getEmail()) && request.getConfirmPass().equals(request.getPassword())) {

            User user = User.builder()
                    .userName(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(1L)
                    .status(true)
                    .createdDate(new Date())
                    .build();

            repository.save(user);

            String jwtToken = jwtService.generateToken(user);

            AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();

            responseObject = ResponseObject.builder()
                    .resCode(AppConst.SUCCESS_CODE)
                    .resDesc(AppConst.SUCCESS_DESC)
                    .resData(authenticationResponse)
                    .build();
        } else {
            responseObject.setResCode(AppConst.FAIL_CODE);
            responseObject.setResDesc(AppConst.FAIL_DESC);
            responseObject.setResData(null);
        }

        return responseObject;
    }

    public ResponseObject authenticate(AuthenticationRequest request) {
        ResponseObject responseObject = new ResponseObject();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPassword()
                    )
            );
            User user = repository.findByUserName(request.getUserName());

            if (user != null) {

                String jwtToken = jwtService.generateToken(user);
                AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .build();

                responseObject = ResponseObject.builder()
                        .resCode(AppConst.SUCCESS_CODE)
                        .resDesc(AppConst.SUCCESS_DESC)
                        .resData(authenticationResponse)
                        .build();
            }
        } catch (Exception e) {
            responseObject.setResCode(AppConst.FAIL_CODE);
            responseObject.setResDesc(AppConst.FAIL_DESC + e.toString());
            responseObject.setResData(null);
        }

        return responseObject;
    }
}
