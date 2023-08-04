package temporadas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import Mapper.MapJugadorEstadisticas;
import contollers.BaseController;
import lombok.Data;

@Data
public class CalcularMaximosTemporada extends BaseController {

	private static final long serialVersionUID = -5658458007370926154L;

	private static MongoClient mongoNuevo;
	private static MongoDatabase dbNuevo;
	private ArrayList<EstadisticasMaximos> listaJugadores;
	private static DecimalFormat df = new DecimalFormat("#.##");
	private static String season="";
	
	public void calcularMaximos(String temporada) {
		
		this.season=temporada;

		listaJugadores = new ArrayList<>();

		mongoNuevo = new MongoClient(host, puertoHost);

		if (null != mongoNuevo) {

			DB db = mongoNuevo.getDB("NBA");
			DBCursor cursor;
			
			DBCollection collection =db.getCollection("totales");

			BasicDBObject allQuery = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject();
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();

			obj.add(new BasicDBObject("temporada", temporada));
			obj.add(new BasicDBObject("tiempo", "regular"));
			obj.add(new BasicDBObject("tiporesultado", "total"));

			allQuery.put("$and", obj);

			cursor = collection.find(allQuery);
			
			while(cursor.hasNext()) {

                DBObject get = cursor.next();
                
                listaJugadores.add(MapJugadorEstadisticas.devolverEstadisticasTotalesJugador(new Document((Map<String, Object>) get)));

            }

		}
		
	}
	
	private ArrayList<EstadisticasMaximos> devolverLista(){
		ArrayList<EstadisticasMaximos> lista = new ArrayList<>();

		mongoNuevo = new MongoClient(host, puertoHost);

		if (null != mongoNuevo) {

			DB db = mongoNuevo.getDB("NBA");
			DBCursor cursor;
			
			DBCollection collection =db.getCollection("totales");

			BasicDBObject allQuery = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject();
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();

			obj.add(new BasicDBObject("temporada", this.season));
			obj.add(new BasicDBObject("tiempo", "regular"));
			obj.add(new BasicDBObject("tiporesultado", "total"));

			allQuery.put("$and", obj);

			cursor = collection.find(allQuery);
			
			while(cursor.hasNext()) {

                DBObject get = cursor.next();
                
                lista.add(MapJugadorEstadisticas.devolverEstadisticasTotalesJugador(new Document((Map<String, Object>) get)));

            }

		}
		
		return lista;
	}
	
	//TIROS DE CAMPO
	
	public ArrayList<EstadisticasMaximos> getTirosCampoMetidosPartido(String radioFg){
		ArrayList<EstadisticasMaximos> lista=devolverLista();
		
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				if(radioFg.equals("media")) {
					return Double.compare(s2.getTiroCampoAnotadosPartido(), s1.getTiroCampoAnotadosPartido());
				}else {
					return Double.compare(Double.valueOf(s2.getTirosCampoMetidos()), Double.valueOf(s1.getTirosCampoMetidos()));
				}
				
			}
		};
		Collections.sort(lista, comparator);
		
		return lista;
	}
	
	public ArrayList<EstadisticasMaximos> getTirosCampoIntentadosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getTiroCampoIntentadosPartido(), s1.getTiroCampoIntentadosPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getTirosCampoPorcentajePartido(int porcentajeTiroCampo){
		
		ArrayList<EstadisticasMaximos> lista=devolverLista();

		Iterator<EstadisticasMaximos> iterador = lista.iterator();
		while (iterador.hasNext()) {
			EstadisticasMaximos objeto = iterador.next();

		    if (Integer.parseInt(objeto.getTirosCampoIntentados())<porcentajeTiroCampo) {

		        iterador.remove();
		    }
		}
		
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getTirosCampoPorcentaje(), s1.getTirosCampoPorcentaje());
			}
		};
		Collections.sort(lista, comparator);
		
		return lista;
	}
	
	//TRIPLES
	
	public ArrayList<EstadisticasMaximos> getTriplesMetidosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getTriplesAnotadosPartido(), s1.getTriplesAnotadosPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getTriplesIntentadosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getTriplesIntentadosPartido(), s1.getTriplesIntentadosPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getTriplesPorcentajePArtido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getTriplesPorcentaje(), s1.getTriplesPorcentaje());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	// DOS PUNTOS
	public ArrayList<EstadisticasMaximos> getDosMetidosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getDosPuntosMetidosPartido(), s1.getDosPuntosMetidosPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getDosInTentadosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getDosPuntosIntentadosPartido(), s1.getDosPuntosIntentadosPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getDosPorcentajePartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getDosPuntosPorcentaje(), s1.getDosPuntosPorcentaje());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	// TIROS LIBRES
	
	public ArrayList<EstadisticasMaximos> getTirosLibresMetidosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getTirosLibresAnotadosPartido(), s1.getTirosLibresAnotadosPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getTirosLibresIntentadosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getTirosLibresIntentadosPartido(), s1.getTirosLibresIntentadosPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getTirosLibresPorcentajePartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getTirosLibresPorcentaje(), s1.getTirosLibresPorcentaje());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	// PUNTOS PARTIDO
	
	public ArrayList<EstadisticasMaximos> getPuntosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getPuntosPartidos(), s1.getPuntosPartidos());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	// REBOTES
	public ArrayList<EstadisticasMaximos> getRebotesPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getRebotesPartido(), s1.getRebotesPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);

		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getRebotesDefensivosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getReboteDefensivoPartido(), s1.getReboteDefensivoPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);

		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getRebotesOfensivosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getReboteOfensivoPartido(), s1.getReboteOfensivoPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);

		return listaJugadores;
	}
	
	// ASISTENCIAS
	
	public ArrayList<EstadisticasMaximos> getAsistenciasPartido() {
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getAsistenciasPartido(), s1.getAsistenciasPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;

	}
	
	// ROBOS
	
	public ArrayList<EstadisticasMaximos> getRobosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getRobosPartido(), s1.getRobosPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
		
	}
	
	// TAPONES
	
	public ArrayList<EstadisticasMaximos> getTaponesPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getTaponesPartido(), s1.getTaponesPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
		
	}
	
	// PERDIDAS
	
	public ArrayList<EstadisticasMaximos> getPerdidasPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getPerdidasPartido(), s1.getPerdidasPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
		
	}
	
	// PERSONALES
	
	public ArrayList<EstadisticasMaximos> getPersonalesPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getFaltasPartido(), s1.getFaltasPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
		
	}
	
	// MINUTOS

	public ArrayList<EstadisticasMaximos> getMinutosPartido(){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getMinutosJugadosPartido(), s1.getMinutosJugadosPartido());
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
		
	}

}
