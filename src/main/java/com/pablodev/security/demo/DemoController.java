// DemoController.java
package com.pablodev.security.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping("/admin")
    public ResponseEntity<String> sayHelloAdmin() {
        return ResponseEntity.ok("Hello from admin endpoint");
    }

    @GetMapping("/user")
    public ResponseEntity<String> sayHelloUser() {
        return ResponseEntity.ok("Hello from user endpoint");
    }

    @GetMapping("/public")
    public ResponseEntity<String> sayHelloPublic() {
        return ResponseEntity.ok("Hello from public endpoint");
    }

    @GetMapping("/rolecheck")
    public ResponseEntity<String> sayHelloRoleCheck() {
        return ResponseEntity.ok("Hello from rolecheck endpoint (accessible by ROLE_USER or ROLE_ADMIN)"); // Updated role check message
    }
}
