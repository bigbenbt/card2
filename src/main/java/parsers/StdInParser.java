package parsers;

import domain.operation.Operation;
import domain.operation.OperationFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StdInParser {

    public static List<Operation> parse() {
        List<Operation> operations = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            operations.add(OperationFactory.buildOperation(in.nextLine()));
        }
        return operations;
    }

}
