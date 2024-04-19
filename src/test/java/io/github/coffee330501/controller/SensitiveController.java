package io.github.coffee330501.controller;

import io.github.coffee330501.entity.SensitiveEntity;
import io.github.coffee330501.mock.MockHelper;
import io.github.coffee330501.sensitive.annotation.IgnoreSensitive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SensitiveController {
    @GetMapping("sensitive")
    public ResponseEntity<SensitiveEntity> sensitive() {
        return ResponseEntity.ok(MockHelper.sensitive());
    }

    @IgnoreSensitive
    @GetMapping("ignore/method")
    public ResponseEntity<SensitiveEntity> skipSensitive() {
        return ResponseEntity.ok(MockHelper.sensitive());
    }
}
