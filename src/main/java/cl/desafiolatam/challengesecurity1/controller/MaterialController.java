package cl.desafiolatam.challengesecurity1.controller;

import cl.desafiolatam.challengesecurity1.dto.Material;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MaterialController {

    @GetMapping("/materiales")
    public ModelAndView home(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("materiales");
        modelAndView.addObject("material", new Material());
        modelAndView.addObject("materiales", session.getAttribute("materiales"));
        return modelAndView;
    }

    @PostMapping("/materiales/agregar")
    public RedirectView home(HttpSession session, @ModelAttribute Material material) {
        List<Material> materials = new ArrayList<>();
        if (session.getAttribute("materiales") != null)
            materials.addAll((List<Material>) session.getAttribute("materiales"));
        materials.add(material);
        session.setAttribute("materiales", materials);

        return new RedirectView("/materiales");
    }

}