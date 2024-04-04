package uz.todo.service;

import uz.todo.bean.User;
import uz.todo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService implements BaseService<User>{

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }


    @Override
    public Integer create(User user) {
        if(alreadyExist(user.getUsername())) {
            return userRepository.create(user);
        }
        return 0;
    }

    private boolean alreadyExist(String username) {
        List<User> users = userRepository.showAll();
        for (User user : users) {
            if(user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer update(User user) {
        if (!alreadyExist(user.getUsername())) {
            return userRepository.update(user);

        }
        return 0;
    }

    @Override
    public Integer delete(Integer id) {
        return userRepository.delete(id);
    }

    @Override
    public User getById(Integer id) {
        Optional<User> user =  userRepository.getById(id);
        return user.orElse(null);
    }

    @Override
    public List<User> showAll() {
        return userRepository.showAll();
    }

}
