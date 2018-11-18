package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.util.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SqlStorage implements Storage {

    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        SqlHelper.sqlExecution(connectionFactory, "DELETE from resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume resume) {
        SqlHelper.sqlExecution(connectionFactory, "INSERT INTO resume (uuid, full_name) VALUES (?, ?)",
                ps -> {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, resume.getFullName());
                    ps.execute();
                });
    }

    @Override
    public Resume get(String uuid) {
        List<String> fullName = new ArrayList<>(1);
        SqlHelper.sqlExecution(connectionFactory, "SELECT * FROM resume r WHERE r.uuid =?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    fullName.add(rs.getString("full_name"));
                });
        return new Resume(uuid, fullName.get(0));
    }

    @Override
    public void update(Resume resume) {
        SqlHelper.sqlExecution(connectionFactory, "UPDATE resume r SET r.full_name=? where r.uuid=?",
                ps -> {
                    ps.setString(1, resume.getFullName());
                    ps.setString(2, resume.getUuid());
                    ps.execute();
                });
    }

    @Override
    public void delete(String uuid) {
        SqlHelper.sqlExecution(connectionFactory, "DELETE FROM resume r WHERE r.uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    ps.execute();
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumeList = new ArrayList<>();
        SqlHelper.sqlExecution(connectionFactory, "SELECT * FROM resume r ORDER BY r.uuid",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        resumeList.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
                    }
                });
        return resumeList;
    }

    @Override
    public int getSize() {
        AtomicInteger result = new AtomicInteger();
        SqlHelper.sqlExecution(connectionFactory, "SELECT count(resume.uuid)",
                ps -> {
                    result.set(ps.getMaxRows());
                });
        return result.get();
    }
}
