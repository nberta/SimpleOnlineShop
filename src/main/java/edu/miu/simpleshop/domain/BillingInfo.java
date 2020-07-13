package edu.miu.simpleshop.domain;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class BillingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne
    private Address billingAddress;

    private Long orderNumber;

    private Long creditCardNumber;

    private LocalDate transactionDate;


    public BillingInfo() {
        this.transactionDate = LocalDate.now();
        this.orderNumber = Long.parseLong(RandomStringUtils.randomNumeric(7));
        this.creditCardNumber = Long.parseLong(RandomStringUtils.randomNumeric(10));
    }
}
