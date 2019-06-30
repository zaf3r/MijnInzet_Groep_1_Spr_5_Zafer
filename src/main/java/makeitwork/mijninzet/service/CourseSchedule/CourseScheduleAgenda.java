package makeitwork.mijninzet.service.CourseSchedule;

import makeitwork.mijninzet.model.CourseSchedule.Agenda;
import makeitwork.mijninzet.model.CourseSchedule.CourseSchedule;
import makeitwork.mijninzet.model.HolidaySchedule;
import makeitwork.mijninzet.repository.CourseScheduleRepository;
import makeitwork.mijninzet.repository.HolidayScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;


@Service
public class CourseScheduleAgenda {

    @Autowired
    HolidayScheduleRepository holidays;

    @Autowired
    CourseScheduleRepository courseScheduleRepository;

    public List<Agenda> addDatesAtBeginning(List<Agenda> agenda, LocalDate begin, LocalDate closing){
        //first check if de first agenda item is later then the (begin)startdate
        var first= agenda.get(0).getDate();
        if (!first.equals(begin)){
            for (LocalDate date = begin.plusDays(-1); date.isBefore(first); date = date.plusDays(1)) {
                Agenda item = new Agenda();
                item.setDate(date);
                agenda.add(item);
            }
        }
        //second check if first date=Monday, if not count backwards untill the first Monday
        first= agenda.get(0).getDate();
        var WEEKDAY="1";//monday
        while (!first.getDayOfWeek().equals(WEEKDAY)){
            Agenda item=new Agenda();
            item.setDate(first);
            agenda.add(item);
            first.plusDays(-1);
        }
        Collections.sort(agenda);
        return agenda;
    }

    public List<Agenda> addWorkshops(List<Agenda> agenda,LocalDate begin, LocalDate closing) {
        List<CourseSchedule> workshops = courseScheduleRepository.findAllByDateAfter(begin.plusDays(-1));
        workshops.removeAll(courseScheduleRepository.findAllByDateAfter(closing));
        if (!workshops.isEmpty()) {
            for (CourseSchedule course : workshops) {
                Agenda agendaItem = new Agenda();
                agendaItem.setDate(course.getDate());
                agendaItem.setDayPart(course.getPartOfDay());
                agendaItem.setDescriptionTop(course.getCohort().getCohortName());
                agendaItem.setDescriptionMiddle(course.getSubject().getSubjectName());
                agendaItem.setId(course.getCourseId());
                agenda.add(agendaItem);
            }
        }
        return agenda;
    }
    public List<Agenda> addHolidays(List<Agenda> agenda,LocalDate begin, LocalDate closing) {
        List<HolidaySchedule> daysOff =holidays.findAllByLocalDateAfter(begin.plusDays(-1));
        daysOff.removeAll(holidays.findAllByLocalDateAfter(closing));
        if (!daysOff.isEmpty()){
            for (HolidaySchedule holiday: daysOff) {
                Agenda agendaItem=new Agenda();
                agendaItem.setDate(holiday.getLocalDate());
                agendaItem.setDescriptionTop(holiday.getDescription());
                agenda.add(agendaItem);
            }}
        return agenda;
    }
    public List<Agenda> addMissingDates(List<Agenda> agenda,LocalDate begin, LocalDate closing) {
        ListIterator<Agenda> agendaIterator = agenda.listIterator();
        var insertDate=begin;
        while (agendaIterator.hasNext()){
            if (agendaIterator.next().getDate()!=insertDate){
                    Agenda item=new Agenda();
                    item.setDate(insertDate);
                    agendaIterator.add(item);
                    insertDate=insertDate.plusDays(1);
                }
            }
        return agenda;
    }

    public List<Agenda> Agenda(LocalDate begin, LocalDate closing){
        List<Agenda> agenda=new ArrayList<>();
        addHolidays(agenda,begin,closing);
        addWorkshops(agenda,begin,closing);
        Collections.sort(agenda);
        addDatesAtBeginning(agenda,begin,closing);
        addMissingDates(agenda,begin,closing);
        return agenda;
    }
}
