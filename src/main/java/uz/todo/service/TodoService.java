package uz.todo.service;

import uz.todo.bean.Todo;
import uz.todo.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

public class TodoService implements BaseService<Todo> {

    private final TodoRepository todoRepository;

    public TodoService() {
        this.todoRepository = new TodoRepository();
    }

    @Override
    public Integer create(Todo todo) {
        return todoRepository.create(todo);
    }

    @Override
    public Integer update(Todo todo) {
        return todoRepository.update(todo);
    }

    @Override
    public Integer delete(Integer id) {
        return todoRepository.delete(id);
    }

    @Override
    public Todo getById(Integer id) {
        Optional<Todo> todo = todoRepository.getById(id);
        return todo.orElse(null);
    }
    @Override
    public List<Todo> showAll() {
        return todoRepository.showAll();
    }

    public List<Todo> showAll(Integer id) {
        return todoRepository.showAll(id);
    }
}
