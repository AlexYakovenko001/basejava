package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (!isResumePresent(r.toString()) && size < storage.length) {
            storage[size++] = r;
        } else {
            System.out.println("Resume " + r + " already exists in the system");
        }
    }

    public void update(Resume resume) {
        int index = findResume(resume.toString());
        if (index != -1) {
            storage[index].setUuid(storage[index].getUuid() + " updated");
        }
    }

    public Resume get(String uuid) {
        if (!uuid.equals("dummy")) {
            int index = findResume(uuid);
            if (index != -1) {
                return storage[index];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int index = findResume(uuid);
        if (index != -1) {
            storage[index] = storage[--size];
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private boolean isResumePresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private int findResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        System.out.println("Error: resume " + uuid + " not found in the system");
        return -1;
    }
}
