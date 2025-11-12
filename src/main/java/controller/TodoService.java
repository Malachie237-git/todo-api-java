package com.todoapp.todoapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoapp.todoapi.model.Todo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final String DB_FILE = "db.json";
    private final ObjectMapper mapper = new ObjectMapper();

    // 🟢 Récupérer tous les todos
    public List<Todo> getAllTodos() {
        try {
            File file = new File(DB_FILE);
            if (!file.exists()) return new ArrayList<>();
            return List.of(mapper.readValue(file, Todo[].class));
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // 🟢 Récupérer un todo par ID
    public Todo getTodoById(Long id) {
        return getAllTodos().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 🟢 Ajouter un nouveau todo (POST)
    public Todo addTodo(Todo todo) {
        List<Todo> todos = new ArrayList<>(getAllTodos());

        // Générer un nouvel ID
        long newId = todos.size() > 0
                ? todos.get(todos.size() - 1).getId() + 1
                : 1;

        todo.setId(newId);
        todos.add(todo);
        saveToFile(todos);
        return todo;
    }

    // 🟢 Modifier un todo existant (PUT)
    public Todo updateTodo(Long id, Todo updatedTodo) {
        List<Todo> todos = new ArrayList<>(getAllTodos());
        for (int i = 0; i < todos.size(); i++) {
            Todo current = todos.get(i);
            if (current.getId().equals(id)) {
                current.setTitle(updatedTodo.getTitle());
                current.setDescription(updatedTodo.getDescription());
                current.setCompleted(updatedTodo.isCompleted());
                saveToFile(todos);
                return current;
            }
        }
        return null;
    }

    // 🟢 Supprimer un todo (DELETE)
    public void deleteTodo(Long id) {
        List<Todo> todos = new ArrayList<>(getAllTodos());
        todos.removeIf(t -> t.getId().equals(id));
        saveToFile(todos);
    }

    // 🟢 Sauvegarder dans db.json
    private void saveToFile(List<Todo> todos) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DB_FILE), todos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
