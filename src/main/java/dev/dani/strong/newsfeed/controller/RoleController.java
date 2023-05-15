package dev.dani.strong.newsfeed.controller;

import dev.dani.strong.newsfeed.model.dto.request.role.CreateRoleRequest;
import dev.dani.strong.newsfeed.model.dto.request.role.UpdateRoleRequest;
import dev.dani.strong.newsfeed.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody @Valid CreateRoleRequest createRoleRequest) {
        return new ResponseEntity<>(roleService.create(createRoleRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(roleService.fetchById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getByRoleName(@RequestParam(name = "role", required = false) Optional<String> roleName) {
        String role = roleName.orElse("ROLE_USER");
        return new ResponseEntity<>(roleService.fetchByRoleName(role), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(roleService.fetchAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id,
                                        @RequestBody @Valid UpdateRoleRequest updateRequest) {
        return new ResponseEntity<>(roleService.updateById(id, updateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(roleService.deleteById(id), HttpStatus.OK);
    }

}
