package Model;

import java.util.List;

public interface CrudOperations<Entity> {
    void create(List<Entity> entityList);
    List<Entity> readAll();
    void displayAll(List<Entity> entityList);
    void update(List<Entity> entityList);
    void delete(List<Entity> entityList);
}
