package domain.operation;

import domain.CardHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class Create implements Operation {

    private String name;
    private String cardNumber;
    private Integer limit;

    @Override
    public void apply(Map<String, CardHolder> people) {
        people.put(name, new CardHolder(name, cardNumber, limit));
    }
}
