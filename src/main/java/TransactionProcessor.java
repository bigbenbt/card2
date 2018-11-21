import com.sun.deploy.util.StringUtils;
import domain.CardHolder;
import domain.Operations;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Getter
public class TransactionProcessor {

    private Map<String, CardHolder> people;

    public TransactionProcessor() {
        this.people = new TreeMap<>();
    }

    public void process(String fileName) throws IOException {
        List<String> commands = ingest(fileName);
        processCommmands(commands);
        System.out.println(summarize());
    }

    protected void processCommmands(List<String> commands) {
        for (String command : commands) {
            String[] values = command.split(" ");
            Operations type = Operations.valueOf(values[0].toUpperCase());
            switch (type) {
                case ADD:
                    this.people.put(values[1], new CardHolder(values[1], values[2], 0, Integer.valueOf(values[3])));
                    break;
                case CHARGE:
                    CardHolder holder = this.people.get(values[1]);
                    holder.charge(Integer.valueOf(values[2]));
                    break;
                case CREDIT:
                    holder = this.people.get(values[1]);
                    holder.credit(Integer.valueOf(values[2]));
                    break;
            }
        }
    }

    protected String summarize() {
        return StringUtils.join(
                this.people.entrySet()
                .stream()
                .map(e -> e.getValue().summarize())
                .collect(Collectors.toList()
                ), "\n");
    }

    protected List<String> ingest(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(s -> s.replace("$", ""))//get rid of the dollar signs
                .collect(Collectors.toList());
    }
}
