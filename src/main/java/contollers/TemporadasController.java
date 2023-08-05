package contollers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import temporadas.CalcularMaximosTemporada;
import temporadas.ClasificacionTemporada;
import temporadas.Equipo;
import temporadas.EstadisticasMaximos;
import util.ClaseEstadisticaNormalTotales;
import util.Utilidades;

@ViewScoped
@ManagedBean(name ="temporadas")
@Data
@EqualsAndHashCode(callSuper=false)
public class TemporadasController extends BaseController{

    private static final long serialVersionUID = -7503343479663707367L;
	private String temporadaElegida="";
    private List<SelectItem> selectMenuTemporadas;
    
    // ClasificacionTemporadas
    private List<Equipo> clasificacionOeste;
    private List<Equipo> clasificacionEste;
    private String clasificacionEleccion="";
    
    // Maximos temporada
    private ArrayList<EstadisticasMaximos> listaMaximos = new ArrayList<>();
    private CalcularMaximosTemporada maximos = new CalcularMaximosTemporada();
    
    private int porcentajeTiroCampo=300;
    private String radioFg="media";
    private String radioFga="media";
    
    private String radioDosFg="media";
    private String radioDosFga="media";
    private int porcentajeDos=300;
    
    private String radioTripleFg="media";
    private String radioTripleFga="media";
    private int porcentajeTriple=300;
    
    private String radioLibreFg="media";
    private String radioLibreFga="media";
    private int porcentajeLibre=300;
    
    private String radioRebote="media";
    private String radioReboteDefensivo="media";
    private String radioReboteOfensivo="media";
    
    private String radioAsistencia="media";
    private String radioRobo="media";
    private String radioTapon="media";
    
    private String radioPerdida="media";
    private String radioPersonal="media";
    private String radioMinuto="media";
    
    @PostConstruct
    public void init() {
        Locale defaultLocale = Locale.getDefault();
        String decimalSeparator = getDecimalSeparatorFromLocale(defaultLocale);
        setDecimalSeparatorForJSF(decimalSeparator);
        clasificacionEleccion="conferencia";
    }
    
    public List<SelectItem> getSelectMenuTemporadas() {
        if(selectMenuTemporadas == null){
            selectMenuTemporadas = Utilidades.devolverTemporadas();
        }
        return selectMenuTemporadas;
    }
    
    public void verClasificacion(){
    	
    	// Clasificacion
    	if(DIVISION.equals(clasificacionEleccion)) {
    		clasificacionDivision();
    	}else{
    		clasificacionConferencia();
    	}
    	
    	// Maximos temporada
    	maximos.calcularMaximos(temporadaElegida);
    }
    
    public void clasificacionConferencia(){
    	
    	clasificacionOeste=ClasificacionTemporada.devolverClasificacionConferencia(
    			temporadaElegida,CONFERENCIA_OESTE,
    			Integer.parseInt(temporadaElegida.substring(6,10)));
    	
    	clasificacionEste=ClasificacionTemporada.devolverClasificacionConferencia(
    			temporadaElegida,CONFERENCIA_ESTE,
    			Integer.parseInt(temporadaElegida.substring(6,10)));
    	
    	

    	System.out.println("Entramos para actualizar temporada: "+temporadaElegida);
    }
    
    public void clasificacionDivision(){
    	
    	clasificacionOeste=ClasificacionTemporada.devolverClasificacionDivision(
    			temporadaElegida,CONFERENCIA_OESTE,
    			Integer.parseInt(temporadaElegida.substring(6,10)));
    	
    	clasificacionEste=ClasificacionTemporada.devolverClasificacionDivision(
    			temporadaElegida,CONFERENCIA_ESTE,
    			Integer.parseInt(temporadaElegida.substring(6,10)));
    	
    	

    	System.out.println("Entramos para actualizar temporada: "+temporadaElegida);
    }
    
    public String devolverTemporadaPretty(){
    	
    	if(!this.temporadaElegida.equals("")) {
    		StringBuilder temporada = new StringBuilder(this.temporadaElegida.replace("season", ""));
        	return (temporada.insert(4, " / ")).toString();
    	}
    	return "";
    }
    
    // TIROS CAMPO
    public List<EstadisticasMaximos> getTirosCampoMetidosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosCampoMetidosPartido(radioFg);
    	}
    	return null;
    }
    
    public List<EstadisticasMaximos> getTirosCampoIntentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosCampoIntentadosPartido(radioFga);
    	}
    	return null;
    }
    
    public List<EstadisticasMaximos> getTirosCampoPorcentajePartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosCampoPorcentajePartido(porcentajeTiroCampo);
    	}
    	return null;
    }
    
    public boolean esFgMedia() {
    	if("media".equals(radioFg)) {
    		return true;
    	}
    	return false;
    }

    public boolean esFgaMedia() {
    	if("media".equals(radioFga)) {
    		return true;
    	}
    	return false;
    }
    
    // TRIPLES
    public List<EstadisticasMaximos>  getTriplesMetidosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTriplesMetidosPartido(radioTripleFg);
    	}
    	return null;
    }
    public List<EstadisticasMaximos>  getTriplesIntentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTriplesIntentadosPartido(radioTripleFga);
    	}
    	return null;
    }
    public List<EstadisticasMaximos>  getTriplesPorcentajePartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTriplesPorcentajePArtido(porcentajeTriple);
    	}
    	return null;
    }
    
    public boolean esTripleFgMedia() {
    	if("media".equals(radioTripleFg)) {
    		return true;
    	}
    	return false;
    }

    public boolean esTripleFgaMedia() {
    	if("media".equals(radioTripleFga)) {
    		return true;
    	}
    	return false;
    }
    
    // DOS PUNTOS
    public List<EstadisticasMaximos> getDosMetidosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getDosMetidosPartido(radioDosFg);
    	}
    	return null;
    }
    public List<EstadisticasMaximos> getDosInTentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getDosInTentadosPartido(radioDosFga);
    	}
    	return null;
    }
    public List<EstadisticasMaximos> getDosPorcentajePartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getDosPorcentajePartido(porcentajeDos);
    	}
    	return null;
    }
    
    public boolean esFgDosMedia() {
    	if("media".equals(radioDosFg)) {
    		return true;
    	}
    	return false;
    }

    public boolean esFgaDosMedia() {
    	if("media".equals(radioDosFga)) {
    		return true;
    	}
    	return false;
    }
    
    // TIROS LIBRES
    public List<EstadisticasMaximos>  getTirosLibresMetidosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosLibresMetidosPartido(radioLibreFg);
    	}
    	return null;
    }
    public List<EstadisticasMaximos>  getTirosLibresIntentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosLibresIntentadosPartido(radioLibreFga);
    	}
    	return null;
    }
    public List<EstadisticasMaximos>  getTirosLibresPorcentajePartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosLibresPorcentajePartido(porcentajeLibre);
    	}
    	return null;
    }
    
    public boolean esFgLibresMedia() {
    	if("media".equals(radioLibreFg)) {
    		return true;
    	}
    	return false;
    }

    public boolean esFgaLibresMedia() {
    	if("media".equals(radioLibreFga)) {
    		return true;
    	}
    	return false;
    }
    
    // REBOTES
    public List<EstadisticasMaximos> getRebotesPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getRebotesPartido(radioRebote);
    	}
    	return null;
    }
    public List<EstadisticasMaximos>getRebotesDefensivosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getRebotesDefensivosPartido(radioReboteDefensivo);
    	}
    	return null;
    }
    public List<EstadisticasMaximos> getRebotesOfensivosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getRebotesOfensivosPartido(radioReboteOfensivo);
    	}
    	return null;
    }
    
    public boolean esReboteMedia() {
    	if("media".equals(radioRebote)) {
    		return true;
    	}
    	return false;
    }

    public boolean esReboteDefensivoMedia() {
    	if("media".equals(radioReboteDefensivo)) {
    		return true;
    	}
    	return false;
    }

    public boolean esReboteOfensivoMedia() {
    	if("media".equals(radioReboteOfensivo)) {
    		return true;
    	}
    	return false;
    }
    
    // ASISTENCIAS
    public List<EstadisticasMaximos> getAsistenciasPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getAsistenciasPartido(radioAsistencia);
    	}
    	return null;
    }
    public boolean esAsistenciaMedia() {
    	if("media".equals(radioAsistencia)) {
    		return true;
    	}
    	return false;
    }
    
    // ROBOS
    public List<EstadisticasMaximos> getRobosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getRobosPartido(radioRobo);
    	}
    	return null;
    }
    public boolean esRoboMedia() {
    	if("media".equals(radioRobo)) {
    		return true;
    	}
    	return false;
    }
    
    // TAPONES
    public List<EstadisticasMaximos> getTaponesPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTaponesPartido(radioTapon);
    	}
    	return null;
    }
    public boolean esTaponMedia() {
    	if("media".equals(radioTapon)) {
    		return true;
    	}
    	return false;
    }
    
    // PERDIDAS
    public List<EstadisticasMaximos> getPerdidasPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getPerdidasPartido(radioPerdida);
    	}
    	return null;
    }
    public boolean esPerdidaMedia() {
    	if("media".equals(radioPerdida)) {
    		return true;
    	}
    	return false;
    }
    
    // PERSONALES
    public List<EstadisticasMaximos>getPersonalesPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getPersonalesPartido(radioPersonal);
    	}
    	return null;
    }
    public boolean esPersonalMedia() {
    	if("media".equals(radioPersonal)) {
    		return true;
    	}
    	return false;
    }
    
    // MINUTOS
    public List<EstadisticasMaximos> getMinutosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getMinutosPartido(radioMinuto);
    	}
    	return null;
    }
    public boolean esMinutoMedia() {
    	if("media".equals(radioMinuto)) {
    		return true;
    	}
    	return false;
    }
    
    // PUNTOS PARTIDO
    public ArrayList<EstadisticasMaximos> getPuntosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getPuntosPartido();
    	}
    	return null;
    }
    
    
}
