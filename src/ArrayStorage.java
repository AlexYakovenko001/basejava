import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[--size];
                break;
            }
        }
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
