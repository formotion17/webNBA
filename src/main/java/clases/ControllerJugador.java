package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ControllerJugador {

    private ControllerFullBoxscore totalPartido = new ControllerFullBoxscore();
    private ArrayList<ControllerTiros> listaTiros = new ArrayList<ControllerTiros>();
    private ControllerEstadisticaNormal boxscore = new ControllerEstadisticaNormal();
    private ControllerEstadisticaAvanzada estadisticaAvanzada = new ControllerEstadisticaAvanzada();
    private ArrayList<ControllerFullBoxscore> cuartos = new ArrayList<ControllerFullBoxscore>();

    private String id = "";
    private String nombre = "";
    private String apellido = "";
    private String apeNom = "";

    private Boolean inicio = false;

    private Integer posicionTabla;

    private Integer segundos = 0;

    public void ordenarJugadoresPuntosMetidos() {
        Comparator<ControllerJugador> comparator = new Comparator<ControllerJugador>() {

            @Override
            public int compare(ControllerJugador o1, ControllerJugador o2) {
                return o1.getBoxscore().getPuntos().compareTo(o2.getBoxscore().getPuntos());
            }
        };
    }

    public String getApeNom() {
        return apeNom;
    }

    public void setApeNom(String apeNom) {
        this.apeNom = apeNom;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public ControllerEstadisticaNormal getBoxscore() {
        return boxscore;
    }

    public void setBoxscore(ControllerEstadisticaNormal boxscore) {
        this.boxscore = boxscore;
    }

    public ArrayList<ControllerFullBoxscore> getCuartos() {
        return cuartos;
    }

    public void setCuartos(ArrayList<ControllerFullBoxscore> cuartos) {
        this.cuartos = cuartos;
    }

    public ControllerEstadisticaAvanzada getEstadisticaAvanzada() {
        return estadisticaAvanzada;
    }

    public void setEstadisticaAvanzada(ControllerEstadisticaAvanzada estadisticaAvanzada) {
        this.estadisticaAvanzada = estadisticaAvanzada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getInicio() {
        return inicio;
    }

    public void setInicio(Boolean inicio) {
        this.inicio = inicio;
    }

    public ArrayList<ControllerTiros> getListaTiros() {
        return listaTiros;
    }

    public void setListaTiros(ArrayList<ControllerTiros> listaTiros) {
        this.listaTiros = listaTiros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getSegundos() {
        return segundos;
    }

    public void setSegundos(Integer segundos) {
        this.segundos = segundos;
    }

    public ControllerFullBoxscore getTotalPartido() {
        return totalPartido;
    }

    public void setTotalPartido(ControllerFullBoxscore totalPartido) {
        this.totalPartido = totalPartido;
    }

    public void addCuartoJugador(ControllerFullBoxscore fullCuarto) {
        cuartos.add(fullCuarto);
    }

    public Integer getPosicionTabla() {
        return posicionTabla;
    }

    public void setPosicionTabla(Integer posicionTabla) {
        this.posicionTabla = posicionTabla;
    }
    
    public String getMinutos(){
        String min="";
        int iSeg, iMin;
        iMin=getSegundos()/60;
        iSeg=segundos-iMin*60;
        return Integer.toString(iMin)+":"+devolverSegundos(iSeg);
    }
    
    public String devolverSegundos(int seg){
        if(seg<10){
            return "0"+Integer.toString(seg);
        }else{
            return Integer.toString(seg);
        }
    }

}
