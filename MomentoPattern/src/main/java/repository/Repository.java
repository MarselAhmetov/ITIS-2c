package repository;

public interface Repository<T> {
    void create(T t);
    T read();
    void update(T t);
    void delete(T t);
}
