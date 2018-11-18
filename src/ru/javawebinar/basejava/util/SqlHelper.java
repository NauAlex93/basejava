package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private static ConnectionFactory connectionFactory;

    public static <T> void sqlExecution(ConnectionFactory connectionFactory, String sqlQuery,
                                         SQLConsumer<PreparedStatement> consumer) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            consumer.accept(ps);
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    public interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }

}
