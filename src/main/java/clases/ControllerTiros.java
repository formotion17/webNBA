package clases;

import lombok.Data;
import util.PiesAMetros;

@Data
public class ControllerTiros {

    private String cuarto;
    private boolean dentro=false;
    private Integer distancia = 0;
    private Integer posicionLeft = 0;
    private Integer posicionTop = 0;
    private String situacionAntes = "";
	private String situacionDespues = "";
    private String tanteo="";
    private String tanteoEquipo="";
    private String tanteoRival="";
    private Integer tiempoRestante = 0;
    private String tipo = "";
    private String clase="";
    
    private String rival="";
    private String equipo="";
    private String fecha="";
    private String jugador="";
    
    private String tooltipTiro="";
    
    public ControllerTiros(){
        
    }
    public ControllerTiros(Integer posicionTop, Integer posicionLeft){
        this.posicionTop=posicionTop;
        this.posicionLeft=posicionLeft;
    }
    
    public ControllerTiros(Integer posicionTop, Integer posicionLeft,boolean dentro) {
        this.posicionTop=posicionTop;
        this.posicionLeft=posicionLeft;
        this.dentro=dentro;
    }
    
    public ControllerTiros(Integer posicionTop, Integer posicionLeft,boolean dentro,String cuarto) {
        this.posicionTop=posicionTop;
        this.posicionLeft=posicionLeft;
        this.dentro=dentro;
        this.cuarto=cuarto;
    }

    public void setDentro(boolean dentro) {
        this.dentro = dentro;
        if(dentro){
            clase = "make";
        }else{
            clase ="miss";
        }
    }
    
    public void rellenarTooltipPartido() {
    	this.tooltipTiro=
    			devolverCuarto(getCuarto())+", A falta de "+getMinutos(getTiempoRestante())+
    			"\n"+jugador+" "+getAcierto(isDentro())+" la canasta de "+getTipo()+" a "+PiesAMetros.devolverMetros(getDistancia())+
    			"\n"+getEquipo()+" "+devolverSituacionPartido(getSituacionDespues())+" "+getTanteo();
    }
    
	private String devolverSituacionPartido(String situacion) {
		switch(situacion) {
		case "GANANDO":
			return "lidera";
		case "PERDIENDO":
			return "pierde";
		case "EMPATE":
			return "empata";
		default:
			return situacion;
		}
	}
    
	private String devolverCuarto(String cuarto) {
    	switch(cuarto) {
			case "1st quarter,":
				return "1° Cuarto";
			case "2nd quarter,":
				return "2° Cuarto";
			case "3rd quarter,":
				return "3° Cuarto";
			case "4th quarter,":
				return "4° Cuarto";
			case "1st overtime,":
				return "1° Prorroga";
			case "2nd overtime,":
				return "2° Prorroga";
			case "3rd overtime,":
				return "3° Prorroga";
			case "4th overtime,":
				return "4° Prorroga";
			case "5th overtime,":
				return "5° Prorroga";
			case "6th overtime,":
				return "6° Prorroga";
			default:
				return cuarto;
		}
    }
    
    private String getMinutos(int segundos){
        int iSeg;
        int iMin;
        iMin=segundos/60;
        iSeg=segundos-iMin*60;
        return Integer.toString(iMin)+":"+devolverSegundos(iSeg);
    }
    
    private String devolverSegundos(int seg){
        if(seg<10){
            return "0"+Integer.toString(seg);
        }else{
            return Integer.toString(seg);
        }
    }
    
    private String getAcierto(boolean canasta) {
    	if(canasta){
            return "encesta";
        }else{
            return "falla";
        }
    }
    
}
