package repository;

public interface Repository<T> {
    void add(T t);
    T read();
    void update(T t);
    void delete(T t);
}
