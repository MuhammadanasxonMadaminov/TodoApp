package uz.todo.resource;

import uz.todo.bean.ApiResponse;
import uz.todo.bean.Todo;
import uz.todo.service.TodoService;

import java.util.List;

public class TodoResource implements BaseResource<Todo>{

    private final TodoService todoService;

    public TodoResource() {
        this.todoService = new TodoService();
    }

    @Override
    public ApiResponse create(Todo todo) {
        try {
            if (todo== null) {
                return new ApiResponse(400,"Failed to create todo", null);
            }
            Integer id = todoService.create(todo);
            if (id != null) {
                return new ApiResponse(200,"Todo successfully created!", id);
            }else {
                return new ApiResponse(500,"Internal Server Error: failed to create todo", null);
            }
        } catch (Exception e) {
            return new ApiResponse(501,"Internal Server Error: failed to create todo ee", e.getMessage());
        }
    }

    @Override
    public ApiResponse update(Todo todo) {
        try {
            if (todo== null) {
                return new ApiResponse(400,"Failed to update todo", null);
            }
            Integer id = todoService.update(todo);
            if (id != null) {
                return new ApiResponse(200,"Todo successfully updated!", id);
            }else {
                return new ApiResponse(500,"Internal Server Error: failed to update todo", null);
            }
        } catch (Exception e) {
            return new ApiResponse(501,"Internal Server Error: failed to update todo ee", e.getMessage());
        }
    }

    @Override
    public ApiResponse delete(Integer id) {
        try {
            Integer delete = todoService.delete(id);
            if (delete != null) {
                return new ApiResponse(200, "Todo deleted successfully", delete);
            } else {
                return new ApiResponse(404, "Todo not found or failed to delete", null);
            }
        } catch (Exception e) {
            return new ApiResponse(500, "Internal Server Error: Failed to delete todo ee", null);
        }
    }

    @Override
    public ApiResponse getById(Integer id) {
        try{
            Todo todo = todoService.getById(id);
            if (todo != null) {
                return new ApiResponse(200, "Todo found successfully", todo);
            } else {
                return new ApiResponse(404, "Todo not found or failed to get it", null);
            }
        } catch (Exception e) {
            return new ApiResponse(500, "Internal Server Error: Failed to get todo ee", null);
        }
    }

    @Override
    public ApiResponse showAll() {
        try{
            List<Todo> todos = todoService.showAll();
            if (todos != null) {
                return new ApiResponse(200, "Todo found successfully", todos);
            } else {
                return new ApiResponse(404, "Todo not found or failed to get it", null);
            }
        } catch (Exception e) {
            return new ApiResponse(500, "Internal Server Error: Failed to get todo ee", null);
        }
    }

    public ApiResponse showAll(Integer id) {
        try{
            List<Todo> todos = todoService.showAll(id);
            if (todos != null) {
                return new ApiResponse(200, "Todo found successfully", todos);
            } else {
                return new ApiResponse(404, "Todo not found or failed to get it", null);
            }
        } catch (Exception e) {
            return new ApiResponse(500, "Internal Server Error: Failed to get todo ee", null);
        }
    }
}
