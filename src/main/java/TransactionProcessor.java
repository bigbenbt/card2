import domain.CardHolder;
import domain.operation.Operation;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

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

    public void process(List<Operation> operations) {
        operations.stream().forEach(o-> o.apply(people));
        System.out.println(summarize());
    }

    protected String summarize() {
        return StringUtils
                .join(people.values()
                        .stream()
                        .map(CardHolder::summarize)
                        .collect(Collectors.toList()), "\n");

    }

}
