import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TransactionProcessorTest {

    private String fileName = "C:\\Users\\Ben\\code\\card2\\src\\test\\resources\\input.txt";
    private TransactionProcessor transactionProcessor;

    @Before
    public void setUp() throws Exception {
        this.transactionProcessor = new TransactionProcessor();
    }

    @Test
    public void process() {
    }

    @Test
    public void processCommmands() throws IOException {
        this.transactionProcessor.processCommmands(transactionProcessor.ingest(fileName));
        transactionProcessor.getPeople();
    }

    @Test
    public void summarize() {
    }

    @Test
    public void ingest() throws IOException {
        transactionProcessor.ingest(fileName);
    }
}