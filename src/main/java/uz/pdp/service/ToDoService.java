package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ToDoDTO;
import uz.pdp.entity.ToDo;
import uz.pdp.entity.User;
import uz.pdp.repository.TodoRepository;
import uz.pdp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    // CREATE
    public ToDo createTodo(ToDoDTO dto, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        ToDo todo = new ToDo();

        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(false);
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUser(user);

        return todoRepository.save(todo);
    }

    // GET ALL
    public List<ToDo> getAllTodos(String username) {
        return todoRepository.findAllByUser_Username(username);
    }

    // GET BY ID
    public ToDo getTodoById(Long id, String username) {

        return todoRepository.findByIdAndUser_Username(id, username)
                .orElseThrow(() ->
                        new RuntimeException("Todo not found"));
    }

    // UPDATE
    public ToDo updateTodo(Long id, ToDoDTO dto, String username) {

        ToDo todo = getTodoById(id, username);

        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(dto.isCompleted());

        return todoRepository.save(todo);
    }

    // DELETE
    public void deleteTodo(Long id, String username) {

        ToDo todo = getTodoById(id, username);

        todoRepository.delete(todo);
    }
}
