package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import lombok.Data;

@Data
public class ControllerEquipo {

    private String nombre = "";
    private String tanteo = "";
    private String derrotas = "";
    private String victorias = "";
    private String nombreAbreviado = "";
    private String puntosConsecutivos = "";

    private Integer sinAnotar = 0;
    private Integer tiempoLider = 0;

    private ArrayList<ControllerJugador> jugadores = new ArrayList<>();
    private ControllerFullBoxscore fullBoxscore = new ControllerFullBoxscore();
    private ControllerTanteoCuartos tanteoCuartos = new ControllerTanteoCuartos();
    private ControllerEstadisticaAvanzada estadisticaAvanzada = new ControllerEstadisticaAvanzada();
    private ControllerEstadisticaNormal estadisticaNormal = new ControllerEstadisticaNormal();
    private ArrayList<ControllerTiros> listaTiros = new ArrayList<>();
    
    private ArrayList<String> listaJugadores = new ArrayList<>();

    private String cuartoElegidoTiros="partido";
    private String jugadorElegidoTiros="";
    

    private int[] maxAnotadores = new int[3];
    private int[] maxAsistentes = new int[3];
    private int[] maxReboteadores = new int[3];
    private int[] maxTaponadores = new int[3];
    private int[] maxPorcentaje = new int[3];

    public String getNombreAbreviado() {
        if(nombreAbreviado.equals("BRK")){
            return "NJN";
        }else if(nombreAbreviado.equals("NOP")){
            return "NOH";
        }else if (nombreAbreviado.equals("CHO")){
            return "CHA";
        }else if(nombreAbreviado.equals("CHH")) {
        	return "CHA";
        }else if(nombreAbreviado.equals("SEA")) {
        	return "OKC";
        }else if(nombreAbreviado.equals("VAN")) {
        	return "MEM";
        			
        }
        return nombreAbreviado;
    }

    public void obtenerEstrellas() {
        maximaAnotacion();
        maximoAsistente();
        maximoReboteador();
        maximoTapones();
        maximoPorcentajes();
        ordenarInicio();
    }
    
    private void maximaAnotacion() {
        ordenarAnotadores();
        maxAnotadores[0] = getJugadores().get(0).getPosicionTabla();
        maxAnotadores[1] = getJugadores().get(1).getPosicionTabla();
        maxAnotadores[2] = getJugadores().get(2).getPosicionTabla();
    }

    private void maximoAsistente() {
        ordenarAsistentes();
        maxAsistentes[0] = getJugadores().get(0).getPosicionTabla();
        maxAsistentes[1] = getJugadores().get(1).getPosicionTabla();
        maxAsistentes[2] = getJugadores().get(2).getPosicionTabla();
    }

    private void maximoReboteador() {
        ordenarReboteadores();
        maxReboteadores[0] = getJugadores().get(0).getPosicionTabla();
        maxReboteadores[1] = getJugadores().get(1).getPosicionTabla();
        maxReboteadores[2] = getJugadores().get(2).getPosicionTabla();
    }
    
    private void maximoTapones() {
    	ordenarTapones();
    	maxTaponadores[0] = getJugadores().get(0).getPosicionTabla();
    	maxTaponadores[1] = getJugadores().get(1).getPosicionTabla();
    	maxTaponadores[2] = getJugadores().get(2).getPosicionTabla();
    }

    private void maximoPorcentajes(){
    	ordenarPorcentajes();
    	maxPorcentaje[0] = getJugadores().get(0).getPosicionTabla();
    	maxPorcentaje[1] = getJugadores().get(1).getPosicionTabla();
    	maxPorcentaje[2] = getJugadores().get(2).getPosicionTabla();
    }
    
    private void ordenarPorcentajes() {
    	Comparator<ControllerJugador> comparator = new Comparator<ControllerJugador>() {
            @Override
            public int compare(ControllerJugador s1, ControllerJugador s2) {
                return s2.getBoxscore().getTirosCampoPorcentaje().compareTo(s1.getBoxscore().getTirosCampoPorcentaje());
            }
        };
        Collections.sort(jugadores, comparator);
    }
    
    private void ordenarTapones() {
    	Comparator<ControllerJugador> comparator = new Comparator<ControllerJugador>() {
            @Override
            public int compare(ControllerJugador s1, ControllerJugador s2) {
                return s2.getTotalPartido().getTapones().compareTo(s1.getTotalPartido().getTapones());
            }
        };
        Collections.sort(jugadores, comparator);
    }

    private void ordenarAnotadores() {
        Comparator<ControllerJugador> comparator = new Comparator<ControllerJugador>() {
            @Override
            public int compare(ControllerJugador s1, ControllerJugador s2) {
                return s2.getTotalPartido().getPuntos().compareTo(s1.getTotalPartido().getPuntos());
            }
        };
        Collections.sort(jugadores, comparator);
    }

    private void ordenarAsistentes() {
        Comparator<ControllerJugador> comparator = new Comparator<ControllerJugador>() {
            @Override
            public int compare(ControllerJugador s1, ControllerJugador s2) {
                return s2.getTotalPartido().getAsistencias().compareTo(s1.getTotalPartido().getAsistencias());
            }
        };
        Collections.sort(jugadores, comparator);
    }

    private void ordenarReboteadores() {
        Comparator<ControllerJugador> comparator = new Comparator<ControllerJugador>() {
            @Override
            public int compare(ControllerJugador s1, ControllerJugador s2) {
                return s2.getTotalPartido().getTotalRebotes().compareTo(s1.getTotalPartido().getTotalRebotes());
            }
        };
        Collections.sort(jugadores, comparator);
    }

    private void ordenarInicio() {
        Comparator<ControllerJugador> comparator = new Comparator<ControllerJugador>() {
            @Override
            public int compare(ControllerJugador s1, ControllerJugador s2) {
                return s1.getPosicionTabla().compareTo(s2.getPosicionTabla());
            }
        };
        Collections.sort(jugadores, comparator);
    }

	public void rellenarTirosEquipos() {
		
		listaJugadores.add(getNombre());
		for(ControllerJugador jugador: getJugadores()) {
			for(ControllerTiros tiro:jugador.getListaTiros()) {
				listaTiros.add(new ControllerTiros(tiro.getPosicionTop(), tiro.getPosicionLeft(), tiro.isDentro(),tiro.getCuarto()));
			}
			listaJugadores.add(jugador.getApeNom());
		}
		
	}

    public void actualizarTiros() {
    	
    	listaTiros.clear();
    	
    	ControllerJugador jugador= buscarJugadorCartaTiros();
    	if(null!=jugador) {
        	for(ControllerTiros tiro:jugador.getListaTiros()) {
    			if(insetarTiroGraficaPartido(tiro)) {listaTiros.add(new ControllerTiros(tiro.getPosicionTop(), tiro.getPosicionLeft(), tiro.isDentro()));}
    		}    		
    	}else {
    		for(ControllerJugador jugadorEquipo: getJugadores()) {
    			for(ControllerTiros tiro:jugadorEquipo.getListaTiros()) {
    				if(insetarTiroGraficaPartido(tiro)) {listaTiros.add(new ControllerTiros(tiro.getPosicionTop(), tiro.getPosicionLeft(), tiro.isDentro()));}
    			}
    		}
    	}
    	
    }
    
    public ControllerJugador buscarJugadorCartaTiros() {
    	for(ControllerJugador jugador:getJugadores()) {
    		if(jugador.getApeNom().equals(getJugadorElegidoTiros())) {
    			return jugador;
    		}
    	}
    	return null;
    }
    
    private boolean insetarTiroGraficaPartido(ControllerTiros tiro){
    	if(tiro.getCuarto().equals(getCuartoElegidoTiros()) || "partido".equals(getCuartoElegidoTiros())) {
    		return true;
    	}
    	
    	return false;
    }
    
}
