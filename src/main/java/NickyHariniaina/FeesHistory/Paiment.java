package NickyHariniaina.FeesHistory;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Paiment {

  private final String id;
  private double paidFees;
  private Instant dateTimeOfPaiment;
}
