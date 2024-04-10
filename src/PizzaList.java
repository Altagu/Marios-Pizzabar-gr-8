public class PizzaList {
    private int number;
    private String name;
    private String ingredients;
    private int price;

    public PizzaList(int number, String name, String ingredients, int price) {
        this.number = number;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-20s %-70s %s dkk%n", number + ".", name + ":", ingredients, price);
        //return "NR " + number + "\t" +name + ":\t\t\t " + ingredients + "\t\t\t\t\t\t\t Pris: " + price + " kr.\n";
    }
    public int getNumber(){
        return this.number;
    }
    public String getName(){
        return this.name;
    }
    public String getIngredients(){
        return this.ingredients;
    }
    public int getPrice(){
        return this.price;
    }
}
