package uz.todo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private int id;
    private String name;
    private String desc;
    private String status;
    private int u_id;

    public Todo(String name, String desc, String status, int u_id) {
        this.name = name;
        this.desc = desc;
        this.status = status;
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "|" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", status='" + status + '\'' +
                ", u_id=" + u_id +
                "| \n";
    }
}
