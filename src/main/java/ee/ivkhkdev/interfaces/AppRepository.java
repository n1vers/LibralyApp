package ee.ivkhkdev.interfaces;

import java.util.List;

public interface AppRepository<T> {
    void save(T entity);
    void saveAll(List<T> entities);
    List<T> load();

}
