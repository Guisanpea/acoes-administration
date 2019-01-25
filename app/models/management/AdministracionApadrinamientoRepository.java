package models.management;

import models.entities.AdministracionApadrinamiento;
import play.db.jpa.JPAApi;

import javax.inject.Inject;

public class AdministracionApadrinamientoRepository extends AbstractRepository<AdministracionApadrinamiento> {
    @Inject
    public AdministracionApadrinamientoRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }
}
