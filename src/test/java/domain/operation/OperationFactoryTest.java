package domain.operation;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OperationFactoryTest {

    @Test
    public void buildCreate() {

        String line = "Add Tom 4111111111111111 $1000";
        Operation testOut = OperationFactory.buildOperation(line);
        assertThat(testOut, instanceOf(Create.class));
        Create casted = (Create) testOut;
        assertThat(casted.getCardNumber(), is("4111111111111111"));
        assertThat(casted.getName(), is("Tom"));
        assertThat(casted.getLimit(), is(1000));

    }

    @Test
    public void testCharge() {

        String line = "Charge Tom $500";
        Operation testOut = OperationFactory.buildOperation(line);
        assertThat(testOut, instanceOf(Modify.class));
        Modify casted = (Modify) testOut;
        assertThat(casted.getChargeAmount(), is(500));
        assertThat(casted.getName(), is("Tom"));

    }

    @Test
    public void testCredit() {

        String line = "Credit Tom $200";
        Operation testOut = OperationFactory.buildOperation(line);
        assertThat(testOut, instanceOf(Modify.class));
        Modify casted = (Modify) testOut;
        assertThat(casted.getChargeAmount(), is(-200));
        assertThat(casted.getName(), is("Tom"));

    }


}