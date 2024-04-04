package uz.todo;

import uz.todo.bean.ApiResponse;
import uz.todo.bean.Todo;
import uz.todo.bean.User;
import uz.todo.resource.TodoResource;
import uz.todo.resource.UserResource;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scannerInt = new Scanner(System.in);
    public static User session;
    private static final UserResource userResource =new UserResource();
    private static final TodoResource todoResource =new TodoResource();

    public static void main(String[] args) {
        runApp();
    }

    private static void runApp() {
        while(true) {
            System.out.println("WELCOME TO TODO APP :)");
            System.out.println("1.Sign up \n2.Sign in \n0.Exit");
            int choose = scannerInt.nextInt();
            switch (choose) {
                case 1: {
                    regisMenu();
                    break;
                }
                case 2: {
                    loginMenu();
                    break;
                }
                case 0: return;
                default:
                    System.out.println("choose 1 or 2 to enter app!");
            }
        }
    }


    private static void regisMenu() {
        System.out.println("SIGN UP!");
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter your password: ");
        String pass = scanner.next();
        User corr = new User(name, pass);
        ApiResponse apiResponse = userResource.create(corr);
        System.out.println(apiResponse.getData());
        int id = (int) apiResponse.getData();
        corr.setId(id);
        session = corr;
        mainMenu();
    }


    private static void loginMenu() {
        System.out.println("SIGN IN!");
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter your password: ");
        String pass = scanner.next();
        User corr = new User(name, pass);
        ApiResponse apiResponse = userResource.login(corr);
        int id = (int) apiResponse.getData();
        corr.setId(id);
//        System.out.println(apiResponse.getMessage());
        if (apiResponse.getCode().equals(200)) {
            session = corr;
            mainMenu();
        }

    }

    private static void mainMenu() {
        while (true) {
            System.out.println("""
                        MAIN MENU
                    1.User profile
                    2.Task panel
                    3.User settings
                    0.Log out
                    """);
            int choose = scannerInt.nextInt();
            switch (choose) {
                case 1:
                    userProfile();
                    break;
                case 2:
                    taskPanel();
                    break;
                case 3:
                    userSettings();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Unknown number choose again ):");
            }
        }
    }


    private static void userProfile() {
        System.out.println("USER PROFILE");
        System.out.println("Your id: " + session.getId());
        System.out.println("Your username: " + session.getUsername());
        ApiResponse apiResponse = todoResource.showAll(session.getId());
        List<Todo> todos = (List<Todo>) apiResponse.getData();
        System.out.println("Tasks: "+ todos.size());
        System.out.println("0.Exit");
        int choose = scannerInt.nextInt();
        if (choose==0) {
            mainMenu();
        }else {
            userProfile();
        }
    }

    private static void taskPanel() {
        while (true) {
        System.out.println("TASK PANEL!");
        System.out.println("""
                1.Create task\s
                2.Read task \s
                3.Update task\s
                4.Delete task\s
                5.Show all tasks\s
                0.Exit""");
        int choose = scannerInt.nextInt();
            switch (choose) {
                case 1:
                    createTask();
                    break;
                case 2:
                    readTask();
                    break;
                case 3:
                    updateTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    showAll();
                    break;
                case 0: return;
                default:
                    System.out.println("Unknown number choose again ):");
            }
        }
    }

    private static void userSettings() {
        while (true) {
            System.out.println("USER SETTINGS");
            System.out.println("""
                    1. Change username
                    2. Change password
                    3. Delete account
                    0. Exit
                    """);
            int choose = scannerInt.nextInt();
            switch (choose) {
                case 1:
                    changeUsername();
                    break;
                case 2:
                    changePassword();
                case 3:
                    deleteAccount();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Unknown number choose again ):");
            }
        }
    }



    //    Sub menus
    private static void createTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("CREATE TASK");

        System.out.print("Task name: ");
        String name = scanner.nextLine();

        System.out.print("Task description: ");
        String desc = scanner.nextLine();

        System.out.print("Task priority: ");
        String priority = scanner.next();
        int u_id = session.getId();
        ApiResponse apiResponse = todoResource.create(new Todo(name, desc, priority, u_id));
        System.out.println(apiResponse.getData());
        System.out.println(session.getId());
        if(apiResponse.getCode().equals(200)) {
            taskPanel();
        }
    }

    private static void readTask() {
        ApiResponse apiResponse = todoResource.showAll(session.getId());
        List<Todo> todos = (List<Todo>) apiResponse.getData();
        for (Todo todo : todos) {
            System.out.println(todo.getId()+ " => " + todo.getName());
        }
        System.out.print("Enter id: ");
        int id = scannerInt.nextInt();
        ApiResponse byId = todoResource.getById(id);
        Todo todo = (Todo) byId.getData();
        System.out.println(todo);
        if(byId.getCode().equals(200)) {
            taskPanel();
        }
    }

    private static void updateTask() {
        System.out.println("UPDATE TASK");
        ApiResponse apiResponse = todoResource.showAll(session.getId());
        List<Todo> todos = (List<Todo>) apiResponse.getData();
        for (Todo todo : todos) {
            System.out.println(todo.getId()+ " => " + todo.getName());
        }
        System.out.print("Enter id: ");
        int id = scannerInt.nextInt();
        ApiResponse byId = todoResource.getById(id);
        Todo todo = (Todo) byId.getData();
        if (todo != null) {
            System.out.println();
            System.out.print("Task name: ");
            String name = scanner.nextLine();

            System.out.print("Task description: ");
            String desc = scanner.nextLine();

            System.out.print("Task priority: ");
            String priority = scanner.next();
            int u_id = session.getId();
            ApiResponse update = todoResource.update(new Todo(name, desc, priority, u_id));
            System.out.println(update.getData());
            System.out.println(session.getId());
            if(update.getCode().equals(200)) {
                taskPanel();
            }
        }else {
            System.out.println("not found");
        }
    }

    private static void deleteTask() {
        System.out.println("DELETE TASK");
        ApiResponse apiResponse = todoResource.showAll(session.getId());
        List<Todo> todos = (List<Todo>) apiResponse.getData();
        for (Todo todo : todos) {
            System.out.println(todo.getId()+ " => " + todo.getName());
        }
        System.out.print("Enter id: ");
        int id = scannerInt.nextInt();
        ApiResponse byId = todoResource.delete(id);
        if(byId.getCode().equals(200)) {
            System.out.println(byId.getMessage());
        }else {
            System.out.println("not found");

        }
        taskPanel();
    }

    private static void showAll() {
        System.out.println("SHOW ALL TASKS");
        ApiResponse apiResponse = todoResource.showAll(session.getId());
        List<Todo> todos = (List<Todo>) apiResponse.getData();
        for (Todo todo : todos) {
            System.out.println(todo);
        }
        if(apiResponse.getCode().equals(200)) {
            taskPanel();
        }
    }

    private static void changeUsername() {
        System.out.println("CHANGE USERNAME");
        System.out.print("Enter your password: ");
        String pass = scanner.next();
        if (pass.equals(session.getPassword())) {
            System.out.print("Enter your new username: ");
            String username = scanner.next();
            session.setUsername(username);
            ApiResponse update = userResource.update(session);
            System.out.println(update.getMessage());
        }else {
            System.out.println("password don't match ): \n Try again");
        }
    }
    private static void changePassword() {
        System.out.println("CHANGE PASSWORD");
        System.out.print("Enter your password: ");
        String pass = scanner.next();
        if (pass.equals(session.getPassword())) {
            System.out.print("Enter your new password: ");
            String username = scanner.next();
            session.setUsername(username);
            ApiResponse update = userResource.update(session);
            System.out.println(update.getMessage());
        } else {
            System.out.println("password don't match ): \n Try again");
        }
    }
    private static void deleteAccount() {
        System.out.println("DELETE ACCOUNT");
        System.out.print("Enter your password: ");
        String pass = scanner.next();
        if (pass.equals(session.getPassword())) {
            System.out.print("Enter your new username: ");
            String username = scanner.next();
            session.setUsername(username);
            ApiResponse update = userResource.delete(session.getId());
            System.out.println(update.getMessage());
            session = null;
            System.out.print("\033[H\033[2J");
            System.out.flush();
            runApp();
        } else {
            System.out.println("password don't match ): \n Try again");
        }
    }

}