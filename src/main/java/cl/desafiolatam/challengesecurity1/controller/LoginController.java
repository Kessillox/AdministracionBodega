package cl.desafiolatam.challengesecurity1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {//se encarga de mostrar la página de inicio de sesión y expone la ruta login.

    @GetMapping("/login") //nos permite simplificar el manejo de los diferentes métodos de Spring MVC y los @RequestMappings
    public ModelAndView login(){//Representa un modelo y una vista devueltos por un controlador, para ser resueltos por un DispatcherServlet.

        return new ModelAndView("login");//Retorna
    }

}
