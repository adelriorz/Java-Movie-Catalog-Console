//Logic layer
package mx.com.movies.data;

import mx.com.exceptions.DataAccessException;
import mx.com.exceptions.DataReadingException;
import mx.com.exceptions.DataWrittingException;
import mx.com.movies.domain.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessImplementation implements IDataAccess{

    File file; //File to manage
    BufferedReader br; // Input variable

    @Override
    public boolean exists(String resourceName) throws DataAccessException {
        file = new File(resourceName);
        return file.exists();
    }

    @Override
    public List<Movie> list(String resourceName) throws DataReadingException, IOException {
        file = new File(resourceName);
        var movieList = new ArrayList<Movie>();
        String line = null;
        try{
            br = new BufferedReader(new FileReader(resourceName));
            line = br.readLine();
            while(line != null){ // To read movies
                var movie = new Movie(line);
                movieList.add(movie);
                line = br.readLine();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace(System.out);
            throw new DataReadingException("Exception when listing movies" + e.getMessage());
        }
        catch (IOException e){ // For IO exceptions
            e.printStackTrace(System.out);
            throw new DataReadingException("Exception when listing movies" + e.getMessage());
        }
        finally{
            br.close();
        }

        return movieList;
    }

    @Override
    public void write(Movie movie, String resourceName, boolean addInfo) throws DataAccessException {
        file = new File(resourceName);
        try {
            var pw = new PrintWriter(new FileWriter(resourceName, addInfo));
            pw.println(movie.toString()); //Printing movie to console
            pw.close();
            System.out.println("Information has been written to file: " + file.getName() + " " + movie);
        }catch(FileNotFoundException e) {
                e.printStackTrace(System.out);
                throw new DataReadingException("Exception when searching movies" + e.getMessage());
        }catch (IOException e){
            e.printStackTrace();
            throw new DataWrittingException("Exception when writing movies: " + e.getMessage());
        }

    }

    @Override
    public String search(String resourceName, String search) throws DataReadingException {
        file = new File(resourceName);
        String result = "", line = null;
        var index = 1;
        try{
            var br = new BufferedReader(new FileReader(resourceName));
            line = br.readLine();
            while(line != null){
                if(search != null && search.equalsIgnoreCase(line)){
                    result = "Movie " + line + " found at: " + index;
                    return result;
                }
                line = br.readLine();
                index++;
            }
            br.close();
        }catch(FileNotFoundException e) {
            e.printStackTrace(System.out);
            throw new DataReadingException("Exception when searching movies" + e.getMessage());
        }catch(IOException e){
            e.printStackTrace(System.out);
            throw new DataReadingException("Exception when searching movies" + e.getMessage());
        }
        return result;
    }

    @Override
    public void create(String resourceName) throws DataAccessException, IOException {
        file = new File(resourceName);
        try{
            var pw = new PrintWriter(new FileWriter(resourceName));
            pw.close();
            System.out.println("File : " + file + " has been created");
        }catch (IOException e){
            e.printStackTrace(System.out);
            throw new DataAccessException("Err creating file" + e.getMessage());
        }

    }

    @Override
    public void delete(String resourceName) throws DataAccessException {
        file = new File(resourceName);
        if (file.exists())
            file.delete();
        System.out.println("File :" + file + " has been deleted");

    }
}
