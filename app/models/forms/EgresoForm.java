package models.forms;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EgresoForm {
        public enum TipoBeneficiario {
            Alumno,
            Colaborador,
            Tercero,
            Socio
        }
        @NotNull
        public Date fecha;
        @NotNull
        public String concepto;
        @NotNull
        public double importe;
        @NotNull
        public TipoBeneficiario tipoBeneficiario;
        public String observaciones;
        @NotNull
        public String partida;
        public String nombreBeneficiarioAlumno;
        public String nombreBeneficiarioColaborador;
        public String nombreProyecto;
        public String nombreBeneficiarioSocio;
        public String beneficiarioTercero;
}
