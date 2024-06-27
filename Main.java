import controller.CinemaController;
import model.Cinema;
import view.CinemaView;

public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        CinemaView cinemaView = new CinemaView();
        CinemaController cinemaController = new CinemaController(cinema, cinemaView);

        cinemaController.carregarDados();
        cinemaController.iniciar();
    }
}
