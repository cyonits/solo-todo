package codestate.solotodo.controller;

import codestate.solotodo.dto.TodoPatchDto;
import codestate.solotodo.dto.TodoPostDto;
import codestate.solotodo.dto.TodoResponseDto;
import codestate.solotodo.entity.Todo;
import codestate.solotodo.mapper.TodoMapper;
import codestate.solotodo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/")
public class TodoController {
    private final static String COMMON_URL = "http://localhost:8080/";
    private final TodoMapper mapper;
    private final TodoService todoService;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }


    @PostMapping
    public ResponseEntity postTodo(@RequestBody TodoPostDto todoPostDto) {
        Todo todo = mapper.TodoPostDtoToTodo(todoPostDto);
        todoService.createTodo(todo);

        TodoResponseDto response = mapper.TodoToTodoResponseDto(todo);
        response.setUrl(COMMON_URL + todo.getId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") long todoId,
                          @RequestBody TodoPatchDto todoPatchDto) {
        todoPatchDto.setId(todoId);
        Todo todo = todoService.updateTodo(mapper.TodoPatchDtoToTodo(todoPatchDto));

        TodoResponseDto response = mapper.TodoToTodoResponseDto(todo);
        response.setUrl(COMMON_URL + todo.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") long todoId) {
        Todo todo = todoService.findTodo(todoId);

        TodoResponseDto response = mapper.TodoToTodoResponseDto(todo);
        response.setUrl(COMMON_URL + todo.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos() {
        List<Todo> todos = todoService.findTodos();

        List<TodoResponseDto> response =
                todos.stream()
                        .map(todo -> mapper.TodoToTodoResponseDto(todo))
                        .peek(todoResponseDto -> todoResponseDto.setUrl(COMMON_URL+todoResponseDto.getId()))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{todo-id}")
    public void deleteTodo(@PathVariable("todo-id") long todoId) {
        todoService.deleteTodo(todoId);
    }

    @DeleteMapping
    public void deleteTodos() {
        todoService.deleteTodos();
    }
}
