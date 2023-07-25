package clases;

import java.io.Serializable;
import java.text.DecimalFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import modelo.JugadorTirosContraEquipo;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControllerEstadisticaNormal implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4956220859884456148L;
	private Integer robos = 0;
    private Integer puntos = 0;
    private Integer tapones = 0;
    private Integer perdidas = 0;
    private Integer asistencias = 0;
    private Integer totalRebotes = 0;
    private Integer triplesMetidos = 0;
    private Integer reboteOfensivo = 0;
    private Integer reboteDefensivo = 0;
    private Integer faltasPersonales = 0;
    private Integer tirosCampoMetidos = 0;
    private Integer triplesIntentados = 0;
    private Integer tirosLibresMetidos = 0;
    private Integer tirosCampoIntentados = 0;
    private Integer tirosLibresIntentados = 0;
    private Integer tirosDosMetidos = 0;
    private Integer tirosDosIntentados = 0;

    private String cuarto = "";

    private Double triplesPorcentaje = 0.0;
    private Double tirosLibresPorcentaje = 0.0;
    private Double tirosCampoPorcentaje = 0.0;
    private Double tirosDosPorcentaje = 0.0;

    private Integer masMenos = 0;
    private String ubicacion = "";
    private String cuando = "";
    private Integer partidosJugados = 0;
    private DecimalFormat df = new DecimalFormat("0.00");
    private DecimalFormat media = new DecimalFormat("0.00");
    private boolean tienePartido=false;
    private String situacionTiro="";
    
	
	public ControllerEstadisticaNormal(JugadorTirosContraEquipo tirosJugador) {
		this.triplesIntentados = tirosJugador.getTresPuntosIntentados();
		this.triplesMetidos = tirosJugador.getTresPuntosMetidos();

		this.tirosCampoIntentados = tirosJugador.getTirosCampoIntentados();
		this.tirosCampoMetidos = tirosJugador.getTirosCampoMetidos();
		this.puntos =tirosJugador.getTresPuntosMetidos()*3 + tirosJugador.getDosPuntosMetidos();
		
		this.tirosCampoIntentados = tirosJugador.getTirosCampoIntentados();
		this.tirosCampoMetidos = tirosJugador.getTirosCampoMetidos();
		
		this.tirosDosIntentados = tirosJugador.getDosPuntosIntentados();
		this.tirosDosMetidos = tirosJugador.getDosPuntosMetidos();
		
		this.tirosDosPorcentaje = tirosJugador.getPorcentajeDosPuntos();
		this.triplesPorcentaje = tirosJugador.getPorcentajeTresPuntos();
		this.tirosCampoPorcentaje = tirosJugador.getPorcentajeTirosCampo();
		
		this.situacionTiro = "Estadisticas del Filtro de Tiros";
	}


    public void calcularTirosDosPuntos() {
        tirosDosIntentados = tirosCampoIntentados - triplesIntentados;
        tirosDosMetidos = tirosCampoMetidos - triplesMetidos;

        if (tirosDosIntentados != 0) {
            tirosDosPorcentaje = (Double.valueOf(tirosDosMetidos) / Double.valueOf(tirosDosIntentados));
        } else {
            tirosDosPorcentaje = 0.0;
        }

        if (triplesIntentados != 0) {
            triplesPorcentaje = (Double.valueOf(triplesMetidos) / Double.valueOf(triplesIntentados));
        } else {
            triplesPorcentaje = 0.0;
        }

        if (tirosLibresIntentados != 0) {
            tirosLibresPorcentaje = (Double.valueOf(tirosLibresMetidos) / Double.valueOf(tirosLibresIntentados));
        } else {
            tirosLibresPorcentaje = 0.0;
        }

        if (tirosCampoIntentados != 0) {
            tirosCampoPorcentaje = (Double.valueOf(tirosCampoMetidos) / Double.valueOf(tirosCampoIntentados));
        } else {
            tirosCampoPorcentaje = 0.0;
        }
    }

    public String devolverPorcentajeTirosCampo() {
        return df.format(tirosCampoPorcentaje);
    }

    public String devolverPorcentajeTirosLibres() {
        return df.format(tirosLibresPorcentaje);
    }

    public String devolverPorcentajeTriples() {
        return df.format(triplesPorcentaje);
    }

    public String devolverPorcentajeTirosDeDos() {
        return df.format(tirosDosPorcentaje);
    }
    
    public String calcularMedia(Integer atributo){
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(atributo) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaPuntos() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(puntos) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaAsistencias() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(asistencias) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaTotalRebotes() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(totalRebotes) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaTirosCampoMetidos() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(tirosCampoMetidos) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaTirosCampoIntentados() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(tirosCampoIntentados) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaTirosDosMetidos() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(tirosDosMetidos) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaTirosDosIntentados() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(tirosDosIntentados) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaTriplesMetidos() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(triplesMetidos) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaTriplesIntentados() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(triplesIntentados) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaTirosLibresMetidos() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(tirosLibresMetidos) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaTirosLibresIntentados() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(tirosLibresIntentados) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaReboteDefensivo() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(reboteDefensivo) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaReboteOfensivo() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(reboteOfensivo) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaRobos() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(robos) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaTapones() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(tapones) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaPerdidas() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(perdidas) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaFaltasPersonales() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(faltasPersonales) / Double.valueOf(partidosJugados));
        }
        return "0";
    }

    public String mediaMasMenos() {
        if (partidosJugados != 0) {
            return media.format(Double.valueOf(masMenos) / Double.valueOf(partidosJugados));
        }
        return "0";
    }
    
	public String getPorcentajeTirosCampo() {
		int porcentaje = (int) (tirosCampoPorcentaje * 100);
		return  porcentaje + "%";
	}

    
}
