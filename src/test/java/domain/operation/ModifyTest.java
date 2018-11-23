package domain.operation;

import domain.CardHolder;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ModifyTest {

    @Test
    public void testCharge() {
        Map<String, CardHolder> testMap = new TreeMap<>();
        Create testCreate = new Create("Ben", "4", 10);
        testCreate.apply(testMap);

        Modify testCharge = new Modify("Ben", 5);
        testCharge.apply(testMap);

        assertThat(testMap.get("Ben").getCardNumber(), is("4"));
        assertThat(testMap.get("Ben").getBalance(), is(5));
        assertThat(testMap.get("Ben").getLimit(), is(10));
        assertThat(testMap.get("Ben").getName(), is("Ben"));

    }

    @Test
    public void testCredit() {

        Map<String, CardHolder> testMap = new TreeMap<>();
        Create testCreate = new Create("Ben", "4", 10);
        testCreate.apply(testMap);

        Modify testCharge = new Modify("Ben", -8);
        testCharge.apply(testMap);

        assertThat(testMap.get("Ben").getCardNumber(), is("4"));
        assertThat(testMap.get("Ben").getBalance(), is(-8));
        assertThat(testMap.get("Ben").getLimit(), is(10));
        assertThat(testMap.get("Ben").getName(), is("Ben"));

    }

}