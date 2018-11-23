package parsers;

import domain.operation.Operation;
import domain.operation.OperationFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileParser {

    public static List<Operation> parse(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(OperationFactory::buildOperation)
                .collect(Collectors.toList());
    }

}
