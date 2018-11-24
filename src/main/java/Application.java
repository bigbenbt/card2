import domain.operation.Operation;
import parsers.FileParser;
import parsers.StdInParser;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        TransactionProcessor processor = new TransactionProcessor();
        List<Operation> operations;
        if (args.length==1) {
            operations = FileParser.parse(args[0]);
        } else {
            operations = StdInParser.parse();
        }
        processor.process(operations);
    }
}
