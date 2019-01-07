package controllers;

import models.entities.Socio;
import models.management.SocioRepository;
import models.management.UsuarioRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.listasocios;

import javax.inject.Inject;
import java.util.List;

public class AgentController extends Controller {

    private SocioRepository socioRepository;

    @Inject
    public AgentController(SocioRepository socioRepository){
        this.socioRepository = socioRepository;
    }

    public Result listSocios() {
        session("userId");

        List<Socio> socioList = socioRepository.list();

        return ok(listasocios.render(socioList));
    }
}
