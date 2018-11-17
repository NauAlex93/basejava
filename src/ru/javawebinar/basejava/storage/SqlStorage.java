package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.*;
import java.util.List;

public class SqlStorage implements Storage{

    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE from resume")) {
            ps.execute();
        }
        catch (SQLException ex)
        {
            throw new StorageException(ex);
        }
    }

    @Override
    public void save(Resume resume) {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT into resume (uuid, full_name) VALUES (?, ?)")) {
            ps.setString(1, resume.getUuid());
            ps.setString(1, resume.getFullName());
            ps.execute();
        }
        catch (SQLException ex)
        {
            throw new StorageException(ex);
        }
    }

    @Override
    public Resume get(String uuid) {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * from resume r where r.uuid =?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume r = new Resume(uuid, rs.getString("full_name"));
            return r;
        }
        catch (SQLException ex)
        {
            throw new StorageException(ex);
        }
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
