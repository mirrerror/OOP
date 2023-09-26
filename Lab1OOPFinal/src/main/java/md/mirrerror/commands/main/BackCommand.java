package md.mirrerror.commands.main;

import md.mirrerror.entities.AppState;
import md.mirrerror.Main;
import md.mirrerror.utils.MenuUtils;
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
        MenuUtils.sendMainMenu();
    }
}
