package domain;

import lombok.AllArgsConstructor;
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
        this.balance = 0;
        this.limit = limit;
    }

    public void charge(Integer value) {
        if (this.balance + value <= this.limit) {
            this.balance += value;
        } else {
            log.error("Charge of {} would take cardholder {} over their limit of {}", value, this.name, this.limit);
        }
    }

    private boolean validCard(){
        return  new LuhnCheckDigit().isValid(this.cardNumber);
    }

    public void credit(Integer value) {
        this.balance -= value;
    }

    public String summarize(){
        return name + ": " + (validCard() ? "$"+balance : "error");
    }

}
