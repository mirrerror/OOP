package md.mirrerror.commands.main;

import md.mirrerror.entities.AppState;
import md.mirrerror.Main;
import md.mirrerror.utils.MenuUtils;
import md.mirrerror.commands.Command;

public class GeneralOperationsCommand extends Command {
    public GeneralOperationsCommand() {
        super("g");
    }

    @Override
    public void onCommand(String[] args) {
        Main.setAppState(AppState.GENERAL_OPERATIONS);
        MenuUtils.sendGeneralMenu();
    }
}
