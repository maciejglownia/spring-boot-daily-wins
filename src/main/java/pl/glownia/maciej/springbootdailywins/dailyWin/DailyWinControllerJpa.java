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
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
class DailyWinControllerJpa {

    public DailyWinControllerJpa(DailyWinRepository dailyWinRepository) {
        super();
        this.dailyWinRepository = dailyWinRepository;
    }

    private DailyWinRepository dailyWinRepository;

    @RequestMapping("list-dailyWins")
    public String listAllDailyWins(ModelMap model) {
        String username = getLoggedInUsername(model);

        List<DailyWin> dailyWins = dailyWinRepository.findByUsername(username);
        model.addAttribute("dailyWins", dailyWins);

        return "listDailyWins";
    }

    @RequestMapping(value = "add-dailyWin", method = RequestMethod.GET)
    public String showNewDailyWinPage(ModelMap model) {
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

        if (result.hasErrors()) {
            return "dailyWin";
        }

        String username = getLoggedInUsername(model);
        dailyWin.setUsername(username);
        dailyWinRepository.save(dailyWin);
        return "redirect:list-dailyWins";
    }

    @RequestMapping("delete-dailyWin")
    public String deleteDailyWin(@RequestParam int id) {
        dailyWinRepository.deleteById(id);
        return "redirect:list-dailyWins";
    }

    @RequestMapping(value = "update-dailyWin", method = RequestMethod.GET)
    public String showUpdateDailyWinPage(@RequestParam int id, ModelMap model) {
        DailyWin dailyWin = dailyWinRepository.findById(id).get();
        model.addAttribute("dailyWin", dailyWin);
        return "dailyWin";
    }

    @RequestMapping(value = "update-dailyWin", method = RequestMethod.POST)
    public String updateDailyWinPage(ModelMap model, @Valid DailyWin dailyWin, BindingResult result) {

        if (result.hasErrors()) {
            return "dailyWin";
        }

        String username = getLoggedInUsername(model);
        dailyWin.setUsername(username); // avoid to be username lost when updating a task
        dailyWinRepository.save(dailyWin);
        return "redirect:list-dailyWins";
    }

    // It is recommended to directly get the value (username) from Spring Security
    private String getLoggedInUsername(ModelMap model) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
