package md.mirrerror.commands.main;

import md.mirrerror.entities.AppState;
import md.mirrerror.Main;
import md.mirrerror.utils.MenuUtils;
import md.mirrerror.commands.Command;

public class FacultyOperationsCommand extends Command {
    public FacultyOperationsCommand() {
        super("f");
    }

    @Override
    public void onCommand(String[] args) {
        Main.setAppState(AppState.FACULTY_OPERATIONS_MENU);
        MenuUtils.sendFacultyMenuHelpMessage();
    }
}
