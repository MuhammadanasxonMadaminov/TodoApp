package uz.todo.service;

import java.util.List;

public interface BaseService<T> {
    Integer create(T t);
    Integer update(T t);
    Integer delete(Integer id);
    T getById(Integer id);
    List<T> showAll();
}
