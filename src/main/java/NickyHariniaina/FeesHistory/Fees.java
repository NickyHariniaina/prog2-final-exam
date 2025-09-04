package NickyHariniaina.FeesHistory;

import java.time.Instant;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Fees {

  private final String id;
  private final String label;
  private final Double feesToPay;
  private LocalDate deadline;
  private Student targetStudent;
  private FeesStatus status;

  public FeesStatus getFeesStatusAt(Instant t) {
    FeesStatus current_status = FeesStatus.PAID;

    return current_status;

  }
}
