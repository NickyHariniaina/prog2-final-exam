package NickyHariniaina;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import NickyHariniaina.FeesHistory.BankPaiment;
import NickyHariniaina.FeesHistory.ClassGroup;
import NickyHariniaina.FeesHistory.Fees;
import NickyHariniaina.FeesHistory.FeesStatus;
import NickyHariniaina.FeesHistory.MobilePaiment;
import NickyHariniaina.FeesHistory.Paiment;
import NickyHariniaina.FeesHistory.Student;

public class FeesTest {

  @Test
  public void should_return_late_fees_status_ok() {

    Student me = new Student("STD24033", "Nicky", "Hariniaina", LocalDate.of(2024, 9, 18),
        new ClassGroup("001", "K2"));

    Fees L1Fees = new Fees("001", "First year fees", 2_000_000.00, 0.00, LocalDate.of(2025, 8, 30), me,
        FeesStatus.IN_PROGRESS, new HashMap<>());
    Paiment firstPaiment = new MobilePaiment("001", 1_000_000.00, Instant.parse("2025-04-12T00:00:00Z"), "MP001");

    L1Fees.payFees(firstPaiment);

    assertEquals(FeesStatus.LATE, L1Fees.getFeesStatusAt(Instant.parse("2025-10-12T00:00:00Z")));
  }

  @Test
  public void should_return_in_progress_fees_status_ok() {

    Student me = new Student("STD24033", "Nicky", "Hariniaina", LocalDate.of(2024, 9, 18),
        new ClassGroup("001", "K2"));

    Fees L1Fees = new Fees("001", "First year fees", 2_000_000.00, 0.00, LocalDate.of(2025, 8, 30), me,
        FeesStatus.IN_PROGRESS, new HashMap<>());
    Paiment firstPaiment = new MobilePaiment("001", 1_000_000.00, Instant.parse("2025-04-12T00:00:00Z"), "MP001");
    Paiment secondPaiment = new BankPaiment("002", 3_000_000, Instant.parse("2025-09-23T00:00:00Z"), "BK004");

    L1Fees.payFees(firstPaiment);

    assertEquals(FeesStatus.IN_PROGRESS, L1Fees.getFeesStatusAt(Instant.parse("2025-05-12T00:00:00Z")));

  }

  @Test
  public void should_return_paid_fees_status_ok() {

    Student me = new Student("STD24033", "Nicky", "Hariniaina", LocalDate.of(2024, 9, 18),
        new ClassGroup("001", "K2"));

    Fees L1Fees = new Fees("001", "First year fees", 2_000_000.00, 0.00, LocalDate.of(2025, 8, 30), me,
        FeesStatus.IN_PROGRESS, new HashMap<>());
    Paiment firstPaiment = new MobilePaiment("001", 2_000_000.00, Instant.parse("2025-04-12T00:00:00Z"), "MP001");

    L1Fees.payFees(firstPaiment);

    assertEquals(FeesStatus.PAID, L1Fees.getFeesStatusAt(Instant.parse("2025-05-12T00:00:00Z")));

  }

  @Test
  public void should_return_overpaid_fees_status_ok() {

    Student me = new Student("STD24033", "Nicky", "Hariniaina", LocalDate.of(2024, 9, 18),
        new ClassGroup("001", "K2"));

    Fees L1Fees = new Fees("001", "First year fees", 2_000_000.00, 0.00, LocalDate.of(2025, 8, 30), me,
        FeesStatus.IN_PROGRESS, new HashMap<>());
    Paiment firstPaiment = new MobilePaiment("001", 4_000_000.00, Instant.parse("2025-04-12T00:00:00Z"), "MP001");

    L1Fees.payFees(firstPaiment);

    assertEquals(FeesStatus.OVERPAID, L1Fees.getFeesStatusAt(Instant.parse("2025-05-12T00:00:00Z")));

  }
}
