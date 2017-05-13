package persistance;

public interface CrudService<T, ID> {
    void add(T entidad);
    void update(T entidad);
    void remove(T entidad);
    T findById(ID id);
    Iterable<T> findAll();

}
