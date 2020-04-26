package View.UserMenu;

import Controller.Client.ClientController;
import View.MainMenu;
import View.Menu;

public class UserMenu extends Menu {
    public UserMenu(Menu parentMenu) {
        super(parentMenu);
    }

    public UserMenu

    @Override
    public void help() {
        String userMenuOptions = "";
        userMenuOptions += "1.Register\n";
        userMenuOptions += "2.Login\n";
        userMenuOptions += "3.Help";
        userMenuOptions += "4.Back";
        System.out.println(userMenuOptions);

    }

    @Override
    public void execute() {
        String command;
        while (!(command = scanner.nextLine()).equalsIgnoreCase("back")) {
            if (command.equalsIgnoreCase("Register")) {
                Menu menu = new RegisterMenu(this).setScanner(this.scanner);
                ClientController.getInstance().setCurrentMenu(menu);
                menu.execute();
            } else if (command.equalsIgnoreCase("Login")) {
                Menu menu = new LoginMenu(this).setScanner(this.scanner);
                ClientController.getInstance().setCurrentMenu(menu);
                menu.execute();
            } else if (command.equalsIgnoreCase("help")) {
                help();
            }else {
                System.out.println("Invalid Command");
            }
        }
        back();
    }



}
