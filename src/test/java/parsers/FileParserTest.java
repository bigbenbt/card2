package parsers;

import domain.operation.Create;
import domain.operation.Modify;
import domain.operation.Operation;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FileParserTest {

    @Test
    public void parse() throws IOException {
        List<Operation> operations = FileParser.parse("src/test/resources/input.txt");
        assertThat(operations.size(), is(8));

        assertThat(operations.get(0), instanceOf(Create.class));
        assertThat(((Create) operations.get(0)).getName(), is("Tom"));
        assertThat(((Create) operations.get(0)).getCardNumber(), is("4111111111111111"));
        assertThat(((Create) operations.get(0)).getLimit(), is(1000));

        assertThat(operations.get(4), instanceOf(Modify.class));
        assertThat(((Modify) operations.get(4)).getName(), is("Tom"));
        assertThat(((Modify) operations.get(4)).getChargeAmount(), is(800));

        assertThat(operations.get(7), instanceOf(Modify.class));
        assertThat(((Modify) operations.get(7)).getName(), is("Quincy"));
        assertThat(((Modify) operations.get(7)).getChargeAmount(), is(-200));
    }
}