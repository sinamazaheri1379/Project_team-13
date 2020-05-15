package View.ProductsAndOffsMenus;

import Controller.Client.ClientController;
import Models.Product.Product;
import View.Menu;

public class ProductMenu extends Menu {
    public ProductMenu(Menu parentMenu) {
        super(parentMenu);
    }
    @Override
    public void help() {
        String userMenuOptions = "";
        userMenuOptions += "1.Digest\n";
        userMenuOptions += "7.Help\n";
        userMenuOptions += "4.Back\n";
        userMenuOptions += "5.LogOut\n";
        System.out.println(userMenuOptions);

    }

    @Override
    public void execute() {
        Product product =  ClientController.getInstance().getCurrentProduct();
        String command;
        while (!(command = scanner.nextLine()).equalsIgnoreCase("back")) {
            if (command.equalsIgnoreCase("digest")) {
                Menu menu = new Digest(this).setScanner(scanner);
                ClientController.getInstance().setCurrentMenu(menu);
                menu.execute();
            } else if (command.equalsIgnoreCase("help")) {
                help();
            } else if (command.equalsIgnoreCase("logout")) {
                ClientController.getInstance().setCurrentUser(null);
                System.out.println("You Logged out!!");
                parentMenu.execute();
            } else {
                System.out.println("Invalid Command");
            }
        }
        back();
    }

}
