package md.mirrerror.commands.main;

import md.mirrerror.Main;
import md.mirrerror.commands.Command;

public class StatusCommand extends Command {
    public StatusCommand() {
        super("status");
    }

    @Override
    public void onCommand(String[] args) {
        Main.getRepository().printStatus();
    }
}
