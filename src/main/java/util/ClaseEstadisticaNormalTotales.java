package util;

import java.text.DecimalFormat;

public class ClaseEstadisticaNormalTotales {

	private String temporada = "     ";
	private String tiempo="";
	private String tipoResultado = "";
	private String partidosJugados="GAMES";
	private String partidosQuintetoInicial="Q";
	private String minutos="   MP";

	private String tirosCampoMetidos = "   FG";
	private String tirosCampoIntentados = "  FGA";
	private String tirosCampoPorcentaje = "  %FG";
	
	private String triplesMetidos = "   3P";
	private String triplesIntentados = "  3PA";
	private String triplesPorcentaje = " %3PG";
	
	private String dosPuntosMetidos = "   2P";
	private String dosPuntosIntentados = "  2PA";
	private String dosPuntosPorcentaje = " %2PG";

	private String tirosLibresMetidos = "   FT";
	private String tirosLibresIntentados = "  FTA";
	private String tirosLibresPorcentaje = " %FTG";

	private String reboteOfensivo = "  ORB";
	private String reboteDefensivo = "  DRB";
	private String totalRebotes = "  TRB";
	
	private String asistencias = "  AST";
	private String robos = "  STL";
	private String tapones = "  BLK";
	private String perdidas = "  TOV";
	private String faltasPersonales = "   PF";
	private String puntos = "  PTS";
        
    private int masMenos=0;
    private String tituloCabeceraJugador="";

	public ClaseEstadisticaNormalTotales() {}
        public ClaseEstadisticaNormalTotales(String temporada,String tiempo) {
        this.temporada=temporada;
        this.tiempo=tiempo;
        }

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
		setTituloCabeceraJugador(tiempo);
	}

	public String getTipoResultado() {
		return tipoResultado;
	}

	public void setTipoResultado(String tipoResultado) {
		this.tipoResultado = tipoResultado;
	}

	public String getPartidosJugados() {
		return partidosJugados;
	}

	public void setPartidosJugados(String partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	public String getPartidosQuintetoInicial() {
		return partidosQuintetoInicial;
	}

	public void setPartidosQuintetoInicial(String partidosQuintetoInicial) {
		this.partidosQuintetoInicial = partidosQuintetoInicial;
	}

	public String getMinutos() {
		return minutos;
	}

	public void setMinutos(String minutos) {
		this.minutos = minutos;
	}

	public String getTirosCampoMetidos() {
		return tirosCampoMetidos;
	}

	public void setTirosCampoMetidos(String tirosCampoMetidos) {
		this.tirosCampoMetidos = tirosCampoMetidos;
	}

	public String getTirosCampoIntentados() {
		return tirosCampoIntentados;
	}

	public void setTirosCampoIntentados(String tirosCampoIntentados) {
		this.tirosCampoIntentados = tirosCampoIntentados;
	}

	public String getTirosCampoPorcentaje() {
		return tirosCampoPorcentaje;
	}

	public void setTirosCampoPorcentaje(String tirosCampoPorcentaje) {
		this.tirosCampoPorcentaje = tirosCampoPorcentaje;
	}

	public String getTriplesMetidos() {
		return triplesMetidos;
	}

	public void setTriplesMetidos(String triplesMetidos) {
		this.triplesMetidos = triplesMetidos;
	}

	public String getTriplesIntentados() {
		return triplesIntentados;
	}

	public void setTriplesIntentados(String triplesIntentados) {
		this.triplesIntentados = triplesIntentados;
	}

	public String getTriplesPorcentaje() {
		return triplesPorcentaje;
	}

	public void setTriplesPorcentaje(String triplesPorcentaje) {
		this.triplesPorcentaje = triplesPorcentaje;
	}

	public String getDosPuntosMetidos() {
		return dosPuntosMetidos;
	}

	public void setDosPuntosMetidos(String dosPuntosMetidos) {
		this.dosPuntosMetidos = dosPuntosMetidos;
	}

	public String getDosPuntosIntentados() {
		return dosPuntosIntentados;
	}

	public void setDosPuntosIntentados(String dosPuntosIntentados) {
		this.dosPuntosIntentados = dosPuntosIntentados;
	}

	public String getDosPuntosPorcentaje() {
		return dosPuntosPorcentaje;
	}

	public void setDosPuntosPorcentaje(String dosPuntosPorcentaje) {
		this.dosPuntosPorcentaje = dosPuntosPorcentaje;
	}

	public String getTirosLibresMetidos() {
		return tirosLibresMetidos;
	}

	public void setTirosLibresMetidos(String tirosLibresMetidos) {
		this.tirosLibresMetidos = tirosLibresMetidos;
	}

	public String getTirosLibresIntentados() {
		return tirosLibresIntentados;
	}

	public void setTirosLibresIntentados(String tirosLibresIntentados) {
		this.tirosLibresIntentados = tirosLibresIntentados;
	}

	public String getTirosLibresPorcentaje() {
		return tirosLibresPorcentaje;
	}

	public void setTirosLibresPorcentaje(String tirosLibresPorcentaje) {
		this.tirosLibresPorcentaje = tirosLibresPorcentaje;
	}

	public String getReboteOfensivo() {
		return reboteOfensivo;
	}

	public void setReboteOfensivo(String reboteOfensivo) {
		this.reboteOfensivo = reboteOfensivo;
	}

	public String getReboteDefensivo() {
		return reboteDefensivo;
	}

	public void setReboteDefensivo(String reboteDefensivo) {
		this.reboteDefensivo = reboteDefensivo;
	}

	public String getTotalRebotes() {
		return totalRebotes;
	}

	public void setTotalRebotes(String totalRebotes) {
		this.totalRebotes = totalRebotes;
	}

	public String getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(String asistencias) {
		this.asistencias = asistencias;
	}

	public String getRobos() {
		return robos;
	}

	public void setRobos(String robos) {
		this.robos = robos;
	}

	public String getTapones() {
		return tapones;
	}

	public void setTapones(String tapones) {
		this.tapones = tapones;
	}

	public String getPerdidas() {
		return perdidas;
	}

	public void setPerdidas(String perdidas) {
		this.perdidas = perdidas;
	}

	public String getFaltasPersonales() {
		return faltasPersonales;
	}

	public void setFaltasPersonales(String faltasPersonales) {
		this.faltasPersonales = faltasPersonales;
	}

	public String getPuntos() {
		return puntos;
	}

	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}

    public int getMasMenos() {
        return masMenos;
    }

    public void setMasMenos(int masMenos) {
        this.masMenos = masMenos;
    }
	public String getTituloCabeceraJugador() {
		return tituloCabeceraJugador;
	}
	public void setTituloCabeceraJugador(String titulo) {
		switch(titulo) {
			case "playoff":
				this.tituloCabeceraJugador="Partidos disputados de Playoff";
				break;
			case "regular":
				this.tituloCabeceraJugador="Partidos disputados en Temporada Regular";
				break;
			case "temporada":
				this.tituloCabeceraJugador="Total de partidos jugados en la NBA";
				break;
		}
	}
}
