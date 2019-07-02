package makeitwork.mijninzet.controller;

import com.google.gson.Gson;
import makeitwork.mijninzet.repository.HolidayScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class HolidayScheduleController extends AbstractController {
    @Autowired
    HolidayScheduleRepository holidays;

    @PostMapping("/theHolidays")
    public @ResponseBody
    String allHolidaysFromNow(@RequestBody String requestPayload) {
        return new Gson().toJson(holidays.findAllByLocalDateAfter(LocalDate.now()));
    }

}
