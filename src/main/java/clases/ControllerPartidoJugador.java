/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

/**
 *
 * @author hatashi
 */
@Data
public class ControllerPartidoJugador {
    
    private Date fecha;
    private boolean casa=false;
    private Integer inicio=0;
    
    private Integer tanteoEquipoJugador=0;
    private Integer tanteoEquipoRival=0;
    
    private String jugandoConAbreviado="";
    private String jugandoConNombre=""; //Tooltip
    
    private String jugandoContraAbreviado="";
    private String jugandoContraNombre=""; //Tooltip
    
    @SuppressWarnings("unused")
	private String minutos="";
    private Integer segundos=0;
    
    private ControllerEstadisticaNormal boxscore = new ControllerEstadisticaNormal();
    private String resultadoPartido="";
    private Integer partidoNumero=0;
    
    private boolean maximaAnotacion=false;
    private boolean maximaMasMenos=false;
    private boolean maximaTapones=false;
    private boolean maximaPerdidas=false;
    private boolean maximaRobos=false;
    private boolean maximaAsistencias=false;
    private boolean maximaRebotes=false;
    private boolean maximaRebotesDefensa=false;
    private boolean maximaRebotesAtaque=false;
    private boolean maximaTiroLibrePorcentaje=false;
    private boolean maximaTiroLibreTirado=false;
    private boolean maximaTiroLibreMetido=false;
    private boolean maximaTriplePorcentaje=false;
    private boolean maximaTripleTirado=false;
    private boolean maximaTripleMetido=false;
    private boolean maximaTirosCampoPorcentaje=false;
    private boolean maximaTirosCampoTirado=false;
    private boolean maximaTirosCampoMetido=false;
    private boolean maximaMinutos=false;
    
    
    public ControllerPartidoJugador(){
        
    }

    public String getJugandoConAbreviado() {
        if("BRK".equalsIgnoreCase(jugandoConAbreviado.toUpperCase())){
            return "NJN";
        }else if("NOP".equalsIgnoreCase(jugandoConAbreviado.toUpperCase())){
            return "NOH";
        }else if("CHO".equalsIgnoreCase(jugandoConAbreviado.toUpperCase())){
            return "CHA";
        }
        return jugandoConAbreviado.toUpperCase();
    }

    public String getJugandoContraAbreviado() {
        if("BRK".equalsIgnoreCase(jugandoContraAbreviado.toUpperCase())){
            return "NJN";
        }else if("NOP".equalsIgnoreCase(jugandoContraAbreviado.toUpperCase())){
            return "NOH";
        }else if("CHO".equalsIgnoreCase(jugandoContraAbreviado.toUpperCase())){
            return "CHA";
        }
        return jugandoContraAbreviado.toUpperCase();
    }

   
    @SuppressWarnings("unused")
	public String getMinutos(){
        String min="";
        int iSeg;
        int iMin;
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

    public ControllerEstadisticaNormal getBoxscore() {
        return boxscore;
    }

    public void setBoxscore(ControllerEstadisticaNormal boxscore) {
        this.boxscore = boxscore;
    }
                  
    public String fechaFormateada(){
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(getFecha());
    }

    public String getResultadoPartido() {
        
        if(casa){
            resultadoPartido = tanteoEquipoRival+" - "+tanteoEquipoJugador+" ";
        }else{
            resultadoPartido = tanteoEquipoJugador+" - "+tanteoEquipoRival+" ";
        }
        
        if(tanteoEquipoJugador<tanteoEquipoRival){ //pierde
            resultadoPartido=resultadoPartido +"(Lose "+(tanteoEquipoRival-tanteoEquipoJugador)+")";
        }else{ // gana
            resultadoPartido = resultadoPartido +"(Win  "+(tanteoEquipoJugador-tanteoEquipoRival)+")";
        }
        
        return resultadoPartido;
    }
    
    public String partidoCasaFuera(){
        if(casa){
            return "https://freeiconshop.com/wp-content/uploads/edd/home-outline.png";
        }else{
            return "https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-plane-512.png";
        }
    }
    
}
