package codestate.solotodo.mapper;

import codestate.solotodo.dto.TodoPatchDto;
import codestate.solotodo.dto.TodoPostDto;
import codestate.solotodo.dto.TodoResponseDto;
import codestate.solotodo.entity.Todo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface TodoMapper {
    Todo TodoPostDtoToTodo(TodoPostDto todoPostDto);
    Todo TodoPatchDtoToTodo(TodoPatchDto todoPatchDto);
    TodoResponseDto TodoToTodoResponseDto(Todo todo);
}
