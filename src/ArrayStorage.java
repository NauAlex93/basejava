import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;
    private Integer resumeId;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        if (storage.length == size) {
            System.out.println("Storage is full.");
            return;
        }
        if (returnResumeId(resume.uuid) == null) {
            if (resume != null) {
                storage[size++] = resume;
            }
        } else {
            System.out.println("Resume already in storage!");
        }
    }

    Resume get(String uuid) {
        if (returnResumeId(uuid) != null) {
            return storage[resumeId];
        }

        System.out.println("Resume is not in storage!");
        return null;
    }

    void update(Resume resume) {
        if (returnResumeId(resume.uuid) != null) {
            storage[resumeId] = resume;
        } else {
            System.out.println("Resume is not in storage!");
        }
    }

    void delete(String uuid) {
        if (returnResumeId(uuid) != null) {
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

    Integer returnResumeId(String uuid) {
        resumeId = null;

        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                resumeId = i;
                return resumeId;
            }
        }

        return resumeId;
    }
}
