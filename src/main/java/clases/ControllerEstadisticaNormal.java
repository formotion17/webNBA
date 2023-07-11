package clases;

import java.text.DecimalFormat;

public class ControllerEstadisticaNormal {

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

	 // Getter Methods 
    public Integer getAsistencias() {
        return asistencias;
    }

    public String getCuarto() {
        return cuarto;
    }

    public Integer getFaltasPersonales() {
        return faltasPersonales;
    }

    public Integer getPerdidas() {
        return perdidas;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public Integer getReboteDefensivo() {
        return reboteDefensivo;
    }

    public Integer getReboteOfensivo() {
        return reboteOfensivo;
    }

    public Integer getRobos() {
        return robos;
    }

    public Integer getTapones() {
        return tapones;
    }

    public Integer getTirosCampoIntentados() {
        return tirosCampoIntentados;
    }

    public Integer getTirosCampoMetidos() {
        return tirosCampoMetidos;
    }

    public Double getTirosCampoPorcentaje() {
        return tirosCampoPorcentaje;
    }

    public Integer getTirosLibresIntentados() {
        return tirosLibresIntentados;
    }

    public Integer getTirosLibresMetidos() {
        return tirosLibresMetidos;
    }

    public Double getTirosLibresPorcentaje() {
        return tirosLibresPorcentaje;
    }

    public Integer getTotalRebotes() {
        return totalRebotes;
    }

    public Integer getTriplesIntentados() {
        return triplesIntentados;
    }

    public Integer getTriplesMetidos() {
        return triplesMetidos;
    }

    public Double getTriplesPorcentaje() {
        return triplesPorcentaje;
    }

	 // Setter Methods 
    public void setAsistencias(Integer asistencias) {
        this.asistencias = asistencias;
    }

    public void setCuarto(String cuarto) {
        this.cuarto = cuarto;
    }

    public void setFaltasPersonales(Integer faltasPersonales) {
        this.faltasPersonales = faltasPersonales;
    }

    public void setPerdidas(Integer perdidas) {
        this.perdidas = perdidas;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public void setReboteDefensivo(Integer reboteDefensivo) {
        this.reboteDefensivo = reboteDefensivo;
    }

    public void setReboteOfensivo(Integer reboteOfensivo) {
        this.reboteOfensivo = reboteOfensivo;
    }

    public void setRobos(Integer robos) {
        this.robos = robos;
    }

    public void setTapones(Integer tapones) {
        this.tapones = tapones;
    }

    public void setTirosCampoIntentados(Integer tirosCampoIntentados) {
        this.tirosCampoIntentados = tirosCampoIntentados;
    }

    public void setTirosCampoMetidos(Integer tirosCampoMetidos) {
        this.tirosCampoMetidos = tirosCampoMetidos;
    }

    public void setTirosCampoPorcentaje(Double tirosCampoPorcentaje) {
        this.tirosCampoPorcentaje = tirosCampoPorcentaje;
    }

    public void setTirosLibresIntentados(Integer tirosLibresIntentados) {
        this.tirosLibresIntentados = tirosLibresIntentados;
    }

    public void setTirosLibresMetidos(Integer tirosLibresMetidos) {
        this.tirosLibresMetidos = tirosLibresMetidos;
    }

    public void setTirosLibresPorcentaje(Double tirosLibresPorcentaje) {
        this.tirosLibresPorcentaje = tirosLibresPorcentaje;
    }

    public void setTotalRebotes(Integer totalRebotes) {
        this.totalRebotes = totalRebotes;
    }

    public void setTriplesIntentados(Integer triplesIntentados) {
        this.triplesIntentados = triplesIntentados;
    }

    public void setTriplesMetidos(Integer triplesMetidos) {
        this.triplesMetidos = triplesMetidos;
    }

    public void setTriplesPorcentaje(Double triplesPorcentaje) {
        this.triplesPorcentaje = triplesPorcentaje;
    }

    public Integer getMasMenos() {
        return masMenos;
    }

    public void setMasMenos(Integer masMenos) {
        this.masMenos = masMenos;
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

    public Integer getTirosDosMetidos() {
        return tirosDosMetidos;
    }

    public void setTirosDosMetidos(Integer tirosDosMetidos) {
        this.tirosDosMetidos = tirosDosMetidos;
    }

    public Integer getTirosDosIntentados() {
        return tirosDosIntentados;
    }

    public void setTirosDosIntentados(Integer tirosDosIntentados) {
        this.tirosDosIntentados = tirosDosIntentados;
    }

    public Double getTirosDosPorcentaje() {
        return tirosDosPorcentaje;
    }

    public void setTirosDosPorcentaje(Double tirosDosPorcentaje) {
        this.tirosDosPorcentaje = tirosDosPorcentaje;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCuando() {
        return cuando;
    }

    public void setCuando(String cuando) {
        this.cuando = cuando;
    }

    public Integer getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(Integer partidosJugados) {
        this.partidosJugados = partidosJugados;
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

    public boolean isTienePartido() {
        return tienePartido;
    }

    public void setTienePartido(boolean tienePartido) {
        this.tienePartido = tienePartido;
    }
    
    
}
