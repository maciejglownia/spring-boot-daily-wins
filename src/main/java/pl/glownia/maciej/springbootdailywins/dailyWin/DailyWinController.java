package pl.glownia.maciej.springbootdailywins.dailyWin;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
class DailyWinController {

    public DailyWinController(DailyWinService dailyWinService) {
        super();
        this.dailyWinService = dailyWinService;
    }

    private DailyWinService dailyWinService;

    @RequestMapping("list-dailyWins")
    public String listAllDailyWins(ModelMap model) {
        String username = getLoggedInUsername(model);
        List<DailyWin> dailyWins = dailyWinService.findByUsername(username);
        model.addAttribute("dailyWins", dailyWins);
        return "listDailyWins";
    }

    @RequestMapping(value = "add-dailyWin", method = RequestMethod.GET)
    public String showNewDailyWinPage(ModelMap model) {
        // Mapping this and present to this form is available on localhost
        String username = getLoggedInUsername(model);
        DailyWin dailyWin = new DailyWin(0, username, "", LocalDate.now(), false);
        model.put("dailyWin", dailyWin);
        return "dailyWin";
    }

    /**
     * 2-way binding concept example from the bean to the form and from the form to the bean
     */
    @RequestMapping(value = "add-dailyWin", method = RequestMethod.POST)
    public String addNewDailyWinPage(ModelMap model, @Valid DailyWin dailyWin, BindingResult result) {

        // protect from go to the list of daily wins
        if (result.hasErrors()) {
            return "dailyWin";
        }

        String username = getLoggedInUsername(model);
        dailyWinService.addDailyWin(username, dailyWin.getDescription(), dailyWin.getTargetDate(), false); // then adding to the list to be able to display
        return "redirect:list-dailyWins"; // use URL so list-dailyWins not a view, so not listDailyWins
    }

    @RequestMapping("delete-dailyWin")
    public String deleteDailyWin(@RequestParam int id) {
        dailyWinService.deleteById(id);
        return "redirect:list-dailyWins";
    }

    @RequestMapping(value = "update-dailyWin", method = RequestMethod.GET)
    public String showUpdateDailyWinPage(@RequestParam int id, ModelMap model) {
        DailyWin dailyWin = dailyWinService.findById(id);
        model.addAttribute("dailyWin", dailyWin);
        return "dailyWin";
    }

    @RequestMapping(value = "update-dailyWin", method = RequestMethod.POST)
    public String updateDailyWinPage(ModelMap model, @Valid DailyWin dailyWin, BindingResult result) {

        // protect from go to the list of daily wins
        if (result.hasErrors()) {
            return "dailyWin";
        }

        String username = getLoggedInUsername(model);
        dailyWin.setUsername(username); // avoid to be username lost when updating a task
        dailyWinService.updateDailyWin(dailyWin);
        return "redirect:list-dailyWins";
    }

    // It is recommended to directly get the value (username) from Spring Security
    private String getLoggedInUsername(ModelMap model) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
