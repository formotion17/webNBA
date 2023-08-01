package temporadas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import contollers.BaseController;
import util.ListaEquipos;

public class ClasificacionTemporada extends BaseController{
	
	private static final long serialVersionUID = -2618832934005476860L;
	private static ArrayList<Equipo> listaEquipos = new ArrayList<>();
	private static MongoClient mongoNuevo;
	private static MongoDatabase dbNuevo;
	
	
	// CLASIFICACION CONFERENCIAS
	public static List<Equipo> devolverClasificacionConferencia(String temporada,String conferencia,int year){
		
		listaEquipos.clear();
		
		mongoNuevo = new MongoClient(host,puertoHost);
        
        if(null!=mongoNuevo) {
        	
        	recogerEquiposTemporadaDivision(temporada,year);
			
			MongoCursor<Document> listaPartidos=devolverPartidos(temporada);
	        
	        while (listaPartidos.hasNext()) {
	        	operarPartido(listaPartidos.next());
	        }
        }
        
        // ordenarConferencia(conferencia);
        
        return ordenarConferencia(conferencia);
        
	}
	

	
	private static List<Equipo> ordenarConferencia(String conferencia){
		
		List<Equipo> equiposConferencia = obtenerEquiposConferencia(conferencia);
		
		Collections.sort(equiposConferencia,
        		Comparator.comparingInt(Equipo::getVictorias).reversed());
		
		return equiposConferencia;
	}
	
	private static List<Equipo> obtenerEquiposConferencia(String conferencia){
		return listaEquipos.stream()
                .filter(equipo -> equipo.getConferencia().equals(conferencia))
                .collect(Collectors.toList());
	}
	
	// CLASIFICACION DIVISIONES
	
	public static List<Equipo> devolverClasificacionDivision(String temporada, String division){
		
		listaEquipos.clear();
		
		mongoNuevo = new MongoClient(host,puertoHost);
        
        if(mongoNuevo!=null) {
        	
        	recogerEquiposTemporadaDivision(temporada,2000);
			
			MongoCursor<Document> listaPartidos=devolverPartidos(temporada);
	        
	        while (listaPartidos.hasNext()) {
	        	operarPartido(listaPartidos.next());
	        }
        }
        
        ordenarPartidosDivision(2000);
        
        return obtenerEquiposConferencia("hola");
		
	}
	
	private static void ordenarPartidosDivision(int year) {
		
		if(year<2004) {
			ordenarDivisionAntiguos();
		}else {
			ordenarDivisionNuevos();
		}
	}
	
	private static List<Equipo> obtenerEquiposDivision(String division){
		return listaEquipos.stream()
                .filter(equipo -> equipo.getDivision().equals(division))
                .collect(Collectors.toList());
	}
	
	private static void ordenarDivisionAntiguos() {
		
		// Filtrar los equipos que tienen la conferencia deseada
        List<Equipo> equiposDivisionAtlantico = obtenerEquiposDivision(DIVISION_ATLANTICO);
        List<Equipo> equiposDivisionCentral = obtenerEquiposDivision(DIVISION_CENTRAL);
        
        List<Equipo> equiposDivisionMedioOeste = obtenerEquiposDivision(DIVISION_MEDIO_OESTE);
        List<Equipo> equiposDivisionPacifico = obtenerEquiposDivision(DIVISION_PACIFICO);
        
     // Ordenar la lista de equipos por victorias en orden descendente
        Collections.sort(equiposDivisionAtlantico, Comparator.comparingInt(Equipo::getVictorias).reversed());
        Collections.sort(equiposDivisionCentral, Comparator.comparingInt(Equipo::getVictorias).reversed());
        Collections.sort(equiposDivisionMedioOeste, Comparator.comparingInt(Equipo::getVictorias).reversed());
        Collections.sort(equiposDivisionPacifico, Comparator.comparingInt(Equipo::getVictorias).reversed());
	}
	
	private static void ordenarDivisionNuevos() {
		// Filtrar los equipos que tienen la conferencia deseada
        List<Equipo> equiposDivisionAtlantico = obtenerEquiposDivision(DIVISION_ATLANTICO);
        List<Equipo> equiposDivisionCentral = obtenerEquiposDivision(DIVISION_CENTRAL);
        List<Equipo> equiposDivisionSureste = obtenerEquiposDivision(DIVISION_SURESTE);
        
        List<Equipo> equiposDivisionNoreste = obtenerEquiposDivision(DIVISION_NOROESTE);
        List<Equipo> equiposDivisionPacifico = obtenerEquiposDivision(DIVISION_PACIFICO);
        List<Equipo> equiposDivisionSuroeste = obtenerEquiposDivision(DIVISION_SUROESTE);
        
        
     // Ordenar la lista de equipos por victorias en orden descendente
        Collections.sort(equiposDivisionAtlantico, Comparator.comparingInt(Equipo::getVictorias).reversed());
        Collections.sort(equiposDivisionCentral, Comparator.comparingInt(Equipo::getVictorias).reversed());
        Collections.sort(equiposDivisionSureste, Comparator.comparingInt(Equipo::getVictorias).reversed());
        
        Collections.sort(equiposDivisionNoreste, Comparator.comparingInt(Equipo::getVictorias).reversed());
        Collections.sort(equiposDivisionPacifico, Comparator.comparingInt(Equipo::getVictorias).reversed());
        Collections.sort(equiposDivisionSuroeste, Comparator.comparingInt(Equipo::getVictorias).reversed());
	}

	
	private static void operarPartido(Document partido) {
		Equipo equipoLocal = devolverEquipo((String)
				((Document) partido.get(ATRIBUTO_PARTIDO_LOCAL)).get(ATRIBUTO_EQUIPO_NOMBRE));
        int tanteoLocal = Integer.parseInt(((String)
        		((Document) partido.get(ATRIBUTO_PARTIDO_LOCAL)).get(ATRIBUTO_EQUIPO_TANTEO)).trim());
        
        Equipo equipoVisitante = devolverEquipo((String) 
        		((Document) partido.get(ATRIBUTO_PARTIDO_VISITANTE)).get(ATRIBUTO_EQUIPO_NOMBRE));                
        int tanteoVisitante = Integer.parseInt(((String)
        		((Document) partido.get(ATRIBUTO_PARTIDO_VISITANTE)).get(ATRIBUTO_EQUIPO_TANTEO)).trim());
        
        if(tanteoLocal>tanteoVisitante) {
        	
        	equipoLocal.addVictoriaLocal();
        	equipoLocal.addVictoria();
        	equipoVisitante.addDerrotaVisitante();
        	equipoVisitante.addDerrota();
        	
        	if(equipoLocal.getDivision().equals(equipoVisitante.getDivision())) {
            	equipoLocal.addVictoriaDivisionLocal();
            	equipoVisitante.addDerrotaDivisionVisitante();
        	}
        	if(equipoLocal.getConferencia().equals(equipoVisitante.getConferencia())) {
        		equipoLocal.addVictoriaConferenciaLocal();
        		equipoVisitante.addDerrotaConferenciaVisitante();
        	}
        	
        }else{

        	equipoLocal.addDerrotaLocal();
        	equipoVisitante.addVictoriaVisitante();
        	equipoLocal.addDerrota();
        	equipoVisitante.addVictoria();
        	
        	if(equipoLocal.getDivision().equals(equipoVisitante.getDivision())) {
            	equipoLocal.addDerrotaDivisionLocal();
            	equipoVisitante.addVictoriaDivisionVisitante();
        	}
        	if(equipoLocal.getConferencia().equals(equipoVisitante.getConferencia())) {
        		equipoLocal.addDerrotaConferenciaLocal();
        		equipoVisitante.addVictoriaConferenciaVisitante();
        	}
        }
        equipoLocal.porcentajes();
        equipoVisitante.porcentajes();
	}
	
	
	private static MongoCursor<Document> devolverPartidos(String temporada){
		MongoCollection<Document> collection = dbNuevo.getCollection(temporada);
        
        // Crear el filtro para la consulta
        Document filter = new Document(ATRIBUTO_PARTIDO_PLAYOFF, false);
		
		// Realizar la consulta para recuperar solo el campo "nombre"
        MongoCursor<Document> cursor = collection.find(filter).projection(
        		new Document("equipoLocal.nombre", 1)
        		.append("equipoLocal.tanteo",1)
        		.append("equipoVisitante.nombre", 1)
        		.append("equipoVisitante.tanteo", 1)
        		).iterator();
        return cursor;
	}
	
	private static void recogerEquiposTemporadaDivision(String temporada,int year) {
		System.out.println("	TEMPORADA: "+temporada+"\n");
		
		dbNuevo = mongoNuevo.getDatabase("NBA");
		
		MongoCursor<String> c = dbNuevo.getCollection(temporada).
				distinct("equipoLocal.nombre", String.class).iterator();
		
		
		while(c.hasNext()) {
			String nombreEquipo = c.next().replace("/", " ");
			if(year<2004) {
				listaEquipos.add(new Equipo(nombreEquipo,
								ListaEquipos.findConferenciaAntiguaByTeam(nombreEquipo),
								ListaEquipos.findDivisionAntiguaByTeam(nombreEquipo),
								ListaEquipos.findFotoByTeam(nombreEquipo)));
			}else {
				listaEquipos.add(new Equipo(nombreEquipo,
						ListaEquipos.findConferenciaNuevaByTeam(nombreEquipo),
						ListaEquipos.findDivisionNuevaByTeam(nombreEquipo),
						ListaEquipos.findFotoByTeam(nombreEquipo)));
			}
		}
	}

	private static Equipo devolverEquipo(String nombre) {
		for(Equipo equipo:listaEquipos) {
			if(nombre.equals(equipo.getNombre())) {
				return equipo;
			}
		}
		return null;
	}
	
}