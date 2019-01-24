package controllers;

import lombok.SneakyThrows;
import models.entities.Ingreso;
import models.entities.Partida;
import models.entities.Proyecto;
import models.entities.Usuario;
import models.forms.IngresoForm;
import models.management.IngresoRepository;
import models.management.PartidaRepository;
import models.management.ProyectoRepository;
import models.management.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.create_ingreso;
import views.html.index_ingresos;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

//TODO @Security.Authenticated(UserAuthenticator.class)
public class IngresoController extends Controller {

    private final IngresoRepository ingresoRepository;
    private final ProyectoRepository proyectoRepository;
    private final PartidaRepository partidaRepository;
    private final UsuarioRepository usuarioRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext ec;

    @Inject
    public IngresoController(IngresoRepository ingresoRepository, ProyectoRepository proyectoRepository, PartidaRepository partidaRepository, UsuarioRepository usuarioRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.ingresoRepository = ingresoRepository;
        this.proyectoRepository = proyectoRepository;
        this.partidaRepository = partidaRepository;
        this.usuarioRepository = usuarioRepository;
        this.formFactory = formFactory;
        this.ec = ec;
    }


    public CompletionStage<Result> listIngresos() {
        return ingresoRepository.list().thenApplyAsync(ingresoList ->
                    ok(index_ingresos.render(ingresoList))
              , ec.current()
        );
    }

    public CompletionStage<Result> renderCreateIngreso() {
        Form<IngresoForm> ingresoForm = formFactory.form(IngresoForm.class);

        return proyectoRepository.list().thenCompose(proyectos -> {
            List<String> proyectosNames = proyectos.stream()
                  .map(Proyecto::getNombre)
                  .collect(Collectors.toCollection(ArrayList::new));
            return partidaRepository.list().thenApplyAsync(partidas -> {
                      List<String> partidasNames = partidas.stream()
                            .map(Partida::getNombre)
                            .collect(Collectors.toCollection(ArrayList::new));
                      return ok(create_ingreso.render(ingresoForm, proyectosNames, partidasNames));
                  }, ec.current()
            );
        });
    }

    @SneakyThrows({ExecutionException.class, InterruptedException.class})
    public Result createIngreso() {
        IngresoForm ingresoForm = formFactory.form(IngresoForm.class).bindFromRequest().get();
        Partida partida = partidaRepository.findByNombre(ingresoForm.nombrePartida).toCompletableFuture().get();
        Proyecto proyecto = proyectoRepository.findByNombre(ingresoForm.nombreProyecto).toCompletableFuture().get();

        String usuarioEmail = session("email");
        Usuario usuario = usuarioRepository.findByEmail(usuarioEmail).toCompletableFuture().get();

        Ingreso ingreso = new Ingreso();
        BeanUtils.copyProperties(ingresoForm, ingreso);
        ingreso.setPartida(partida);
        ingreso.setProyecto(proyecto);
        ingreso.setCreador(usuario);

        ingresoRepository.add(ingreso).toCompletableFuture().get();
        return redirect(routes.SocioController.listSocios());

        /*
        return partidaRepository.findByNombre(sPartida).thenCompose(partida ->
              proyectoRepository.findByNombre(sProyecto).thenCompose(proyecto -> {
                  String usuarioEmail = session("email");
                  return usuarioRepository.findByEmail(usuarioEmail).thenCompose(usuario -> {
                      newIngreso.setPartida(partida);
                      newIngreso.setProyecto(proyecto);
                      newIngreso.setCreador(usuario);
                      return ingresoRepository.add(newIngreso).thenApplyAsync(i ->
                                  redirect(routes.SocioController.listSocios())
                            , ec.current());
                  });
              })
        );
        */
    }
}
