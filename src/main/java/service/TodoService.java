package com.todoapp.todoapi.service;

import com.todoapp.todoapi.model.Todo;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TodoService {

    private final Map<Long, Todo> todos = new HashMap<>();
    private Long currentId = 1L;

    public List<Todo> getAllTodos() {
        return new ArrayList<>(todos.values());
    }

    public Todo getTodoById(Long id) {
        return todos.get(id);
    }

    public Todo addTodo(Todo todo) {
        todo.setId(currentId++);
        todos.put(todo.getId(), todo);
        return todo;
    }

    public Todo updateTodo(Long id, Todo updated) {
        Todo existing = todos.get(id);
        if (existing != null) {
            existing.setTitle(updated.getTitle());
            existing.setDescription(updated.getDescription());
            existing.setCompleted(updated.isCompleted());
        }
        return existing;
    }

    public void deleteTodo(Long id) {
        todos.remove(id);
    }
}
