package controllers;

import models.management.SocioRepository;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.listasocios;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class AgentController extends Controller {

    private final SocioRepository socioRepository;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public AgentController(SocioRepository socioRepository, HttpExecutionContext ec) {
        this.socioRepository = socioRepository;
        this.httpExecutionContext = ec;
    }

    public CompletionStage<Result> listSocios() {
        session("userId");

        return socioRepository.list().thenApplyAsync(socioList ->
                    ok(listasocios.render(socioList))
              , httpExecutionContext.current()
        );
    }
}
