import java.util.*;
import java.io.*;

public class MPB {
    static List<Ordre> ordre = new ArrayList<>();
    static List<PizzaList> pizzas = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        menu();
    } //Afslutning af main
    public static void sePizzaer() {
        //Oversigt over Pizza og Ingredienser
        pizzas.add(new PizzaList(1, "Vesuvio", "tomatsauce, ost, skinke, oregano", 57));
        pizzas.add(new PizzaList(2, "Amerikaner", "Tomatsauce, ost, oksefars, oregano", 53));
        pizzas.add(new PizzaList(3, "Cacciatore", "Tomatsauce, ost, pepperoni, oregano", 57));
        pizzas.add(new PizzaList(4, "Carbona", "Tomatsauce, ost, kødsauce, spaghetti, cocktailpølser, oregano", 63));
        pizzas.add(new PizzaList(5, "Dennis", "Tomatsauce, ost, skinke, pepperoni, cocktailpølser, oregano", 65));
        pizzas.add(new PizzaList(6, "Bertil", "Tomatsauce, ost, bacon, oregano", 57));
        pizzas.add(new PizzaList(7, "Silvia", "Tomatsauce, ost, pepperoni, rød peber, løg, oliven, oregano", 61));
        pizzas.add(new PizzaList(8, "Victoria", "Tomatsauce, ost, skinke, ananas, champignon, løg, oregano", 61));
        pizzas.add(new PizzaList(9, "Toronfo", "Tomatsauce, ost, skinke, bacon, kebab, chili, oregano", 61));
        pizzas.add(new PizzaList(10, "Capricciosa", "Tomatsauce, ost, skinke, champignon, oregano", 61));
        pizzas.add(new PizzaList(11, "Hawai", "Tomatsauce, ost, skinke, ananas, oregano", 61));
        pizzas.add(new PizzaList(12, "Le Blissola", "Tomatsauce, ost, skinke, rejer, oregano", 61));
        pizzas.add(new PizzaList(13, "Venezia", "Tomatsauce, ost, skinke, bacon, oregano", 61));
        pizzas.add(new PizzaList(14, "Mafia", "Tomatsauce, ost, pepperoni, bacon, løg, oregano", 61));

        System.out.println("Pizzaliste:");
        System.out.printf("%-3s %-15s %-65s %-5s\n", "Nr.", "Navn", "Ingredienser", "Pris");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (PizzaList p : pizzas) {
            System.out.printf("%-3d %-15s %-65s %-5d\n", p.getNumber(), p.getName(), p.getIngredients(), p.getPrice());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------\n");
    }

    public static PizzaList getPizzaByNumber(int number) {
        for (PizzaList p : pizzas) {
            if (p.getNumber() == number) {
                return p;
            }
        }
        return null;
    }

    public static void menu() {
        System.out.println("Menu: ");
        System.out.println("1. Opret ordre");
        System.out.println("2. Vis aktive bestillinger");
        System.out.println("3. Afslut ordre");
        System.out.println("4. Se menu");
        System.out.println("5. Se ordrehistorikken");
        System.out.println("6. Afslut programmet");

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                sePizzaer();
                opretOrdre();
                menu();
            case 2:
                listeOverBestillinger();
                menu();
            case 3:
                gemFlytAfslutOrdre(ordre.size());
                menu();
            case 4:
                sePizzaer();
                menu();
            case 5:
                //ordreHistorik();
                menu();
            case 6:
                System.exit(0);
            default:
                System.out.println("Ugyldigt valg vælg mellem 1-6.");
        }//Afslutning af switch
    }//Afslutning af menu

    public static Ordre opretOrdre() {
        ArrayList<PizzaList> pizzas = new ArrayList<>();

        while (true) {
            System.out.println("Hvilken pizza vil du tilføje (tast 0 for at afslutte)");
            int pizzaNumber = sc.nextInt();
            if (pizzaNumber == 0) {
                break;
            }
            PizzaList pizza = getPizzaByNumber(pizzaNumber);
            if (pizza != null) {
                pizzas.add(pizza);
                System.out.println("Pizzaen blev tilføjet til ordren\n\n");

            } else System.out.println("Pizzaen findes ikke, prøv et andet nr.");
        }
        System.out.println("Hvad er dit navn?");
        sc.nextLine();
        String navn = sc.nextLine();

        System.out.println("Hvad er din adresse?");
        String adresse = sc.nextLine();

        System.out.println("Hvad er dit telefonnummer?");
        int tlfnr = sc.nextInt();
        sc.nextLine();  // consume leftover newline

        System.out.println("Hvad er din email?");
        String email = sc.nextLine();

        System.out.println("Hvornår vil du have din pizza? (Angiv TID som HH:MM)");
        String tid = sc.next();

        System.out.println("Din ordre er nu oprettet, tak for din bestilling!");

        int ordreNr = ordre.size() + 1;
        Ordre newOrdre = new Ordre(pizzas, navn, adresse, tlfnr, email, tid, ordreNr);
        ordre.add(newOrdre);
        return new Ordre(pizzas, navn, adresse, tlfnr, email, tid, ordreNr);
    }//Afslutning af opretOrdre

    //Liste over bestillinger
    public static void listeOverBestillinger() {
        Collections.sort(ordre, Comparator.comparing(Ordre::getTid));
        System.out.println("Liste over bestillinger:\n");
        for (Ordre o : ordre) {
            System.out.println("Ordre nr: " + o.getOrdreNr());
            System.out.println("Kunde navn: " + o.getNavn());
            System.out.println("Pizzaer skal være klar kl: " + o.getTid());
            for (PizzaList p : o.getPizzas()) {
                System.out.println(p);
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
    public static void gemFlytAfslutOrdre(int ordreNr){
        System.out.println("Indtast ordrenummeret, der skal fjernes:");
        int ordreNr1 = sc.nextInt();

        Iterator<Ordre> iterator = ordre.iterator();
        while (iterator.hasNext()){
            Ordre ordre = iterator.next();
            if (ordre.getOrdreNr() == ordreNr){
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Ordrehistorik.txt", true))){
                    writer.write(ordre.toString());
                    writer.newLine();
                } catch (IOException e){
                    System.out.println("Der er opstået en fejl!" + e.getMessage());
                }
                iterator.remove();
                break;
            }
        }

    }//Afslutning af afslutOrdre
}