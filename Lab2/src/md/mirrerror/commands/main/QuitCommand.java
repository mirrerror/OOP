package md.mirrerror.commands.main;

import md.mirrerror.Main;
import md.mirrerror.commands.Command;

public class QuitCommand extends Command {

    public QuitCommand() {
        super("q");
    }

    @Override
    public void onCommand(String[] args) {
        System.out.println("Disabling the app...");
        Main.setRunning(false);
    }
}
