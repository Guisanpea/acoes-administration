package controllers;

import beanUtils.PropertyUtils;
import models.entities.Proyecto;
import models.forms.ProyectoForm;
import models.management.ProyectoRepository;
import models.management.TipoProyectoRepository;
import models.management.RegionAyudaRepository;
import org.springframework.beans.BeanUtils;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.create_proyecto;
import views.html.edit_proyecto;
import views.html.index_proyectos;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

// TODO @Security.Authenticated(ProyectoAuthenticator.class)
public class ProyectoController extends Controller {

    private final ProyectoRepository proyectoRepository;
    private final TipoProyectoRepository tipoProyectoRepository;
    private final RegionAyudaRepository regionAyudaRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public ProyectoController(ProyectoRepository proyectoRepository, RegionAyudaRepository regionAyudaRepository, TipoProyectoRepository tipoProyectoRepository, RegionAyudaRepository regionAyudaRepository1, FormFactory formFactory, HttpExecutionContext ec) {
        this.proyectoRepository = proyectoRepository;
        this.tipoProyectoRepository = tipoProyectoRepository;
        this.regionAyudaRepository = regionAyudaRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }

    public CompletionStage<Result> listProyectos() {
        return proyectoRepository.list().thenApplyAsync(proyectosList ->
                        ok(index_proyectos.render(proyectosList))
                , httpExecutionContext.current()
        );
    }

    public Result renderCreateProyecto() {
        Form<ProyectoForm> proyectoForm = formFactory.form(ProyectoForm.class);

        return ok(create_proyecto.render(proyectoForm));
    }

    // TODO Asignar rol,dado que ahora no se puede crear un proyecto sin estar logueado
    //@Security.Authenticated
    public CompletionStage<Result> createProyecto() {
        ProyectoForm newProyecto = formFactory.form(ProyectoForm.class).bindFromRequest().get();

        Proyecto createdProyecto = new Proyecto();
        BeanUtils.copyProperties(newProyecto, createdProyecto);
        return regionAyudaRepository.findByNombre(newProyecto.regionAyuda).thenCompose(region -> {
            return tipoProyectoRepository.findByNombre(newProyecto.nombreTipoProyecto).thenCompose(tipo -> {
                createdProyecto.setTipoProyecto(tipo);
                createdProyecto.setRegionAyuda(region);
                return proyectoRepository.add(createdProyecto).thenApplyAsync(p ->
                                redirect(routes.ProyectoController.listProyectos())
                        , httpExecutionContext.current()
                );
            });
        });

    }

    public CompletionStage<Result> renderEditProyecto(Integer proyectoId) {
        final Form<ProyectoForm> proyectoForm = formFactory.form(ProyectoForm.class);

        return proyectoRepository.findById(proyectoId).thenApplyAsync(proyecto -> {
                    ProyectoForm proyectoToFill = new ProyectoForm();
                    BeanUtils.copyProperties(proyecto, proyectoToFill);
                    proyectoToFill.nombreTipoProyecto = proyecto.getTipoProyecto().getNombre();
                    proyectoToFill.regionAyuda = proyecto.getRegionAyuda().getNombre();
                    return ok(edit_proyecto.render(proyectoForm.fill(proyectoToFill), proyectoId));
                }

                , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> editProyecto(Integer proyectoId) {
        ProyectoForm editedProyecto = formFactory.form(ProyectoForm.class).bindFromRequest().get();

        return proyectoRepository.findById(proyectoId).thenCompose(dbProyecto -> {
            PropertyUtils.copyNonNullProperties(editedProyecto, dbProyecto);
            return tipoProyectoRepository.findByNombre(editedProyecto.nombreTipoProyecto).thenCompose(tipoProyecto -> {
                return regionAyudaRepository.findByNombre(editedProyecto.regionAyuda).thenCompose(region -> {
                    dbProyecto.setTipoProyecto(tipoProyecto);
                    dbProyecto.setRegionAyuda(region);
                    return proyectoRepository.update(dbProyecto).thenApplyAsync(u ->
                                    redirect(routes.ProyectoController.listProyectos())
                            , httpExecutionContext.current()
                    );
                });
            });
        });
    }
}



