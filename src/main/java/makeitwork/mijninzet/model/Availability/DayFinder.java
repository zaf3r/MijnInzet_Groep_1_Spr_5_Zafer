package makeitwork.mijninzet.model.Availability;

import java.util.List;

public interface DayFinder<T> {

    T findDay(Weekday weekday, List<T> dayList);
}
