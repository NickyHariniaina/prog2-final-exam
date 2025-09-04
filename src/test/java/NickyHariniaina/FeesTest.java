package NickyHariniaina;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import NickyHariniaina.FeesHistory.ClassGroup;
import NickyHariniaina.FeesHistory.Fees;
import NickyHariniaina.FeesHistory.FeesStatus;
import NickyHariniaina.FeesHistory.MobilePaiment;
import NickyHariniaina.FeesHistory.Paiment;
import NickyHariniaina.FeesHistory.Student;

public class FeesTest {

  @Test
  public void should_return_fees_status_ok() {

    Student me = new Student("STD24033", "Nicky", "Hariniaina", LocalDate.of(2024, 9, 18),
        new ClassGroup("001", "K2"));

    Fees L1Fees = new Fees("001", "First year fees", 2_000_000.00, LocalDate.of(2025, 8, 30), me,
        FeesStatus.IN_PROGRESS);

    Paiment myPaiment = new MobilePaiment("001", 1_000_000.00, Instant.now(), "MP001");

    assertEquals(FeesStatus.IN_PROGRESS, L1Fees.getFeesStatusAt(Instant.parse("2025-04-13T23:58:11Z")));
    assertEquals(FeesStatus.LATE, L1Fees.getFeesStatusAt(Instant.parse("2025-10-12T00:00:00Z")));
  }

}
