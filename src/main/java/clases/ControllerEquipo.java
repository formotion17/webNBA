package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ControllerEquipo {

    private String nombre = "";
    private String tanteo = "";
    private String derrotas = "";
    private String victorias = "";
    private String nombreAbreviado = "";
    private String puntosConsecutivos = "";

    private Integer sinAnotar = 0;
    private Integer tiempoLider = 0;

    private ArrayList<ControllerJugador> jugadores = new ArrayList< ControllerJugador>();
    private ControllerFullBoxscore fullBoxscore = new ControllerFullBoxscore();
    private ControllerTanteoCuartos tanteoCuartos = new ControllerTanteoCuartos();
    private ControllerEstadisticaAvanzada estadisticaAvanzada = new ControllerEstadisticaAvanzada();
    private ControllerEstadisticaNormal estadisticaNormal = new ControllerEstadisticaNormal();

    public String getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(String derrotas) {
        this.derrotas = derrotas;
    }

    public ControllerEstadisticaAvanzada getEstadisticaAvanzada() {
        return estadisticaAvanzada;
    }

    public void setEstadisticaAvanzada(ControllerEstadisticaAvanzada estadisticaAvanzada) {
        this.estadisticaAvanzada = estadisticaAvanzada;
    }

    public ControllerEstadisticaNormal getEstadisticaNormal() {
        return estadisticaNormal;
    }

    public void setEstadisticaNormal(ControllerEstadisticaNormal estadisticaNormal) {
        this.estadisticaNormal = estadisticaNormal;
    }

    public ControllerFullBoxscore getFullBoxscore() {
        return fullBoxscore;
    }

    public void setFullBoxscore(ControllerFullBoxscore fullBoxscore) {
        this.fullBoxscore = fullBoxscore;
    }

    public ArrayList<ControllerJugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<ControllerJugador> jugadores) {
        this.jugadores = jugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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

    public void setNombreAbreviado(String nombreAbreviado) {
        this.nombreAbreviado = nombreAbreviado;
    }

    public String getPuntosConsecutivos() {
        return puntosConsecutivos;
    }

    public void setPuntosConsecutivos(String puntosConsecutivos) {
        this.puntosConsecutivos = puntosConsecutivos;
    }

    public Integer getSinAnotar() {
        return sinAnotar;
    }

    public void setSinAnotar(Integer sinAnotar) {
        this.sinAnotar = sinAnotar;
    }

    public String getTanteo() {
        return tanteo;
    }

    public void setTanteo(String tanteo) {
        this.tanteo = tanteo;
    }

    public ControllerTanteoCuartos getTanteoCuartos() {
        return tanteoCuartos;
    }

    public void setTanteoCuartos(ControllerTanteoCuartos tanteoCuartos) {
        this.tanteoCuartos = tanteoCuartos;
    }

    public Integer getTiempoLider() {
        return tiempoLider;
    }

    public void setTiempoLider(Integer tiempoLider) {
        this.tiempoLider = tiempoLider;
    }

    public String getVictorias() {
        return victorias;
    }

    public void setVictorias(String victorias) {
        this.victorias = victorias;
    }

    public int[] getMaxAnotadores() {
        return maxAnotadores;
    }

    public void setMaxAnotadores(int[] maxAnotadores) {
        this.maxAnotadores = maxAnotadores;
    }

    public int[] getMaxAsistentes() {
        return maxAsistentes;
    }

    public void setMaxAsistentes(int[] maxAsistentes) {
        this.maxAsistentes = maxAsistentes;
    }

    public int[] getMaxReboteadores() {
        return maxReboteadores;
    }

    public void setMaxReboteadores(int[] maxReboteadores) {
        this.maxReboteadores = maxReboteadores;
    }

    private int[] maxAnotadores = new int[3];
    private int[] maxAsistentes = new int[3];
    private int[] maxReboteadores = new int[3];

    public void obtenerEstrellas() {
        maximaAnotacion();
        maximoAsistente();
        maximoReboteador();
        ordenarInicio();
    }

    private void maximaAnotacion() {
//		Collections.sort(jugadores,new ordenMaximoAnotador());
        ordenarAnotadores();
        maxAnotadores[0] = getJugadores().get(0).getPosicionTabla();
        maxAnotadores[1] = getJugadores().get(1).getPosicionTabla();
        maxAnotadores[2] = getJugadores().get(2).getPosicionTabla();
    }

    private void maximoAsistente() {
//		Collections.sort(jugadores,new ordenMaximoAsistente());
        ordenarAsistentes();
        maxAsistentes[0] = getJugadores().get(0).getPosicionTabla();
        maxAsistentes[1] = getJugadores().get(1).getPosicionTabla();
        maxAsistentes[2] = getJugadores().get(2).getPosicionTabla();
    }

    private void maximoReboteador() {
//		Collections.sort(jugadores,new ordenMaximoReboteador());
        ordenarReboteadores();
        maxReboteadores[0] = getJugadores().get(0).getPosicionTabla();
        maxReboteadores[1] = getJugadores().get(1).getPosicionTabla();
        maxReboteadores[2] = getJugadores().get(2).getPosicionTabla();
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
}
