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
import util.ListaEquipos;
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
    
    // Bracket
    private String primeraRonda="Conference First Round";
    private String semifinalConferencia="Conference Semifinals";
    private String finalConferencia="Conference Finals";
    private String finalNBA="NBA Finals";
    
    private String rondaOeste1="";
    private String rondaOeste1Victorias="";
    private String rondaOeste2="";
    private String rondaOeste2Victorias="";
    private String rondaOeste3="";
    private String rondaOeste3Victorias="";
    private String rondaOeste4="";
    private String rondaOeste4Victorias="";
    private String rondaOeste5="";
    private String rondaOeste5Victorias="";
    private String rondaOeste6="";
    private String rondaOeste6Victorias="";
    private String rondaOeste7="";
    private String rondaOeste7Victorias="";
    private String rondaOeste8="";
    private String rondaOeste8Victorias="";
    
    private String semifinal1Oeste="";
    private String semifinal1OesteVictorias="";
    private String semifinal2Oeste="";
    private String semifinal2OesteVictorias="";
    private String semifinal3Oeste="";
    private String semifinal3OesteVictorias="";
    private String semifinal4Oeste="";
    private String semifinal4OesteVictorias="";
    
    private String final1Oeste="";
    private String final1OesteVictorias="";
    private String final2Oeste="";
    private String final2OesteVictorias="";
    
    private String finalistaOeste="";
    private String finalistaOesteVictorias="";
    private String iconoFinalistaOeste="";
    
    private String rondaEste1="";
    private String rondaEste1Victorias="";
    private String rondaEste2="";
    private String rondaEste2Victorias="";
    private String rondaEste3="";
    private String rondaEste3Victorias="";
    private String rondaEste4="";
    private String rondaEste4Victorias="";
    private String rondaEste5="";
    private String rondaEste5Victorias="";
    private String rondaEste6="";
    private String rondaEste6Victorias="";
    private String rondaEste7="";
    private String rondaEste7Victorias="";
    private String rondaEste8="";
    private String rondaEste8Victorias="";
    
    private String semifinal1Este="";
    private String semifinal1EsteVictorias="";
    private String semifinal2Este="";
    private String semifinal2EsteVictorias="";
    private String semifinal3Este="";
    private String semifinal3EsteVictorias="";
    private String semifinal4Este="";
    private String semifinal4EsteVictorias="";
    
    private String final1Este="";
    private String final1EsteVictorias="";
    private String final2Este="";
    private String final2EsteVictorias="";
    
    private String finalistaEste="";
    private String finalistaEsteVictorias="";
    private String iconoFinalistaEste="";
    
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
    	
    	// Bracket
    	ordenarBracket();
    }
    
    private void ordenarBracket() {
		rondaOeste1=clasificacionOeste.get(0).getNombre();
		rondaOeste2=clasificacionOeste.get(1).getNombre();
		rondaOeste3=clasificacionOeste.get(2).getNombre();
		rondaOeste4=clasificacionOeste.get(3).getNombre();
		rondaOeste5=clasificacionOeste.get(4).getNombre();
		rondaOeste6=clasificacionOeste.get(5).getNombre();
		rondaOeste7=clasificacionOeste.get(6).getNombre();
		rondaOeste8=clasificacionOeste.get(7).getNombre();
		
		rondaEste1=clasificacionEste.get(0).getNombre();
		rondaEste2=clasificacionEste.get(1).getNombre();
		rondaEste3=clasificacionEste.get(2).getNombre();
		rondaEste4=clasificacionEste.get(3).getNombre();
		rondaEste5=clasificacionEste.get(4).getNombre();
		rondaEste6=clasificacionEste.get(5).getNombre();
		rondaEste7=clasificacionEste.get(6).getNombre();
		rondaEste8=clasificacionEste.get(7).getNombre();
		
		
		rondaOeste1Victorias=ClasificacionTemporada.devolverVictorias(rondaOeste1,temporadaElegida,primeraRonda);
		rondaOeste8Victorias=ClasificacionTemporada.devolverVictorias(rondaOeste8,temporadaElegida,primeraRonda);
		
		rondaOeste4Victorias=ClasificacionTemporada.devolverVictorias(rondaOeste4, temporadaElegida, primeraRonda);
		rondaOeste5Victorias=ClasificacionTemporada.devolverVictorias(rondaOeste5, temporadaElegida, primeraRonda);
		
		rondaOeste3Victorias=ClasificacionTemporada.devolverVictorias(rondaOeste3, temporadaElegida, primeraRonda);
		rondaOeste6Victorias=ClasificacionTemporada.devolverVictorias(rondaOeste6, temporadaElegida, primeraRonda);
		
		rondaOeste2Victorias=ClasificacionTemporada.devolverVictorias(rondaOeste2, temporadaElegida, primeraRonda);
		rondaOeste7Victorias=ClasificacionTemporada.devolverVictorias(rondaOeste7, temporadaElegida, primeraRonda);
		
		

		semifinal1Oeste=
				(Integer.parseInt(rondaOeste1Victorias)>Integer.parseInt(rondaOeste8Victorias))?
						rondaOeste1:rondaOeste8;

		semifinal2Oeste=
				(Integer.parseInt(rondaOeste4Victorias)>Integer.parseInt(rondaOeste5Victorias))?
						rondaOeste4:rondaOeste5;

		semifinal3Oeste=
				(Integer.parseInt(rondaOeste3Victorias)>Integer.parseInt(rondaOeste6Victorias))?
						rondaOeste3:rondaOeste6;

		semifinal4Oeste=
				(Integer.parseInt(rondaOeste2Victorias)>Integer.parseInt(rondaOeste7Victorias))?
						rondaOeste2:rondaOeste7;

		semifinal1OesteVictorias=ClasificacionTemporada.devolverVictorias(semifinal1Oeste, temporadaElegida, semifinalConferencia);
		semifinal2OesteVictorias=ClasificacionTemporada.devolverVictorias(semifinal2Oeste, temporadaElegida, semifinalConferencia);
		semifinal3OesteVictorias=ClasificacionTemporada.devolverVictorias(semifinal3Oeste, temporadaElegida, semifinalConferencia);
		semifinal4OesteVictorias=ClasificacionTemporada.devolverVictorias(semifinal4Oeste, temporadaElegida, semifinalConferencia);
		
		
		final1Oeste=
				(Integer.parseInt(semifinal1OesteVictorias)>Integer.parseInt(semifinal2OesteVictorias))?
						semifinal1Oeste:semifinal2Oeste;
		
		final2Oeste=
				(Integer.parseInt(semifinal3OesteVictorias)>Integer.parseInt(semifinal4OesteVictorias))?
						semifinal3Oeste:semifinal4Oeste;
		
		final1OesteVictorias=ClasificacionTemporada.devolverVictorias(final1Oeste, temporadaElegida, finalConferencia);
		final2OesteVictorias=ClasificacionTemporada.devolverVictorias(final2Oeste, temporadaElegida, finalConferencia);
		
		finalistaOeste=
				(Integer.parseInt(final1OesteVictorias)>Integer.parseInt(final2OesteVictorias))?
						final1Oeste:final2Oeste;
		
		finalistaOesteVictorias=ClasificacionTemporada.devolverVictorias(finalistaOeste, temporadaElegida, finalNBA);
		iconoFinalistaOeste = ListaEquipos.findFotoByTeam(finalistaOeste);
		
		
		
		
		rondaEste1Victorias=ClasificacionTemporada.devolverVictorias(rondaEste1,temporadaElegida,primeraRonda);
        rondaEste8Victorias=ClasificacionTemporada.devolverVictorias(rondaEste8,temporadaElegida,primeraRonda);
        
        rondaEste4Victorias=ClasificacionTemporada.devolverVictorias(rondaEste4, temporadaElegida, primeraRonda);
        rondaEste5Victorias=ClasificacionTemporada.devolverVictorias(rondaEste5, temporadaElegida, primeraRonda);
        
        rondaEste3Victorias=ClasificacionTemporada.devolverVictorias(rondaEste3, temporadaElegida, primeraRonda);
        rondaEste6Victorias=ClasificacionTemporada.devolverVictorias(rondaEste6, temporadaElegida, primeraRonda);
        
        rondaEste2Victorias=ClasificacionTemporada.devolverVictorias(rondaEste2, temporadaElegida, primeraRonda);
        rondaEste7Victorias=ClasificacionTemporada.devolverVictorias(rondaEste7, temporadaElegida, primeraRonda);
        
        

        semifinal1Este=
                (Integer.parseInt(rondaEste1Victorias)>Integer.parseInt(rondaEste8Victorias))?
                        rondaEste1:rondaEste8;

        semifinal2Este=
                (Integer.parseInt(rondaEste4Victorias)>Integer.parseInt(rondaEste5Victorias))?
                        rondaEste4:rondaEste5;

        semifinal3Este=
                (Integer.parseInt(rondaEste3Victorias)>Integer.parseInt(rondaEste6Victorias))?
                        rondaEste3:rondaEste6;

        semifinal4Este=
                (Integer.parseInt(rondaEste2Victorias)>Integer.parseInt(rondaEste7Victorias))?
                        rondaEste2:rondaEste7;

        semifinal1EsteVictorias=ClasificacionTemporada.devolverVictorias(semifinal1Este, temporadaElegida, semifinalConferencia);
        semifinal2EsteVictorias=ClasificacionTemporada.devolverVictorias(semifinal2Este, temporadaElegida, semifinalConferencia);
        semifinal3EsteVictorias=ClasificacionTemporada.devolverVictorias(semifinal3Este, temporadaElegida, semifinalConferencia);
        semifinal4EsteVictorias=ClasificacionTemporada.devolverVictorias(semifinal4Este, temporadaElegida, semifinalConferencia);
        
        
        final1Este=
                (Integer.parseInt(semifinal1EsteVictorias)>Integer.parseInt(semifinal2EsteVictorias))?
                        semifinal1Este:semifinal2Este;
        
        final2Este=
                (Integer.parseInt(semifinal3EsteVictorias)>Integer.parseInt(semifinal4EsteVictorias))?
                        semifinal3Este:semifinal4Este;
        
        final1EsteVictorias=ClasificacionTemporada.devolverVictorias(final1Este, temporadaElegida, finalConferencia);
        final2EsteVictorias=ClasificacionTemporada.devolverVictorias(final2Este, temporadaElegida, finalConferencia);
        
        finalistaEste=
                (Integer.parseInt(final1EsteVictorias)>Integer.parseInt(final2EsteVictorias))?
                        final1Este:final2Este;
        
        finalistaEsteVictorias=ClasificacionTemporada.devolverVictorias(finalistaEste, temporadaElegida, finalNBA);
        iconoFinalistaEste = ListaEquipos.findFotoByTeam(finalistaEste);
		
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
    
    public String getTemporadaElegida() {
    	return temporadaElegida;
    }
    
    
}