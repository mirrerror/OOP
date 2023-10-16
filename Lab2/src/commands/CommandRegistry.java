package commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandRegistry {

    private static final List<Command> registeredCommands;

    static {
        registeredCommands = new ArrayList<>();
    }

    public void registerCommand(Command command) {
        registeredCommands.add(command);
    }

    public void registerCommands(List<Command> commands) {
        registeredCommands.addAll(commands);
    }

    public static List<Command> getRegisteredCommands() {
        return Collections.unmodifiableList(registeredCommands);
    }
}
