package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int insertionPoint = Arrays.binarySearch(storage, 0, size, resume, Comparator.comparing(Resume::getUuid));
        if (insertionPoint >= 0) {
            System.out.println("Резюме с id " + resume.getUuid() + " уже существует.");
            return;
        }
        insertionPoint = -(insertionPoint + 1);
        if (insertionPoint < size) {
            System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
        }
        storage[insertionPoint] = resume;
        size++;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("No resume with id: " + resume.getUuid() + "were found for updating");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("No resume with id: " + uuid + "were found for deleting");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
