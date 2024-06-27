import model.*;
import view.*;


public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        CinemaView cinemaView = new CinemaView();
        CinemaController cinemaController = new CinemaController(cinema, cinemaView);

        cinemaController.iniciar();
    }
}
