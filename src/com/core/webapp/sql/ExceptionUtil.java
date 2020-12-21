package com.core.webapp.sql;

import org.postgresql.util.PSQLException;
import com.core.webapp.exception.ExistStorageException;
import com.core.webapp.exception.StorageException;

import java.sql.SQLException;

public class ExceptionUtil {
    public ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
