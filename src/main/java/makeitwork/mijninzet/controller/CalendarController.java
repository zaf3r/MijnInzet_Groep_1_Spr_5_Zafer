package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Event;
import makeitwork.mijninzet.repository.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
public class CalendarController {

    EventRepository eventRepository;


    @RequestMapping(value="/docentenrooster", method= RequestMethod.GET)
        public ModelAndView index() {
            ModelAndView modelAndView = new ModelAndView("teacherSchedule");
            return modelAndView;
        }

    @RequestMapping(value="/events", method=RequestMethod.GET)
    public List<Event> getEventsInRange(@RequestParam(value = "start", required = true) String start,
                                        @RequestParam(value = "end", required = true) String end) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
             e.printStackTrace();
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(),
                ZoneId.systemDefault());

        LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(),
                ZoneId.systemDefault());


        return eventRepository.findByDateBetween(startDateTime, endDateTime);
    }
    }

