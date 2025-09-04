package NickyHariniaina.FeesHistory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Fees {

  private final String id;
  private final String label;
  private final double feesToPay;
  private double feesAlreadyPaid;
  private LocalDate deadline;
  private Student targetStudent;
  private FeesStatus status;

  public FeesStatus getFeesStatusAt(Instant t) {
    FeesStatus current_status = FeesStatus.PAID;
    ZoneId zone = ZoneId.systemDefault();
    LocalDate targetDate = LocalDate.ofInstant(t, zone);

    if (targetDate.isBefore(deadline) && feesAlreadyPaid < feesToPay) {
      current_status = FeesStatus.IN_PROGRESS;
    } else if (feesAlreadyPaid == feesToPay) {
      current_status = FeesStatus.PAID;
    } else if (targetDate.isAfter(deadline) && feesAlreadyPaid < feesToPay) {
      current_status = FeesStatus.LATE;
    } else if (feesAlreadyPaid > feesToPay) {
      current_status = FeesStatus.OVERPAID;
    }
    return current_status;
  }

  public void payFees(Paiment paiment) {
    this.feesAlreadyPaid += paiment.getPaidFees();
  }
}
