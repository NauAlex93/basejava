import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (storage.length == size) {
            System.out.println("Storage is full.");
            return;
        }
        if (returnResumeId(resume.getUuid()) == -1) {
            storage[size++] = resume;
        } else {
            System.out.println("Resume already in storage!");
        }
    }

    public Resume get(String uuid) {
        int resumeId = returnResumeId(uuid);

        if (resumeId != -1) {
            return storage[resumeId];
        }

        System.out.println("Resume is not in storage!");
        return null;
    }

    public void update(Resume resume) {
        int resumeId = returnResumeId(resume.getUuid());

        if (resumeId != -1) {
            storage[resumeId] = resume;
        } else {
            System.out.println("Resume is not in storage!");
        }
    }

    public void delete(String uuid) {
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
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int returnResumeId(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }

        return -1;
    }
}
