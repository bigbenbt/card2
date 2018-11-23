package domain.operation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OperationFactory {

    public static Operation buildOperation(String line) {

        String[] values = line.split(" ");
        OperationType type = OperationType.valueOf(values[0].toUpperCase());

        Operation operation;

        switch (type) {
            case ADD:
                operation = new Create(values[1], values[2], Integer.valueOf(values[3].replace("$", "")));
                break;
            case CHARGE:
                operation = new Modify(values[1], Integer.valueOf(values[2].replace("$", "")));
                break;
            case CREDIT:
                operation = new Modify(values[1], -Integer.valueOf(values[2].replace("$", "")));
                break;
            default:
                log.warn("Unable to assign line to an operation; valid operations are Add, Charge, and Credit");
                operation = null;
                break;
        }

        return operation;
    }

}
