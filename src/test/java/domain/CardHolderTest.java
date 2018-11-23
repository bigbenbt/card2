package domain;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class CardHolderTest {

    @Test
    public void modify() {

        CardHolder testCardholder = new CardHolder("Lisa", "5454545454545454", 100);
        testCardholder.modify(10);
        assertThat(testCardholder.getBalance(), is(10));
        testCardholder.modify(1000);
        assertThat(testCardholder.getBalance(), is(10));
        testCardholder.modify(-1000);
        assertThat(testCardholder.getBalance(), is(-990));
    }

    @Test
    public void validCard() {
        CardHolder testCardholder = new CardHolder("Ben", "number", 10000);
        assertFalse(testCardholder.validCard());
        testCardholder = new CardHolder("Lisa", "5454545454545454", 10000);
        assertTrue(testCardholder.validCard());
    }

    @Test
    public void summarize() {
        CardHolder testCardholder = new CardHolder("Ben", "number", 10000);
        assertThat(testCardholder.summarize(), is("Ben: error"));
        testCardholder = new CardHolder("Lisa", "5454545454545454", 10000);
        assertThat(testCardholder.summarize(), is("Lisa: $0"));
    }

    @Test
    public void testCreate() {

        CardHolder testCardholder = new CardHolder("Ben", "number", 10000);
        assertThat(testCardholder.getName(), is("Ben"));
        assertThat(testCardholder.getCardNumber(), is("number"));
        assertThat(testCardholder.getLimit(), is(10000));
        assertThat(testCardholder.getBalance(), is(0));

    }
}