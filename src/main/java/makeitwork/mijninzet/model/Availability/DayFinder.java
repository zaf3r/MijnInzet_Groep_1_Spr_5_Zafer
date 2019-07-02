package makeitwork.mijninzet.model.Availability;

import java.util.List;

public interface DayFinder<T,S> {

    T findDay(S weekday, List<T> dayList);
}
