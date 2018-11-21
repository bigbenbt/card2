import java.io.IOException;

public class app {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello!");
        TransactionProcessor processor = new TransactionProcessor();
        processor.process(args[0]);
    }
}
