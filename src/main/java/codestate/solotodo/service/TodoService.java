package codestate.solotodo.service;

import codestate.solotodo.entity.Todo;
import codestate.solotodo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) { this.todoRepository = todoRepository; }

    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo){
        Todo findTodo = findVerifiedTodo(todo.getId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(todoOrder -> findTodo.setTodoOrder(todoOrder));
        Optional.ofNullable(todo.isCompleted())
                .ifPresent(completed -> findTodo.setCompleted(completed));

        return todoRepository.save(findTodo);
    }

    public Todo findTodo(long todoId){
        return findVerifiedTodo(todoId);
    }

    public List<Todo> findTodos(){
        return todoRepository.findAll();
    }

    public void deleteTodo(Long todoId){
        Todo findTodo = findVerifiedTodo(todoId);
        todoRepository.delete(findTodo);
    }

    public void deleteTodos(){
        todoRepository.deleteAll();
    }

    private Todo findVerifiedTodo(long todoId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo findTodo = optionalTodo.orElseThrow(() -> new RuntimeException());

        return findTodo;
    }
}
