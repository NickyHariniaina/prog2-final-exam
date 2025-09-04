package NickyHariniaina.FeesHistory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Statistics {
  public List<Fees> getLateFees(List<Fees> fees, Instant t) {
    List<Fees> lateFees = new ArrayList<>();

    for (Fees fee : fees) {
      FeesStatus status = fee.getFeesStatusAt(t);
      if (status == FeesStatus.LATE) {
        lateFees.add(fee);
      }
    }

    return lateFees;
  }

  public double getTotalMissingFees(List<Fees> fees, Instant t) {
    double totalMissingFees = 0.00;

    for (Fees fee : fees) {
      FeesStatus status = fee.getFeesStatusAt(t);
      if (status == FeesStatus.LATE) {
        totalMissingFees += fee.getTotalRemaingFeesToPay();
      }
    }
    return totalMissingFees;
  }

  public double getTotalPaidByStudent(Student student, List<Fees> fees, Instant t) {
    double paidFees = 0.00;
    for (Fees fee : fees) {
      if (fee.getTargetStudent().equals(student)) {
        for (Map.Entry<Instant, Double> history : fee.getPaidFeesHistory().entrySet()) {
          if (history.getKey().isBefore(t)) {
            paidFees += history.getValue();
          }
        }
      }
    }
    return paidFees;
  }
}
