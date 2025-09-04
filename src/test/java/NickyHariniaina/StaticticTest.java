package NickyHariniaina;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import NickyHariniaina.FeesHistory.ClassGroup;
import NickyHariniaina.FeesHistory.Fees;
import NickyHariniaina.FeesHistory.FeesStatus;
import NickyHariniaina.FeesHistory.MobilePaiment;
import NickyHariniaina.FeesHistory.Paiment;
import NickyHariniaina.FeesHistory.Statistics;
import NickyHariniaina.FeesHistory.Student;

public class StaticticTest {

  @Test
  public void get_late_fees_ok() {
    Student me = new Student("STD24033", "Nicky", "Hariniaina", LocalDate.of(2024, 9, 18),
        new ClassGroup("001", "K2"));

    Fees L1Fees = new Fees("001", "First year fees", 2_000_000.00, 0.00, LocalDate.of(2025, 8, 30), me,
        FeesStatus.IN_PROGRESS, new HashMap<>());

    Fees L2Fees = new Fees("002", "Second year fees", 3_000_000.00, 0.00, LocalDate.of(2026, 8, 30), me,
        FeesStatus.IN_PROGRESS, new HashMap<>());

    Paiment firstPaiment = new MobilePaiment("004", 2_000_000.00, Instant.parse("2025-07-12T00:00:00Z"), "MP002");
    Paiment secondPaiment = new MobilePaiment("001", 1_000_000.00, Instant.parse("2026-09-12T00:00:00Z"), "MP008");

    L1Fees.payFees(firstPaiment);
    L2Fees.payFees(secondPaiment);

    List<Fees> feesList = new ArrayList<>();
    feesList.add(L1Fees);
    feesList.add(L2Fees);

    Statistics stat = new Statistics();

    assertEquals(1, stat.getLateFees(feesList, Instant.parse("2027-04-12T00:00:00Z")).size());

  }
}
