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

    @GetMapping("/materiales")//metodo get que abarca la ruta materiales
    public ModelAndView home(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("materiales");//Crea un objeto tipo VistaModelo de nombre materiales
        modelAndView.addObject("material", new Material());//luego, le agrega como atributo un objeto de tipo Material () de nombre "material"
        modelAndView.addObject("materiales", session.getAttribute("materiales"));//luego, le agrega como atributo todos los materiales que esten en la sesión http q ingresa por parámetro y los nombra "materiales"
        return modelAndView;// retorna todo el objeto
    }

    @PostMapping("/materiales/agregar")//Le asigna la solicitud http post al metodo
    public RedirectView home(HttpSession session, @ModelAttribute Material material) {//Los valores recibidos desde el formulario son almacenados directamente en la sesión. Se agrega a una lista de valores en la que se ingresa desde las vistas con el nombre valores.

        List<Material> materials = new ArrayList<>();									//Se crea una lista de "Material"es 
        if (session.getAttribute("materiales") != null)									//Si la sesion http tiene la lista de "materiales", copia sus elementos a la lista de este metodo (materials)
            materials.addAll((List<Material>) session.getAttribute("materiales"));
        materials.add(material);														//Luego le agrega a la sesion, el valor recibido desde el formulario y redirige a la ruta "/materiales"
        session.setAttribute("materiales", materials);

        return new RedirectView("/materiales");
    }

}