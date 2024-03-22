package src.ru.javawebinar.basejava.storage;

import src.ru.javawebinar.basejava.model.Resume;

public interface Storage {

    void clear();

    void save(Resume r);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    int size();
}
