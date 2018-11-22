
import domain.CardHolder;
import domain.Operations;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class TransactionProcessor {

    private Map<String, CardHolder> people;

    public TransactionProcessor() {
        this.people = new TreeMap<>();
    }

    public void process(List<String> commands) {
        processCommmands(commands);
        System.out.println(summarize());
    }

    protected void processCommmands(List<String> commands) {
        for (String command : commands) {
            String[] values = command.split(" ");
            Operations type = Operations.valueOf(values[0].toUpperCase());
            switch (type) {
                case ADD:
                    addPerson(values);
                    break;
                case CHARGE:
                    chargePerson(values);
                    break;
                case CREDIT:
                    creditPerson(values);
                    break;
                default:
                    log.error("Invalid command provided; Command received was {}, may only be of type ADD, CHARGE, or CREDIT", type);
            }
        }
    }

    protected void creditPerson(String[] values) {
        CardHolder holder;
        holder = this.people.get(values[1]);
        if (holder != null) {
            holder.credit(Integer.valueOf(values[2]));
        } else {
            log.error("Tried to credit account for {} but no card holder exists with that name", values[1]);
        }
    }

    protected void chargePerson(String[] values) {
        CardHolder holder = this.people.get(values[1]);
        if (holder != null) {
            holder.charge(Integer.valueOf(values[2]));
        } else {
            log.error("Tried to apply a charge to {} but no card holder exists with that name", values[1]);
        }
    }

    protected void addPerson(String[] values) {
        this.people.put(values[1], new CardHolder(values[1], values[2], Integer.valueOf(values[3])));
    }

    protected String summarize() {
        return StringUtils.join(
                this.people.entrySet()
                .stream()
                .map(e -> e.getValue().summarize())
                .collect(Collectors.toList()
                ), "\n");
    }

}
