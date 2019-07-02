package makeitwork.mijninzet.service.CourseSchedule;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.CourseSchedule.Agenda;
import makeitwork.mijninzet.model.CourseSchedule.CourseSchedule;
import makeitwork.mijninzet.model.HolidaySchedule;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.CourseScheduleRepository;
import makeitwork.mijninzet.repository.HolidayScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;


@Service
public class CourseAgendaService {

    @Autowired
    HolidayScheduleRepository holidays;

    @Autowired
    CourseScheduleRepository courseScheduleRepository;

    @Autowired
    CohortRepository cohorts;

    public List<Agenda> addDatesAtBeginning(List<Agenda> agenda, LocalDate begin, LocalDate closing){
        //first check if de first agenda item is later then the (begin)startdate
        var first= agenda.get(0).getDate();
        if (!first.equals(begin)){
            for (LocalDate date = begin; date.isBefore(first); date = date.plusDays(1)) {
                Agenda item = new Agenda();
                item.setDate(date);
                agenda.add(item);
            }
        }
        Collections.sort(agenda);
        //second check if first date=Monday, if not count backwards untill the first Monday
        //deze functie java.lang.OutOfMemoryError: Java heap space
        first= agenda.get(0).getDate().plusDays(0);
        if (!first.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            do {
                first = first.plusDays(-1);
                Agenda item = new Agenda();
                item.setDate(first);
                agenda.add(item);
            }
            while (!first.getDayOfWeek().equals(DayOfWeek.MONDAY));

        }
        ;
        return agenda;
    }

    public List<Agenda> addWorkshops(List<Agenda> agenda,LocalDate begin, LocalDate closing) {
        try {
            List<CourseSchedule> workshops = courseScheduleRepository.findAll();
            List<CourseSchedule> courses=new ArrayList<>();
            if(!workshops.isEmpty()) {
                for (CourseSchedule course : workshops) {
                    var courseDate = course.getDate();
                    if (courseDate.isAfter(begin) && courseDate.isBefore(closing.plusDays(-1))) courses.add(course);
                }
            }
            workshops.clear();
            workshops.addAll(courses);
            if (!workshops.isEmpty()) {
                for (CourseSchedule course : workshops) {
                    Agenda agendaItem = new Agenda();
                    setAgendaItem(agendaItem,course);
                    agendaItem.setDate(course.getDate());
                    agenda.add(agendaItem);
                }
            }
    } catch(
    NullPointerException e)
    {
        System.out.printf("\n\nGeen workshops: %s\n\n", e);
    }
        return agenda;
    }
    public Agenda setAgendaItem(Agenda agenda, CourseSchedule course){
        agenda.setDate(course.getDate());
        agenda.setDayPart(course.getPartOfDay().ordinal());
        switch (agenda.getDayPart()){
            case 0: {
                agenda.setCohortMorning(course.getCohort().getCohortName());
                agenda.setSubjectMorning(course.getSubject().getSubjectName());
                break;
            }
            case 1: {
                agenda.setCohortAfternoon(course.getCohort().getCohortName());
                agenda.setSubjectAfternoon(course.getSubject().getSubjectName());
                break;
            }
            case 2: {
                agenda.setCohortNight(course.getCohort().getCohortName());
                agenda.setSubjectNight(course.getSubject().getSubjectName());
                break;
            }
            default:break;
            }
        return agenda;
    }
    public List<Agenda> addHolidays(List<Agenda> agenda,LocalDate begin, LocalDate closing) {

        try {
            List<HolidaySchedule> daysOff = holidays.findAll();
            if (!daysOff.isEmpty()) {
                for (HolidaySchedule holiday : daysOff) {
                    Agenda agendaItem = new Agenda();
                    agendaItem.setDate(holiday.getLocalDate());
                    agendaItem.setDescriptionTop(holiday.getDescription());
                    agenda.add(agendaItem);
                }
            }
        }catch (NullPointerException e) {
            System.out.printf("\n\nGeen vakantierooster: %s\n\n",e);
        };
        return agenda;
    }

    public List<Agenda> addMissingDates(List<Agenda> agenda,LocalDate begin, LocalDate closing) {
        for (LocalDate date = begin; date.isBefore(closing); date = date.plusDays(1)) {
        var present=false;
            for (Agenda item: agenda) {
            if (item.getDate()==date ){
                present=true;
                break;}
            }
        if (present==false) {
            Agenda item=new Agenda();
            item.setDate(date);
            agenda.add(item);
            }
        }
        return agenda;
    }
    public List<Agenda> removeAfterClosing(List<Agenda> agenda, LocalDate closing){
        List<Agenda> beforeClosing=new ArrayList<>();if(!agenda.isEmpty()){
            for (Agenda item:agenda) {
                if (item.getDate().isBefore(closing)) beforeClosing.add(item);
            }
            agenda.clear();
            agenda.addAll(beforeClosing);
        }
        return agenda;
    }
    public List<Agenda> onlyCohort(List<Agenda> agenda, String cohortName){
        List<Agenda> cohort= new ArrayList<>();
        if (cohortName!=""){
            for (Agenda item:agenda) {
                if (item.getCohortAfternoon()==cohortName||
                    item.getCohortMorning()==cohortName||
                    item.getCohortNight()==cohortName){
                    cohort.add(item);
                }
            }
        }
        return cohort;
    }
    public Cohort cohort(String cohortName){
        System.out.printf("dit cohort: %s",cohorts.findByCohortName(cohortName));
        return cohorts.findByCohortName(cohortName);
    }

    public List<Agenda> Agenda(LocalDate begin, LocalDate closing,String cohortName){
        List<Agenda> agenda=new ArrayList<>();
        addWorkshops(agenda,begin,closing);
        if (cohortName!="") onlyCohort(agenda,cohortName);
        addHolidays(agenda,begin,closing);
        Collections.sort(agenda);
        addDatesAtBeginning(agenda,begin,closing);
        Collections.sort(agenda);
        addMissingDates(agenda,begin,closing);
        Collections.sort(agenda);
        return agenda;
    }
}
