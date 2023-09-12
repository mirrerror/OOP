public class Diamond {

    private int size;

    public Diamond(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void print() {
        int c = 1;
        int space;
        while (c < size) {
            space = (size - c)/2;
            for(int i = 0; i < space; i++) System.out.print(" ");
            for(int i = 0; i < c; i++) System.out.print("*");
            for(int i = 0; i < space; i++) System.out.print(" ");
            c += 2;
            System.out.println();
        }
        while (c > 0) {
            space = (size - c)/2;
            for(int i = 0; i < space; i++) System.out.print(" ");
            for(int i = 0; i < c; i++) System.out.print("*");
            for(int i = 0; i < space; i++) System.out.print(" ");
            c -= 2;
            System.out.println();
        }
    }
}
