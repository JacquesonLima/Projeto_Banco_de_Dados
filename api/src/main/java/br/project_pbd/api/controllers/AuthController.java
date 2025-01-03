package br.project_pbd.api.controllers;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.project_pbd.api.domain.user.User;
import br.project_pbd.api.dto.LoginRequestDTO;
import br.project_pbd.api.dto.RegisterRequestDTO;
import br.project_pbd.api.dto.ResponseDTO;
import br.project_pbd.api.infra.security.TokenService;
import br.project_pbd.api.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  @SuppressWarnings("rawtypes")
  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginRequestDTO body) {
    User user = this.repository.findByUsername(body.username())
        .orElseThrow(() -> new RuntimeException("User not found"));
    if (passwordEncoder.matches(body.password(), user.getPassword())) {
      String token = this.tokenService.generateToken(user);
      return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token));
    }
    return ResponseEntity.badRequest().body("Usuário ou senha inválidos");
  }

  @SuppressWarnings("rawtypes")
  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
    Optional<User> user = this.repository.findByUsername(body.username());

    if (user.isEmpty()) {
      User newUser = new User();
      newUser.setPassword(passwordEncoder.encode(body.password()));
      newUser.setUsername(body.username());
      this.repository.save(newUser);

      String token = this.tokenService.generateToken(newUser);
      return ResponseEntity.ok(new ResponseDTO(newUser.getUsername(), token));
    }
    return ResponseEntity.badRequest().body("Usuário já cadastrado.");
  }
}