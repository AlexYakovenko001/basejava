package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

abstract public class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("No resume with id: " + uuid + "were found");
        return null;
    }
}
