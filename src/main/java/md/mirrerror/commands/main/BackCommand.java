package md.mirrerror.commands.main;

import md.mirrerror.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;

public class BackCommand extends Command {
    public BackCommand() {
        super("b");
    }

    @Override
    public void onCommand(String[] args) {
        if(Main.getAppState() == AppState.MAIN_MENU) {
            System.out.println("You are in the main menu.");
            return;
        }

        Main.setAppState(AppState.MAIN_MENU);
        System.out.println("Welcome to TUM's student management system!");
        System.out.println("What do you want to do?");
        System.out.println("g - General operations");
        System.out.println("f - Faculty operations");
        System.out.println("s - Student operations");
        System.out.println();
        System.out.println("q - Quit program");
    }
}
