package md.mirrerror.commands.main;

import md.mirrerror.entities.AppState;
import md.mirrerror.Main;
import md.mirrerror.utils.MenuUtils;
import md.mirrerror.commands.Command;

public class StudentOperationsCommand extends Command {
    public StudentOperationsCommand() {
        super("s");
    }

    @Override
    public void onCommand(String[] args) {
        Main.setAppState(AppState.STUDENT_OPERATIONS_MENU);
        MenuUtils.sendStudentsMenuHelpMessage();
    }
}
