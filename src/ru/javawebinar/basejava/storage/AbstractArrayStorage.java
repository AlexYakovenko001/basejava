package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

abstract public class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(int index, Resume resume);

    protected abstract void deleteResume(int index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.toString());
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("There's no more space in the system for new resumes", resume.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertResume(index, resume);
            size++;
        }
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.toString());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteResume(index);
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
}
