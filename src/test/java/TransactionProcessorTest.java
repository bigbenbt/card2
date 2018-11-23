import domain.CardHolder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TransactionProcessorTest {

    @Test
    public void testAdd() {

        String testCommand = "ADD Tom 12345 10000";

        TransactionProcessor testProcessor = new TransactionProcessor();
        testProcessor.addPerson(testCommand.split(" "));

        Map<String, CardHolder> testRes = testProcessor.getPeople();

        assertThat(testRes.size(), is(1));
        CardHolder testPerson = testRes.get("Tom");
        assertThat(testPerson.getName(), is("Tom"));
        assertThat(testPerson.getBalance(), is(0));
        assertThat(testPerson.getLimit(), is(10000));
        assertThat(testPerson.getCardNumber(), is("12345"));

    }

    @Test
    public void testCharge() {

        String testCommand1 = "CHARGE Tom 10";
        String testCommand2 = "ADD Tom 12345 10000";

        TransactionProcessor testProcessor = new TransactionProcessor();

        // here to make sure it fails gracefully.  Trying to apply a charge to someone who hasn't been added shouldn't crash
        testProcessor.chargePerson(testCommand1.split(" "));

        //now add the person and then apply the charge
        testProcessor.addPerson(testCommand2.split(" "));
        testProcessor.chargePerson(testCommand1.split(" "));

        Map<String, CardHolder> testRes = testProcessor.getPeople();

        assertThat(testRes.size(), is(1));
        CardHolder testPerson = testRes.get("Tom");
        assertThat(testPerson.getBalance(), is(10));
    }

    @Test
    public void testCredit() {

        String testCommand1 = "CREDIT Tom 50";
        String testCommand2 = "ADD Tom 12345 10000";

        TransactionProcessor testProcessor = new TransactionProcessor();

        // here to make sure it fails gracefully.  Trying to apply a credit to someone who hasn't been added shouldn't crash
        testProcessor.chargePerson(testCommand1.split(" "));

        //now add the person and then apply the charge
        testProcessor.addPerson(testCommand2.split(" "));
        testProcessor.creditPerson(testCommand1.split(" "));

        Map<String, CardHolder> testRes = testProcessor.getPeople();

        assertThat(testRes.size(), is(1));
        CardHolder testPerson = testRes.get("Tom");
        assertThat(testPerson.getBalance(), is(-50));

    }

    @Test
    public void summarize() {

        TransactionProcessor testProcessor = new TransactionProcessor();
        assertThat(testProcessor.summarize(), is(""));

        String testCommand = "Add Tom 4111111111111111 1000";
        List<String> commands = new ArrayList<>();
        commands.add(testCommand);
        testProcessor.process(commands);

        assertThat(testProcessor.summarize(), is("Tom: $0"));

    }
}