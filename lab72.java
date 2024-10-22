import java.util.Scanner;
abstract class Bevarage{
    public void PrepareRecipe(){
        BoilWater();
        Brew();
        PourInCup();
        AddCondiments();
    }
    private void BoilWater(){
        System.out.println("Kipyat Vody");
    }
    private void PourInCup(){
        System.out.println("Nalivaut v stakan");
    }

    protected abstract void Brew();
    protected abstract void AddCondiments();
}
class Tea extends Bevarage{
    private final int choice;

    public Tea(int choice) {
        this.choice = choice;
    }
    @Override
    protected void Brew(){
        System.out.println("Zavarka Shaya");
    }
    @Override
    protected void AddCondiments(){
        switch (choice){
            case 1:
                System.out.println("Add milk");
                break;
            case 2:
                System.out.println("Add sugar");
                break;
            case 3:
                System.out.println("Add Coconut milk");
                break;
            default:
                System.out.println("No add condiments");
        }
    }
}
class Coffee extends Bevarage{
    private final int choice;

    public Coffee(int choice) {
        this.choice = choice;
    }
    @Override
    protected void Brew(){
        System.out.println("Zavarka Coffee ");
    }
    @Override
    protected void AddCondiments(){
        switch (choice){
            case 1:
                System.out.println("Add milk");
                break;
            case 2:
                System.out.println("Add sugar");
                break;
            case 3:
                System.out.println("Add Coconut milk");
                break;
            default:
                System.out.println("No add condiments");
        }
    }
}
class Chocolate extends Bevarage{
    private final int choice;

    public Chocolate(int choice) {
        this.choice = choice;
    }
    @Override
    protected void Brew(){
        System.out.println("Zavarka shockolate");
    }
    protected void AddCondiments(){
        switch (choice){
            case 1:
                System.out.println("Add milk");
                break;
            case 2:
                System.out.println("Add sugar");
                break;
            case 3:
                System.out.println("Add Coconut milk");
                break;
            default:
                System.out.println("No add condiments");
        }
    }
}
public class lab72{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Shay 1 - add milk, 2 - add sugar, 3 - add Coconut milk");
        int choise = scan.nextInt();
        Bevarage tea = new Tea(choise);
        tea.PrepareRecipe();




        System.out.println("\nCoffee 1 - add milk, 2 - add sugar, 3 - add Coconut milk");
        int choise2 = scan.nextInt();
        Bevarage coffee = new Coffee(choise2);
        coffee.PrepareRecipe();




        System.out.println("\nChokolate 1 - add milk, 2 - add sugar, 3 - add Coconut milk");
        int choise3 = scan.nextInt();
        Bevarage choko = new Chocolate(choise3);
        choko.PrepareRecipe();
    }
}