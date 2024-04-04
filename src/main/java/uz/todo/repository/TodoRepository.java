package uz.todo.repository;

import uz.todo.Main;
import uz.todo.bean.Todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoRepository implements BaseRepository<Todo> {
    @Override
    public Integer create(Todo todo) {
        try {
            String sql = "insert into todos (name,description,status,u_id) values (?,?,?,?) returning id;";
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, todo.getName());
            preparedStatement.setString(2, todo.getDesc());
            preparedStatement.setString(3, todo.getStatus());
            preparedStatement.setInt(4, Main.session.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Integer update(Todo todo) {
        try {
            String sql = "update todos set name=?, description=?, status=?, u_id=? where id=?;";
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1,todo.getName());
            preparedStatement.setString(2,todo.getDesc());
            preparedStatement.setString(3,todo.getStatus());
            preparedStatement.setInt(4,todo.getU_id());
            preparedStatement.setInt(5,todo.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        try {
            String sql = "delete from todos where id=?;";
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Optional<Todo> getById(Integer id) {
        try {
            String sql = "select * from todos where id = ?;";
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id2 =resultSet.getInt(1);
                String name =resultSet.getString(2);
                String desc = resultSet.getString(3);
                String status = resultSet.getString(4);
                int u_id = resultSet.getInt(5);
                return Optional.of(new Todo(id2,name,desc,status,u_id));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Todo> showAll() {
        List<Todo> todos = new ArrayList<>();
        try {
            String sql = "select * from todos;";
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {
                int id2 =resultSet.getInt(1);
                String name =resultSet.getString(2);
                String desc = resultSet.getString(3);
                String status = resultSet.getString(4);
                int u_id = resultSet.getInt(5);
                todos.add(new Todo(id2,name,desc,status,u_id));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return todos;
    }

    public List<Todo> showAll(Integer id) {
        List<Todo> todos = new ArrayList<>();
        try {
            String sql = "select * from todos where u_id =?;";
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {
                int id2 =resultSet.getInt(1);
                String name =resultSet.getString(2);
                String desc = resultSet.getString(3);
                String status = resultSet.getString(4);
                int u_id = resultSet.getInt(5);
                todos.add(new Todo(id2,name,desc,status,u_id));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return todos;
    }
}
