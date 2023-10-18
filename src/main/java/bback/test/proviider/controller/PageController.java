package bback.test.proviider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping(value = {"/index", "/"})
    String index(Model model) {
        model.addAttribute("welcomeContext", "indexer!");
        return "index";
    }

    @GetMapping("/about")
    String about(Model model) {
        model.addAttribute("welcomeContext", "abouter!");
        return "about";
    }
}
