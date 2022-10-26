//Layer to interact with client

package mx.com.movies.view;

import mx.com.exceptions.DataAccessException;
import mx.com.movies.service.IMovieCatalog;
import mx.com.movies.service.MovieCatalogImplementation;

import java.io.IOException;
import java.util.Scanner;

public class MovieCatalogView {
    public static void main(String[] args) throws IOException, DataAccessException {
        var option = -1;
        var sc = new Scanner(System.in);
        IMovieCatalog catalog = new MovieCatalogImplementation();

        while(option != 0){
            System.out.println("Please select an option: \n" +
                    "\t0. Exit.\n" +
                    "\t1. Start movie catalog.\n" +
                    "\t2. Add movie.\n" +
                    "\t3. Show movies.\n" +
                    "\t4. Search movie.\n");
            option = Integer.parseInt(sc.nextLine());

            switch (option){
                case 0:
                    System.out.println("Later!");
                    break;
                case 1:
                    catalog.initializeMovieCatalog();
                    break;
                case 2:
                    System.out.println("Add movie name: ");
                    var movieName = sc.nextLine();
                    catalog.addMovie(movieName);
                    break;
                case 3:
                    catalog.listMovie();
                    break;
                case 4:
                    System.out.println("Which movie do you look for?: ");
                    var search = sc.nextLine();
                    catalog.searchMovie(search);
                    break;
                default:
                    System.out.println("Invalid option, please try again.\n");
            }
        }
    }
}
