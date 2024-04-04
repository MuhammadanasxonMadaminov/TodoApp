package uz.todo.repository;

import uz.todo.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements BaseRepository<User> {
    @Override
    public Integer create(User user) {
        try {
            String sql = "insert into users (username,password) values (?,?) returning id;";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new RuntimeException("No ID returned from the insert statement");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Integer update(User user) {
        try {
            String sql = "update users set username=?, password=? where id=?  returning id;";
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setInt(3,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer delete(Integer id) {
        try {
            String sql = "delete from users where id=?  returning id;";
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getById(Integer id) {
        try {
            String sql = "select * from users where id = ?;";
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id2 =resultSet.getInt(1);
                String username =resultSet.getString(2);
                String password = resultSet.getString(3);
                return Optional.of(new User(id2,username,password));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> showAll() {
        List<User> users = new ArrayList<>();
        try {
            String sql = "select * from users;";
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {
                int id2 =resultSet.getInt(1);
                String username =resultSet.getString(2);
                String password = resultSet.getString(3);
                users.add(new User(id2,username,password));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }



}
