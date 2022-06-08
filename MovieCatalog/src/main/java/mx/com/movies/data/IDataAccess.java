// Communication and logic access class. Defining behaviour.
package mx.com.movies.data;

import mx.com.exceptions.DataAccessException;
import mx.com.exceptions.DataReadingException;
import mx.com.exceptions.DataWrittingException;
import mx.com.movies.domain.Movie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IDataAccess {
    boolean exists(String resourceName) throws DataAccessException; //Could be db or any persistence.
    List<Movie> list(String resourceName) throws DataReadingException, IOException;
    void write(Movie movie, String resourceName, boolean addInfo) throws DataAccessException, IOException;
    String search(String resourceName, String search) throws DataReadingException, FileNotFoundException;
    void create(String resourceName) throws DataAccessException, IOException;
    void delete(String resourceName) throws DataAccessException;
}
