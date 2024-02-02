package com.authentication.autentication.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(
        name = "User",
        description = "The User API"
)
public class UserController {

    @Operation(
            summary = "API for test",
            description = "This API is used only for testing"
    )

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<String> greeting(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hello Yasser");
    }

}
