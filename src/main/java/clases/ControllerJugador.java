package clases;

import java.util.ArrayList;
import java.util.Comparator;

import lombok.Data;

@Data
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

    @SuppressWarnings("unused")
	public void ordenarJugadoresPuntosMetidos() {
        Comparator<ControllerJugador> comparator = new Comparator<ControllerJugador>() {

            @Override
            public int compare(ControllerJugador o1, ControllerJugador o2) {
                return o1.getBoxscore().getPuntos().compareTo(o2.getBoxscore().getPuntos());
            }
        };
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
    
    @SuppressWarnings("unused")
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
