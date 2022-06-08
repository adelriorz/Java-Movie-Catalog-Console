package mx.com.movies.service;

import mx.com.exceptions.DataAccessException;
import mx.com.exceptions.DataReadingException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IMovieCatalog {

    //'public static final' is by default on compiler on Interfaces
    public static final String RESOURCE_NAME = "movies.txt";

    void addMovie(String movieName) throws DataAccessException, IOException;
    void listMovie();
    void searchMovie(String search) throws DataReadingException, FileNotFoundException;
    void initializeMovieCatalog() throws DataAccessException, IOException;
}
