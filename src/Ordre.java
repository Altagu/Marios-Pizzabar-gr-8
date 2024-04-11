import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ordre {
    private ArrayList<PizzaList> pizzas;
    private String navn;
    private String adresse;
    private int tlfnr;
    private String email;
    private LocalTime tid;
    private int ordreNr;

    public Ordre(ArrayList<PizzaList> pizzas, String navn, String adresse, int tlfnr, String email, String tid, int ordreNr){
        this.navn = navn;
        this.adresse = adresse;
        this.tlfnr = tlfnr;
        this.email = email;
        this.pizzas = pizzas;
        this.tid = LocalTime.parse(tid, DateTimeFormatter.ofPattern("HH:mm"));
        this.ordreNr = ordreNr;
    }
    public String getNavn(){
        return this.navn;
    }
    public ArrayList<PizzaList> getPizzas() {
        return this.pizzas;
    }
    public LocalTime getTid() {
        return this.tid;
    }
    public int getOrdreNr() {
        return this.ordreNr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ordre nr: ").append(ordreNr).append("\n");
        sb.append("Kunde navn: ").append(navn).append("\n");
        sb.append("Pizzaer skal være klar kl: ").append(tid).append("\n");
        for (PizzaList p : pizzas) {
            sb.append(p).append("\n");
        }
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        return sb.toString();
    }
}