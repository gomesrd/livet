package br.com.livet.application.controller.user;

import br.com.livet.domain.model.user.CreateUserRequest;
import br.com.livet.domain.port.user.UserServicePort;
import br.com.livet.domain.service.openAi.OpenAiService;
import br.com.livet.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServicePort userServicePort;
    private final OpenAiService openAiService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CreateUserRequest user) {
        return ResponseEntity.ok(userServicePort.create(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable UUID id, @RequestBody User user) {
        return ResponseEntity.ok(userServicePort.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userServicePort.findByIdOrElseThrow(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userServicePort.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<ChatResponse> search() {
        return ResponseEntity.ok(openAiService.getPromptResponse());
    }
}
