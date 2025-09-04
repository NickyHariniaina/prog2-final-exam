package NickyHariniaina.FeesHistory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;

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

  private HashMap<Instant, Double> paidFeesHistory = new HashMap<>();

  public FeesStatus getFeesStatusAt(Instant t) {
    ZoneId zone = ZoneId.systemDefault();
    LocalDate targetDate = LocalDate.ofInstant(t, zone);

    if (targetDate.isBefore(deadline) && feesAlreadyPaid < feesToPay) {

      this.status = FeesStatus.IN_PROGRESS;
    } else if (feesAlreadyPaid == feesToPay) {
      this.status = FeesStatus.PAID;
    } else if (targetDate.isAfter(deadline) && feesAlreadyPaid < feesToPay) {
      this.status = FeesStatus.LATE;
    } else if (feesAlreadyPaid > feesToPay) {
      this.status = FeesStatus.OVERPAID;
    }
    return this.status;
  }

  public void payFees(Paiment paiment) {
    this.feesAlreadyPaid += paiment.getPaidFees();
    this.paidFeesHistory.put(paiment.getDateTimeOfPaiment(), paiment.getPaidFees());
  }

  public double getTotalRemaingFeesToPay() {
    return this.feesToPay - this.feesAlreadyPaid;
  }
}
