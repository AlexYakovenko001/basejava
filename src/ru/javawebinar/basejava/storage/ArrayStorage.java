package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        int index = getIndex(r.toString());
        if (size >= storage.length) {
            System.out.println("There's no more space in the system for new resumes");
        } else if (index != -1) {
            System.out.println("Such a resume " + r.getUuid() + " already exists in the system");
        } else {
            storage[size++] = r;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.toString());
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("No resume with id: " + resume.getUuid() + "were found for updating");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[--size];
            storage[size] = null;
        } else {
            System.out.println("No resume with id: " + uuid + "were found for deleting");
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
