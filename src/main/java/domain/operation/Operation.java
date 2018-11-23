package domain.operation;

import domain.CardHolder;

import java.util.Map;

public interface Operation {

    void apply(Map<String, CardHolder> people);

}
