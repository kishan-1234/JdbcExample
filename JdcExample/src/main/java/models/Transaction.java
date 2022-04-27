package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Transaction {

    private Long id;
    private Timestamp timestamp;
    private Long userId;
    public enum Currency {
        RUPEES, USD, EURO
    }
    private Currency currency;
    private Double amount;
}
