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
        	
        	recogerEquiposTemporadaConferencia(temporada,year);
			
			MongoCursor<Document> listaPartidos=devolverPartidos(temporada);
	        
	        while (listaPartidos.hasNext()) {
	        	operarPartido(listaPartidos.next());
	        }
        }
        
        return ordenarClasificacion(conferencia);
        
	}
	
	private static List<Equipo> ordenarClasificacionDivision(String division){
		
		List<Equipo> equiposConferencia = obtenerEquiposConferencia(division);
		
		Collections.sort(equiposConferencia,
        		Comparator.comparingInt(Equipo::getVictorias).reversed());
		
		// Ordenar la lista de equipos primero por división y luego por número (de más a menos)
        Collections.sort(listaEquipos, new Comparator<Equipo>() {
            @Override
            public int compare(Equipo equipo1, Equipo equipo2) {
                // Comparar primero por división
                int comparacionDivision = equipo1.getDivision().compareTo(equipo2.getDivision());
                if (comparacionDivision != 0) {
                    return comparacionDivision;
                }

                // Si las divisiones son iguales, comparar por número (de más a menos)
                return -1 * Integer.compare(equipo1.getVictorias(), equipo2.getVictorias());
            }
        });

        // Asignar la posición para cada equipo dentro de su división (de más a menos)
        String divisionActual = null;
        int contadorPosicion = 0;

        for (Equipo equipo : listaEquipos) {
            if (!equipo.getDivision().equals(divisionActual)) {
                // Cambio de división, reiniciar el contador de posición
                divisionActual = equipo.getDivision();
                contadorPosicion = 1;
            }

            equipo.setPosicion(contadorPosicion);
            contadorPosicion++;
        }
		
		return equiposConferencia;
	}
	

	
	private static List<Equipo> ordenarClasificacion(String conferencia){
		
		List<Equipo> equiposConferencia = obtenerEquiposConferencia(conferencia);
		
		Collections.sort(equiposConferencia,
        		Comparator.comparingInt(Equipo::getVictorias).reversed());
		
		String orden="";
    	int posicion=0;
		
		for(Equipo team : equiposConferencia) {
    		if(team.getConferencia().equals(orden)) {
    			posicion++;
    			team.setPosicion(posicion);
    		}else {
    			orden = team.getConferencia();
    			posicion=1;
    			team.setPosicion(posicion);
    		}
    	}
		
		return equiposConferencia;
	}
	
	private static List<Equipo> obtenerEquiposConferencia(String conferencia){
		return listaEquipos.stream()
                .filter(equipo -> equipo.getConferencia().equals(conferencia))
                .collect(Collectors.toList());
	}
	
	// CLASIFICACION DIVISIONES
	
	public static List<Equipo> devolverClasificacionDivision(String temporada, String division,int year){
		
		listaEquipos.clear();
		
		mongoNuevo = new MongoClient(host,puertoHost);
        
        if(mongoNuevo!=null) {
        	
        	recogerEquiposTemporadaDivision(temporada,year);
			
        	MongoCursor<Document> listaPartidos=devolverPartidos(temporada);
	        
	        while (listaPartidos.hasNext()) {
	        	operarPartido(listaPartidos.next());
	        }
        }
        
        return ordenarClasificacionDivision(division);
        
		
	}
	
	private static List<Equipo> obtenerEquiposDivision(String division){
		return listaEquipos.stream()
                .filter(equipo -> equipo.getDivision().equals(division))
                .collect(Collectors.toList());
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
	
	private static void recogerEquiposTemporadaConferencia(String temporada,int year) {
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
								ListaEquipos.findFotoByTeam(nombreEquipo),
								"Conferencia "+ListaEquipos.findConferenciaAntiguaByTeam(nombreEquipo).toUpperCase()));
			}else {
				listaEquipos.add(new Equipo(nombreEquipo,
						ListaEquipos.findConferenciaNuevaByTeam(nombreEquipo),
						ListaEquipos.findDivisionNuevaByTeam(nombreEquipo),
						ListaEquipos.findFotoByTeam(nombreEquipo),
						"Conferencia "+ListaEquipos.findConferenciaNuevaByTeam(nombreEquipo).toUpperCase()));
			}
		}
	}
	
	private static void recogerEquiposTemporadaDivision(String temporada,int year) {
		
		dbNuevo = mongoNuevo.getDatabase("NBA");
		
		MongoCursor<String> c = dbNuevo.getCollection(temporada).
				distinct("equipoLocal.nombre", String.class).iterator();
		
		
		while(c.hasNext()) {
			String nombreEquipo = c.next().replace("/", " ");
			if(year<2004) {
				listaEquipos.add(new Equipo(nombreEquipo,
								ListaEquipos.findConferenciaAntiguaByTeam(nombreEquipo),
								ListaEquipos.findDivisionAntiguaByTeam(nombreEquipo),
								ListaEquipos.findFotoByTeam(nombreEquipo),
								"División "+ListaEquipos.findDivisionAntiguaByTeam(nombreEquipo).toUpperCase()));
			}else {
				listaEquipos.add(new Equipo(nombreEquipo,
						ListaEquipos.findConferenciaNuevaByTeam(nombreEquipo),
						ListaEquipos.findDivisionNuevaByTeam(nombreEquipo),
						ListaEquipos.findFotoByTeam(nombreEquipo),
						"Division "+ListaEquipos.findDivisionNuevaByTeam(nombreEquipo).toUpperCase()));
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