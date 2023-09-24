package md.mirrerror.commands.main;

import md.mirrerror.Main;
import md.mirrerror.commands.Command;

public class QuitCommand extends Command {
    public QuitCommand() {
        super("q");
    }

    @Override
    public void onCommand(String[] args) {
        System.out.println("Saving the data...");
        Main.getDataRegistry().saveData();
        System.out.println("Disabling the app...");
        System.exit(0);
    }
}
