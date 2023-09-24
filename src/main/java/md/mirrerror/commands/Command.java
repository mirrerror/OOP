package md.mirrerror.commands;

public abstract class Command {

    private String label;

    public Command(String label) {
        this.label = label;
    }

    public abstract void onCommand(String[] args);

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
