package NickyHariniaina.FeesHistory;

import java.time.Instant;

import lombok.Getter;

@Getter
public class BankPaiment extends Paiment {

  private final String receiptReference;

  public BankPaiment(String id, double paidFees, Instant dateTimeOfPaiment, String receiptReference) {
    super(id, paidFees, dateTimeOfPaiment);
    this.receiptReference = receiptReference;
  }

}
