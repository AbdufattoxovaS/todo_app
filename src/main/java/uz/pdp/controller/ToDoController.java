package uz.pdp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ToDoDTO;
import uz.pdp.entity.ToDo;
import uz.pdp.service.ToDoService;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService todoService;

    @PostMapping
    public ResponseEntity<ToDo> createTodo(
            @RequestBody ToDoDTO todoDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(todoService.createTodo(todoDto, userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<ToDo> getAllTodos(
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(
                (ToDo) todoService.getAllTodos(userDetails.getUsername()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getTodoById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(
                todoService.getTodoById(id, userDetails.getUsername()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateTodo(
            @PathVariable Long id,
            @RequestBody ToDoDTO todoDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(
                todoService.updateTodo(id, todoDto, userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        todoService.deleteTodo(id, userDetails.getUsername());

        return ResponseEntity.noContent().build();
    }
}

