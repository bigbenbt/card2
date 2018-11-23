package domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;

@Data
@Slf4j
public class CardHolder {

    private String name;
    private String cardNumber;
    private Integer balance;
    private Integer limit;

    public CardHolder(String name, String cardNumber, Integer limit) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.limit = limit;
        this.balance = 0;
    }

    public void modify(Integer value) {
        if (this.balance + value <= this.limit) {
            this.balance += value;
        } else {
            log.warn("Charge of {} would take cardholder {} over their limit of {}, charge will not be applied", value, this.name, this.limit);
        }
    }

    protected boolean validCard(){
        return  new LuhnCheckDigit().isValid(this.cardNumber);
    }

    public String summarize(){
        return name + ": " + (validCard() ? "$"+balance : "error");
    }

}
