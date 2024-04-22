package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void insertResume(int index, Resume resume) {
        int insertionPoint = -(index + 1);
        if (insertionPoint < size) {
            System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
        }
        storage[insertionPoint] = resume;
    }

    public void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size - 1] = null;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
