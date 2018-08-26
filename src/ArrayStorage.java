import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        if (storage.length == size) {
            System.out.println("Storage is full.");
            return;
        }
        if (returnResumeId(resume.uuid) == -1) {
            storage[size++] = resume;
        } else {
            System.out.println("Resume already in storage!");
        }
    }

    Resume get(String uuid) {
        int resumeId = returnResumeId(uuid);

        if (resumeId != -1) {
            return storage[resumeId];
        }

        System.out.println("Resume is not in storage!");
        return null;
    }

    void update(Resume resume) {
        int resumeId = returnResumeId(resume.uuid);

        if (resumeId != -1) {
            storage[resumeId] = resume;
        } else {
            System.out.println("Resume is not in storage!");
        }
    }

    void delete(String uuid) {
        int resumeId = returnResumeId(uuid);

        if (resumeId != -1) {
            storage[resumeId] = storage[--size];
            storage[size] = null;
        } else {
            System.out.println("Resume is not in storage!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private int returnResumeId(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }

        return -1;
    }
}
