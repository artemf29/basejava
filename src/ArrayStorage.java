/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null && resume.uuid.equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (uuid.equals(storage[i].uuid)) {
                storage[i] = null;
                while (storage[i + 1] != null && i + 1 < storage.length) {
                    storage[i] = storage[i + 1];
                    storage[i + 1] = null;
                    i++;
                }
                break;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count = 0;
        Resume[] withoutNull;
        for (Resume resume : storage) {
            if (resume != null) {
                count++;
            }
        }
        withoutNull = new Resume[count];
        for (int i = 0;i<withoutNull.length;i++){
            withoutNull[i] = storage[i];
        }
        return withoutNull;
    }

    int size() {
        return storage.length;
    }
}
