package temporadas;

import lombok.Data;

@Data
public class EstadisticasMaximos {
	
	/**
	 * TEMPORADA EN LA QUE ESTAMOS
	 */
	private String temporada = "";
	
	/**
	 * 
	 */
	private String nombre="";
	
	/**
	 * 
	 */
	private String apellido="";
	
	/**
	 * TIEMPO JUGADOR
	 */
	private String tiempo="";
	
	/**
	 * TIPO RESULTADO
	 */
	private String tipoResultado = "";
	
	/**
	 * PARTIDOS JUGADOS
	 */
	private String partidosJugados="";
	
	/**
	 * PARTIDOS JUGADOS EN EL QUINTETO INICIAL
	 */
	private String partidosQuintetoInicial="";
	
	/**
	 * MINUTOS JUGADOS
	 */
	private String minutos="";
	private Double minutosJugadosPartido=0.0;
	
	/**
	 * ID JUGADOR
	 */
	private String idJugador="";
	
	/**
	 * TIROS DE CAMPO METIDOS
	 */
	private String tirosCampoMetidos = "";
	private Double tiroCampoAnotadosPartido=0.0;
	
	/**
	 * TIROS DE CAMPO INTENTADOS
	 */
	private String tirosCampoIntentados = "";
	private Double tiroCampoIntentadosPartido=0.0;
	
	/**
	 * PORCENTAJE EN TIROS DE CAMPO
	 */
	private Double tirosCampoPorcentaje = 0.0;
	
	/**
	 * TRIPLES METIDOS
	 */
	private String triplesMetidos = "";
	private Double triplesAnotadosPartido=0.0;
	
	/**
	 * TRIPLES INTENTADOS
	 */
	private String triplesIntentados = "";
	private Double triplesIntentadosPartido=0.0;
	
	/**
	 * PORCENTAJE EN TRIPLES
	 */
	private Double triplesPorcentaje = 0.0;
	
	/**
	 * CANASTAS DE DOS PUNTOS METIDAS
	 */
	private String dosPuntosMetidos = "";
	private Double dosPuntosMetidosPartido=0.0;
	
	/**
	 * CANASTAS DE DOS PUNTOS INTENTADAS
	 */
	private String dosPuntosIntentados = "";
	private Double dosPuntosIntentadosPartido=0.0;
	
	/**
	 * PORCENTAJE DE TIROS DE DOS
	 */
	private Double dosPuntosPorcentaje = 0.0;
	
	/**
	 * TIROS LIBRES METIDOS
	 */
	private String tirosLibresMetidos = "";
	private Double tirosLibresAnotadosPartido=0.0;
	
	/**
	 * TIROS LIBRES INTENTADOS
	 */
	private String tirosLibresIntentados = "";
	private Double tirosLibresIntentadosPartido=0.0;
	
	/**
	 * PORCENTAJE DE TIROS LIBRES
	 */
	private Double tirosLibresPorcentaje = 0.0;
	
	/**
	 * REBOTES OFENSIVOS
	 */
	private String reboteOfensivo = "";
	private Double reboteOfensivoPartido=0.0;
	
	/**
	 * REBOTES DEFENSIVOS
	 */
	private String reboteDefensivo = "";
	private Double reboteDefensivoPartido=0.0;
	
	/**
	 * TOTAL REBOTES
	 */
	private String totalRebotes = "";
	private Double rebotesPartido=0.0;
	
	/**
	 * ASISTENCIAS
	 */
	private String asistencias = "";
	private Double asistenciasPartido=0.0;
	
	/**
	 * ROBOS
	 */
	private String robos = "";
	private Double robosPartido=0.0;
	
	/**
	 * TAPONES
	 */
	private String tapones = "";
	private Double taponesPartido=0.0;
	
	/**
	 * PERDIDAS
	 */
	private String perdidas = "";
	private Double perdidasPartido=0.0;
	
	/**
	 * FALTAS PERSONALES
	 */
	private String faltasPersonales = "";
	private Double faltasPartido=0.0;
	
	/**
	 * PUNTOS
	 */
	private String puntos = "";
	private Double puntosPartidos=0.0;
	
		
	/**
	 * Calculamos los puntos por partido
	 */
	public void setPuntosPartidos() {
		this.puntosPartidos = (double)Integer.parseInt(getPuntos())/Integer.parseInt(getPartidosJugados());
	}


	/**
	 * Calculamos el total de rebotes por partido
	 */
	public void setRebotesPartido() {
		this.rebotesPartido = (double)Integer.parseInt(getTotalRebotes())/Integer.parseInt(getPartidosJugados());
	}
	
	/**
	 * Calculamos el total de rebotes Defensivos por partido
	 */
	public void setRebotesDefensivosPartido() {
		this.reboteDefensivoPartido = (double)Integer.parseInt(getReboteDefensivo())/Integer.parseInt(getPartidosJugados());
	}
	
	/**
	 * Calculamos el total de rebotes Ofensivos por partido
	 */
	public void setRebotesOfensivosPartido() {
		this.reboteOfensivoPartido = (double)Integer.parseInt(getReboteOfensivo())/Integer.parseInt(getPartidosJugados());
	}

	/**
	 * Calculamos las asistencias por partido
	 */
	public void setAsistenciasPartido() {
		this.asistenciasPartido = (double)Integer.parseInt(getAsistencias())/Integer.parseInt(getPartidosJugados());
	}


	/**
	 * Calculamos los tapones por partido
	 */
	public void setTaponesPartido( ) {
		this.taponesPartido = (double)Integer.parseInt(getTapones())/Integer.parseInt(getPartidosJugados());;
	}


	/**
	 * Calculamos los robos por partido
	 */
	public void setRobosPartido( ) {
		this.robosPartido = (double)Integer.parseInt(getRobos())/Integer.parseInt(getPartidosJugados());
	}

	/**
	 * Calculamos los minutos por partido
	 */
	public void setMinutosJugadosPartido( ) {
		this.minutosJugadosPartido = (double)Integer.parseInt(getMinutos())/Integer.parseInt(getPartidosJugados());
	}

	/**
	 * Calculamos los tiros de campo anotados por partido
	 */
	public void setTiroCampoAnotadosPartido()  {
		this.tiroCampoAnotadosPartido = (double)Integer.parseInt(getTirosCampoMetidos())/Integer.parseInt(getPartidosJugados());
	}

	/**
	 * Calculamos los tiros de campo intentados por partido
	 */
	public void setTiroCampoIntentadosPartido( ) {
		this.tiroCampoIntentadosPartido = (double)Integer.parseInt(getTirosCampoIntentados())/Integer.parseInt(getPartidosJugados());
	}
	
	/**
	 * Calculamos los tiros de campo de 2 anotados por partido
	 */
	public void setTiroDosAnotadosPartido()  {
		this.dosPuntosMetidosPartido = (double)Integer.parseInt(getDosPuntosMetidos())/Integer.parseInt(getPartidosJugados());
	}
	
	/**
	 * Calculamos los tiros de campo de 2 intentados por partido
	 */
	public void setTiroDosIntentadosPartido( ) {
		this.dosPuntosIntentadosPartido = (double)Integer.parseInt(getDosPuntosIntentados())/Integer.parseInt(getPartidosJugados());
	}
	
	/**
	 * Calculamos los triples anotados por partido
	 */
	public void setTriplesAnotadosPartido() {
		this.triplesAnotadosPartido = (double)Integer.parseInt(getTriplesMetidos())/Integer.parseInt(getPartidosJugados());
	}

	/**
	 * Calculamos los triples intentados por partido
	 */
	public void setTriplesIntentadosPartido( ) {
		this.triplesIntentadosPartido = (double)Integer.parseInt(getTriplesIntentados())/Integer.parseInt(getPartidosJugados());
	}

	/**
	 * Calculamos los tiros libres anotados por partido
	 */
	public void setTirosLibresAnotadosPartido( ) {
		this.tirosLibresAnotadosPartido = (double)Integer.parseInt(getTirosLibresMetidos())/Integer.parseInt(getPartidosJugados());
	}

	/**
	 * Calculamos los tiros libres por partido
	 */
	public void setTirosLibresIntentadosPartido() {
		this.tirosLibresIntentadosPartido = (double)Integer.parseInt(getTirosLibresIntentados())/Integer.parseInt(getPartidosJugados());
	}

	/**
	 * Calculamos las perdidas por partido
	 */
	public void setPerdidasPartido( ) {
		this.perdidasPartido = (double)Integer.parseInt(getPerdidas())/Integer.parseInt(getPartidosJugados());
	}

	/**
	 * calculamos las faltas por partido
	 */
	public void setFaltasPartido( ) {
		this.faltasPartido = (double)Integer.parseInt(getFaltasPersonales())/Integer.parseInt(getPartidosJugados());
	}
	
	/**
	 * Calcular porcentajes
	 */
	public void calcularPorcentajes() {
		if (!this.tirosCampoIntentados.equals("")) {
			this.tirosCampoPorcentaje = ((Double.valueOf(this.tirosCampoMetidos) / Double.valueOf(this.tirosCampoIntentados)))*100;
        } else {
        	this.tirosCampoPorcentaje = 0.0;
        }
		
		if (!this.dosPuntosIntentados.equals("")) {
			this.dosPuntosPorcentaje = ((Double.valueOf(this.dosPuntosMetidos) / Double.valueOf(this.dosPuntosIntentados)))*100;
        } else {
        	this.dosPuntosPorcentaje = 0.0;
        }
		if (!this.triplesIntentados.equals("")) {
			this.triplesPorcentaje = ((Double.valueOf(this.triplesMetidos) / Double.valueOf(this.triplesIntentados)))*100;
        } else {
        	this.triplesPorcentaje = 0.0;
        }
		
		if (!this.tirosLibresIntentados.equals("")) {
			this.tirosLibresPorcentaje = ((Double.valueOf(this.tirosLibresMetidos) / Double.valueOf(this.tirosLibresIntentados)))*100;
        } else {
        	this.tirosLibresPorcentaje = 0.0;
        }
	}

	/**
	 * Calculamos las medias
	 */
	public void calcularMedias() {
		setPuntosPartidos();
		setRebotesPartido();
		setRebotesDefensivosPartido();
		setRebotesOfensivosPartido();
		setAsistenciasPartido();
		setTaponesPartido();
		setRobosPartido();
		setMinutosJugadosPartido();
		setTiroCampoAnotadosPartido();
		setTiroCampoIntentadosPartido();
		setTriplesAnotadosPartido();
		setTriplesIntentadosPartido();
		setTirosLibresAnotadosPartido();
		setTirosLibresIntentadosPartido();
		setPerdidasPartido();
		setFaltasPartido();
		setTiroDosAnotadosPartido();
		setTiroDosIntentadosPartido();
		//calcularPorcentajes();
	}	

	public int gettirosDeCampoIntentados() {
		if(this.tirosCampoIntentados.equals("")) {
			return 0;
		}
		return Integer.parseInt(this.tirosCampoIntentados);
	}
	
	public Double getMinutosPartidoMedia() {
		return minutosJugadosPartido/60;
	}
	
	public Double getMinutosPartido() {
		return Double.valueOf(getMinutos())/60;
	}
}
