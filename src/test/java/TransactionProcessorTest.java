import domain.CardHolder;
import domain.operation.Create;
import domain.operation.Modify;
import domain.operation.Operation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransactionProcessorTest {

    TransactionProcessor testProcessor;
    List<Operation> testOperations;

    @Before
    public void setUp() {
        this.testProcessor = new TransactionProcessor();
        testOperations = new ArrayList<>();
        testOperations.add(new Create("Ben", "5454545454545454", 10000));
        testOperations.add(new Create("Lisa", "5454545454545454", 5000));
        testOperations.add(new Create("Bob", "1234567890123456", 5000));
        testOperations.add(new Modify("Ben", 500));
        testOperations.add(new Modify("Lisa", -1000));
    }

    @Test
    public void process() {
        testProcessor.process(testOperations);
        Map<String, CardHolder> people = testProcessor.getPeople();
        assertThat(people.get("Ben").getBalance(), is(500));
        assertThat(people.get("Lisa").getBalance(), is(-1000));
    }

    @Test
    public void summarize() {
        testProcessor.process(testOperations);
        assertThat(testProcessor.summarize(), is("Ben: $500\n" +
                "Bob: error\n" +
                "Lisa: $-1000"));
    }
}