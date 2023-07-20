package util;

import lombok.Data;

@Data
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

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
		setTituloCabeceraJugador(tiempo);
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
