package controllers;

import lombok.SneakyThrows;
import models.entities.Proyecto;
import models.entities.RegistroEconomico;
import models.management.RegistroRepository;
import models.management.ProyectoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.indexregistroeconomico;
import views.html.introducirregistro;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RegistroEconomicoController extends Controller {

    private RegistroRepository registroRepository;
    private ProyectoRepository proyectoRepository;
    private FormFactory formFactory;

    @Inject
    public RegistroEconomicoController(RegistroRepository registroRepository, FormFactory formFactory){
        this.registroRepository = registroRepository;
        this.formFactory = formFactory;
    }

    public Result listRegistrosEconomicos(){
        List<RegistroEconomico> registroList = registroRepository.list();

        return ok(indexregistroeconomico.render(registroList));
    }

    public Result renderAddRegistro() {
        Form<RegistroEconomico> registroForm = formFactory.form(RegistroEconomico.class);

        return ok(introducirregistro.render(registroForm));
    }

    public Result addRegistro(Integer proyecto) {
        RegistroEconomico newRegistro = formFactory.form(RegistroEconomico.class).bindFromRequest(
                "fecha", "tipo", "concepto", "importe",
                "observaciones", "codigoServicio",
                "proyecto.id").get();
        Proyecto newProyecto = proyectoRepository.findById(newRegistro.getProyecto().getId());
        newRegistro.setProyecto(newProyecto);

        registroRepository.add(newRegistro);

        return redirect(routes.RegistroEconomicoController.listRegistrosEconomicos());
    }
}
