package uz.todo.resource;

import uz.todo.bean.ApiResponse;


public interface BaseResource<T> {
    ApiResponse create(T t);
    ApiResponse update(T t);
    ApiResponse delete(Integer id);
    ApiResponse getById(Integer id);
    ApiResponse showAll();
}
