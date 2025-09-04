package NickyHariniaina.FeesHistory;

import java.time.LocalDate;
import java.util.HashMap;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Student {

  private final String id;
  private final String firstname;
  private final String lastname;
  private final LocalDate entryDate;
  private ClassGroup currentGroup;
  private HashMap<LocalDate, ClassGroup> groupHistory = new HashMap<>();

  public Student(String id, String firstname, String lastname, LocalDate entryDate, ClassGroup currentGroup) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.entryDate = entryDate;
    this.currentGroup = currentGroup;
    this.groupHistory.put(LocalDate.now(), this.currentGroup);
  }
}
