package ru.javawebinar.basejava.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume with such id: " + uuid + "already exists", uuid);
    }
}
