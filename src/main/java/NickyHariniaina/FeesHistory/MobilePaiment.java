package NickyHariniaina.FeesHistory;

import java.time.Instant;

import lombok.Getter;

@Getter
public class MobilePaiment extends Paiment {
  private String reference;

  public MobilePaiment(String id, double paidFees, Instant dateTimeOfPaiment, String reference) {
    super(id, paidFees, dateTimeOfPaiment);
    this.reference = reference;
  }
}
