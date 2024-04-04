package uz.todo.resource;

import uz.todo.bean.ApiResponse;
import uz.todo.bean.User;
import uz.todo.service.UserService;

import java.util.List;

public class UserResource implements BaseResource<User>{
    private final UserService userService;

    public UserResource () {
        this.userService = new UserService();
    }

    @Override
    public ApiResponse create(User user) {
        try {
            if (user== null) {
                return new ApiResponse(400,"Failed to create user", null);
            }
            Integer id = userService.create(user);
            if (id != null) {
                return new ApiResponse(200,"User successfully created!", id);
            } else {
                return new ApiResponse(500,"Internal Server Error: failed to create user", null);
            }
        } catch (Exception e) {
            return new ApiResponse(501,"Internal Server Error: failed to create user ee", e.getMessage());
        }
    }

    @Override
    public ApiResponse update(User user) {
        try {
            if (user== null) {
                return new ApiResponse(400,"Failed to update user", null);
            }
            Integer id = userService.create(user);
            if (id != null) {
                return new ApiResponse(201,"User successfully updated!", id);
            }else {
                return new ApiResponse(500,"Internal Server Error: failed to update user", null);
            }
        } catch (Exception e) {
            return new ApiResponse(501,"Internal Server Error: failed to update user ee", e.getMessage());
        }
    }

    @Override
    public ApiResponse delete(Integer id) {
        try {
            Integer delete = userService.delete(id);
            if (delete != null) {
                return new ApiResponse(200, "User deleted successfully", delete);
            } else {
                return new ApiResponse(404, "User not found or failed to delete", null);
            }
        } catch (Exception e) {
            return new ApiResponse(500, "Internal Server Error: Failed to delete user ee", null);
        }
    }

    @Override
    public ApiResponse getById(Integer id) {
        try{
            User user = userService.getById(id);
            if (user != null) {
                return new ApiResponse(200, "User found successfully", user);
            } else {
                return new ApiResponse(404, "User not found or failed to get it", null);
            }
        } catch (Exception e) {
            return new ApiResponse(500, "Internal Server Error: Failed to get user ee", null);
        }
    }

    @Override
    public ApiResponse showAll() {
        try{
            List<User> users = userService.showAll();
            if (users != null) {
                return new ApiResponse(200, "User found successfully", users);
            } else {
                return new ApiResponse(404, "User not found or failed to get it", null);
            }
        } catch (Exception e) {
            return new ApiResponse(500, "Internal Server Error: Failed to get user ee", null);
        }
    }

    public ApiResponse login(User user) {
        try {
            List<User> users = userService.showAll();
            for (User u : users) {
                if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                    return new ApiResponse(200, "User found successfully", u.getId());
                }
            }
            return new ApiResponse(404, "User not found or failed to get it", user);
        } catch (Exception e) {
            return new ApiResponse(404, "User not found or failed to get it ee", null);

        }
    }
}
