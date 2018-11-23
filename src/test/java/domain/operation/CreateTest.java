package domain.operation;

import domain.CardHolder;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateTest {

    @Test
    public void apply() {

        Map<String, CardHolder> testMap = new TreeMap<>();
        Create testCreate = new Create("Ben", "4", 10);
        testCreate.apply(testMap);
        assertThat(testMap.size(), is(1));
        assertThat(testMap.get("Ben").getCardNumber(), is("4"));
        assertThat(testMap.get("Ben").getBalance(), is(0));
        assertThat(testMap.get("Ben").getLimit(), is(10));
        assertThat(testMap.get("Ben").getName(), is("Ben"));

    }
}