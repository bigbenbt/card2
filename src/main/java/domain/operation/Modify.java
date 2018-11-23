package domain.operation;

import domain.CardHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class Modify implements Operation {

    private String name;
    private Integer chargeAmount;

    @Override
    public void apply(Map<String, CardHolder> people) {
        CardHolder entry = people.get(name);
        if (entry != null) {
           entry.modify(chargeAmount);
        }
    }
}
