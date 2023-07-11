/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hatashi
 */
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isCasa() {
        return casa;
    }

    public void setCasa(boolean casa) {
        this.casa = casa;
    }

    public String getJugandoConAbreviado() {
        if("BRK".equals(jugandoConAbreviado.toUpperCase())){
            return "NJN";
        }else if("NOP".equals(jugandoConAbreviado.toUpperCase())){
            return "NOH";
        }else if("CHO".equals(jugandoConAbreviado.toUpperCase())){
            return "CHA";
        }
        return jugandoConAbreviado.toUpperCase();
    }

    public void setJugandoConAbreviado(String jugandoConAbreviado) {
        this.jugandoConAbreviado = jugandoConAbreviado;
    }

    public String getJugandoConNombre() {
        return jugandoConNombre;
    }

    public void setJugandoConNombre(String jugandoConNombre) {
        this.jugandoConNombre = jugandoConNombre;
    }

    public String getJugandoContraAbreviado() {
        if("BRK".equals(jugandoContraAbreviado.toUpperCase())){
            return "NJN";
        }else if("NOP".equals(jugandoContraAbreviado.toUpperCase())){
            return "NOH";
        }else if("CHO".equals(jugandoContraAbreviado.toUpperCase())){
            return "CHA";
        }
        return jugandoContraAbreviado.toUpperCase();
    }

    public void setJugandoContraAbreviado(String jugandoContraAbreviado) {
        this.jugandoContraAbreviado = jugandoContraAbreviado;
    }

    public String getJugandoContraNombre() {
        return jugandoContraNombre;
    }

    public void setJugandoContraNombre(String jugandoContraNombre) {
        this.jugandoContraNombre = jugandoContraNombre;
    }

    public Integer getTanteoEquipoJugador() {
        return tanteoEquipoJugador;
    }

    public void setTanteoEquipoJugador(Integer tanteoEquipoJugador) {
        this.tanteoEquipoJugador = tanteoEquipoJugador;
    }

    public Integer getTanteoEquipoRival() {
        return tanteoEquipoRival;
    }

    public void setTanteoEquipoRival(Integer tanteoEquipoRival) {
        this.tanteoEquipoRival = tanteoEquipoRival;
    }

    public Integer getSegundos() {
        return segundos;
    }

    public void setSegundos(Integer segundos) {
        this.segundos = segundos;
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

    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
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

    public Integer getPartidoNumero() {
        return partidoNumero;
    }

    public void setPartidoNumero(Integer partidoNumero) {
        this.partidoNumero = partidoNumero;
    }

    public boolean isMaximaAnotacion() {
        return maximaAnotacion;
    }

    public void setMaximaAnotacion(boolean maximaAnotacion) {
        this.maximaAnotacion = maximaAnotacion;
    }

    public boolean isMaximaMasMenos() {
        return maximaMasMenos;
    }

    public void setMaximaMasMenos(boolean maximaMasMenos) {
        this.maximaMasMenos = maximaMasMenos;
    }

    public boolean isMaximaTapones() {
        return maximaTapones;
    }

    public void setMaximaTapones(boolean maximaTapones) {
        this.maximaTapones = maximaTapones;
    }

    public boolean isMaximaPerdidas() {
        return maximaPerdidas;
    }

    public void setMaximaPerdidas(boolean maximaPerdidas) {
        this.maximaPerdidas = maximaPerdidas;
    }

    public boolean isMaximaRobos() {
        return maximaRobos;
    }

    public void setMaximaRobos(boolean maximaRobos) {
        this.maximaRobos = maximaRobos;
    }

    public boolean isMaximaAsistencias() {
        return maximaAsistencias;
    }

    public void setMaximaAsistencias(boolean maximaAsistencias) {
        this.maximaAsistencias = maximaAsistencias;
    }

    public boolean isMaximaRebotes() {
        return maximaRebotes;
    }

    public void setMaximaRebotes(boolean maximaRebotes) {
        this.maximaRebotes = maximaRebotes;
    }

    public boolean isMaximaRebotesDefensa() {
        return maximaRebotesDefensa;
    }

    public void setMaximaRebotesDefensa(boolean maximaRebotesDefensa) {
        this.maximaRebotesDefensa = maximaRebotesDefensa;
    }

    public boolean isMaximaRebotesAtaque() {
        return maximaRebotesAtaque;
    }

    public void setMaximaRebotesAtaque(boolean maximaRebotesAtaque) {
        this.maximaRebotesAtaque = maximaRebotesAtaque;
    }

    public boolean isMaximaTiroLibrePorcentaje() {
        return maximaTiroLibrePorcentaje;
    }

    public void setMaximaTiroLibrePorcentaje(boolean maximaTiroLibrePorcentaje) {
        this.maximaTiroLibrePorcentaje = maximaTiroLibrePorcentaje;
    }

    public boolean isMaximaTiroLibreTirado() {
        return maximaTiroLibreTirado;
    }

    public void setMaximaTiroLibreTirado(boolean maximaTiroLibreTirado) {
        this.maximaTiroLibreTirado = maximaTiroLibreTirado;
    }

    public boolean isMaximaTiroLibreMetido() {
        return maximaTiroLibreMetido;
    }

    public void setMaximaTiroLibreMetido(boolean maximaTiroLibreMetido) {
        this.maximaTiroLibreMetido = maximaTiroLibreMetido;
    }

    public boolean isMaximaTriplePorcentaje() {
        return maximaTriplePorcentaje;
    }

    public void setMaximaTriplePorcentaje(boolean maximaTriplePorcentaje) {
        this.maximaTriplePorcentaje = maximaTriplePorcentaje;
    }

    public boolean isMaximaTripleTirado() {
        return maximaTripleTirado;
    }

    public void setMaximaTripleTirado(boolean maximaTripleTirado) {
        this.maximaTripleTirado = maximaTripleTirado;
    }

    public boolean isMaximaTripleMetido() {
        return maximaTripleMetido;
    }

    public void setMaximaTripleMetido(boolean maximaTripleMetido) {
        this.maximaTripleMetido = maximaTripleMetido;
    }

    public boolean isMaximaTirosCampoPorcentaje() {
        return maximaTirosCampoPorcentaje;
    }

    public void setMaximaTirosCampoPorcentaje(boolean maximaTirosCampoPorcentaje) {
        this.maximaTirosCampoPorcentaje = maximaTirosCampoPorcentaje;
    }

    public boolean isMaximaTirosCampoTirado() {
        return maximaTirosCampoTirado;
    }

    public void setMaximaTirosCampoTirado(boolean maximaTirosCampoTirado) {
        this.maximaTirosCampoTirado = maximaTirosCampoTirado;
    }

    public boolean isMaximaTirosCampoMetido() {
        return maximaTirosCampoMetido;
    }

    public void setMaximaTirosCampoMetido(boolean maximaTirosCampoMetido) {
        this.maximaTirosCampoMetido = maximaTirosCampoMetido;
    }

    public boolean isMaximaMinutos() {
        return maximaMinutos;
    }

    public void setMaximaMinutos(boolean maximaMinutos) {
        this.maximaMinutos = maximaMinutos;
    }
    
    
}
