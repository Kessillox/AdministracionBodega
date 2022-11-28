package cl.desafiolatam.challengesecurity1.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Material {

    private String nombre;
    private Long precio;
    private Date ingreso = new Date();
    private Bodega bodega;

}
