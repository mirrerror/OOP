package md.mirrerror.commands.main;

import md.mirrerror.Main;
import md.mirrerror.commands.Command;

public class CommitCommand extends Command {
    public CommitCommand() {
        super("commit");
    }

    @Override
    public void onCommand(String[] args) {
        Main.getRepository().commit();
        System.out.println("Committed.");
    }
}
