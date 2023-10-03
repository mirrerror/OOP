package md.mirrerror.commands.main;

import md.mirrerror.Main;
import md.mirrerror.commands.Command;
import md.mirrerror.commands.CommandParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BatchUpdateCommand extends Command {

    public BatchUpdateCommand() {
        super("bu");
    }

    @Override
    public void onCommand(String[] args) {
        File file;

        if(args.length < 1) {
            System.out.println("Usage: bu/<file name>");
            return;
        }

        file = new File(args[0]);

        if(!file.exists()) {
            System.out.println("The file with specified name doesn't exist.");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            CommandParser commandParser = new CommandParser();
            while ((line = br.readLine()) != null) Main.executeCommand(commandParser, line);
            br.close();
        } catch (IOException e) {
            Main.getLogger().error("Couldn't load the batch update from the file + " + file.getName() + ".");
            e.printStackTrace();
            return;
        }

        System.out.println("Successfully executed the batch update from the file " + file.getName() + ".");
        Main.getLogger().info("Successfully executed the batch update from the file " + file.getName() + ".");
    }
}
