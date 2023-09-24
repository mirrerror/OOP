package md.mirrerror.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandRegistry {

    private static List<Command> registeredCommands;

    static {
        registeredCommands = new ArrayList<>();
    }

    public void registerCommand(Command command) {
        registeredCommands.add(command);
    }

    public static List<Command> getRegisteredCommands() {
        return Collections.unmodifiableList(registeredCommands);
    }
}
