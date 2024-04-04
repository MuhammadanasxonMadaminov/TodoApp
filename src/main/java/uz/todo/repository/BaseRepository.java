package uz.todo.repository;

import uz.todo.properties.DbProps;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    String url = DbProps.get("db.url");
    String username = DbProps.get("db.username");
    String password = DbProps.get("db.password");

     Integer create(T t);
     Integer update(T t);
     Integer delete(Integer id);
     Optional<T> getById(Integer id);
     List<T> showAll();

}
