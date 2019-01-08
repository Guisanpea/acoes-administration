package controllers;

import lombok.SneakyThrows;
import models.entities.Socio;
import models.management.SocioRepository;
import models.management.UsuarioRepository;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.introducirsocio;
import views.html.listasocios;

import javax.inject.Inject;
import java.util.List;

public class SocioController extends Controller {

    private SocioRepository socioRepository;
    private FormFactory formFactory;

    @Inject
    public SocioController(SocioRepository socioRepository, FormFactory formFactory){
        this.socioRepository = socioRepository;
        this.formFactory = formFactory;
    }

    public Result listSocios() {
        List<Socio> socioList = socioRepository.list();
        return ok(listasocios.render(socioList));
    }

    public Result renderAddSocio() {
        Form<Socio> socioForm = formFactory.form(Socio.class);

        return ok(introducirsocio.render(socioForm));
    }

    @SneakyThrows(Exception.class)
    public Result addSocio() {
        Socio newSocio = formFactory.form(Socio.class).bindFromRequest(
                "nombre", "apellidos", "estado", "nif",
                "direccion", "poblacion",
                "codigoPostal", "provincia", "telefonoFijo", "telefonoMovil",
                "email", "relacion", "certificado", "sector",
                "fechaAlta", "fechaBaja", "observaciones").get();

        socioRepository.add(newSocio).toCompletableFuture().get();

        return redirect(routes.SocioController.listSocios());
    }
}
