/*Business layer to implement movie catalog*/

package mx.com.movies.service;

import mx.com.exceptions.DataAccessException;
import mx.com.exceptions.DataReadingException;
import mx.com.movies.data.DataAccessImplementation;
import mx.com.movies.data.IDataAccess;
import mx.com.movies.domain.Movie;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MovieCatalogImplementation implements IMovieCatalog{
    private final IDataAccess data;

    // Initialize movie catalog as well as movies
    public MovieCatalogImplementation() {
        this.data = new DataAccessImplementation();
    }

    @Override
    public void addMovie(String movieName) throws DataAccessException, IOException {
        var movie = new Movie(movieName);
        var addInfoToFile = false;
        addInfoToFile = data.exists(RESOURCE_NAME);
        data.write(movie, RESOURCE_NAME, addInfoToFile);
    }

    @Override
    public void listMovie() {
        try{
            var movies = this.data.list(RESOURCE_NAME);
            for(var movie:movies){
                System.out.println("movie = " + movie);
            }
        }catch (DataAccessException e){
            System.out.println("Error accessing data = " + e);
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void searchMovie(String search) throws DataReadingException, FileNotFoundException {
        String result = null;
        result = this.data.search(RESOURCE_NAME, search);
        System.out.println("result = " + result);
    }

    @Override
    public void initializeMovieCatalog() {
        try{
            if(this.data.exists(RESOURCE_NAME)) {
                data.delete(RESOURCE_NAME);
                data.create(RESOURCE_NAME);
            }else{
                data.create(RESOURCE_NAME);
            }
        }catch (DataAccessException e){
            System.out.println("Error when initializing movie catalog");
            e.printStackTrace(System.out);
        }catch (IOException e){
            System.out.println("Error when initializing movie catalog");
            e.printStackTrace(System.out);
        }
    }
}
