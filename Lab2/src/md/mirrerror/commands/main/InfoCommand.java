package md.mirrerror.commands.main;

import md.mirrerror.Main;
import md.mirrerror.commands.Command;

import java.io.File;

public class InfoCommand extends Command {
    public InfoCommand() {
        super("info");
    }

    @Override
    public void onCommand(String[] args) {
        File file;

        if(args.length < 1) {
            System.out.println("Usage: info <file name>");
            return;
        }

        file = new File(Main.getRepository().getDirectory().getName() + "/" + args[0]);

        if(!file.exists()) {
            System.out.println("File with the specified name does not exist.");
            return;
        }

        Main.getRepository().printInfo(file);
    }
}
