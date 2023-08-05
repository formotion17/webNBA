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
	
/////////////////////////TIROS DE CAMPO
	
	public ArrayList<EstadisticasMaximos> getTirosCampoMetidosPartido(String radioFg){
		
		//ArrayList<EstadisticasMaximos> lista=devolverLista();
		
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
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getTirosCampoIntentadosPartido(String radioFga){
		
		//ArrayList<EstadisticasMaximos> lista=devolverLista();ç
		
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				if(radioFga.equals("media")) {
					return Double.compare(s2.getTiroCampoIntentadosPartido(), s1.getTiroCampoIntentadosPartido());
				}else {
					return Double.compare(Double.valueOf(s2.getTirosCampoIntentados()), Double.valueOf(s1.getTirosCampoIntentados()));
				}
				
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
	
///////////////////////// DOS PUNTOS
	public ArrayList<EstadisticasMaximos> getDosMetidosPartido(String radioDosFg){
		
		//ArrayList<EstadisticasMaximos> lista=devolverLista();
		
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				if("media".equals(radioDosFg)) {
					return Double.compare(s2.getDosPuntosMetidosPartido(), s1.getDosPuntosMetidosPartido());
				}else {
					return Double.compare(
							Double.valueOf(s2.getDosPuntosMetidos()), Double.valueOf(s1.getDosPuntosMetidos()));
				}
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getDosInTentadosPartido(String radioDosFga){
		
		//ArrayList<EstadisticasMaximos> lista=devolverLista();
		
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				if("media".equals(radioDosFga)) {
					return Double.compare(s2.getDosPuntosIntentadosPartido(), s1.getDosPuntosIntentadosPartido());
				}else {
					return Double.compare(
							Double.valueOf(s2.getDosPuntosIntentados()), Double.valueOf(s1.getDosPuntosIntentados()));
				}
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getDosPorcentajePartido(int porcentajeDos){
		
		ArrayList<EstadisticasMaximos> lista=devolverLista();

		Iterator<EstadisticasMaximos> iterador = lista.iterator();
		while (iterador.hasNext()) {
			EstadisticasMaximos objeto = iterador.next();

		    if (Integer.parseInt(objeto.getDosPuntosIntentados())<porcentajeDos) {

		        iterador.remove();
		    }
		}
		
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getDosPuntosPorcentaje(), s1.getDosPuntosPorcentaje());
			}
		};
		Collections.sort(lista, comparator);
		
		return lista;
	}
	
///////////////////////////TRIPLES
	
	public ArrayList<EstadisticasMaximos> getTriplesMetidosPartido(String radioTripleFg){
		
		//ArrayList<EstadisticasMaximos> lista=devolverLista();
		
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				if("media".equals(radioTripleFg)) {
					return Double.compare(s2.getTriplesAnotadosPartido(), s1.getTriplesAnotadosPartido());
				}else {
					return Double.compare(
							Double.valueOf(s2.getTriplesMetidos()), Double.valueOf(s1.getTriplesMetidos()));
				}
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getTriplesIntentadosPartido(String radioTripleFga){
		
		//ArrayList<EstadisticasMaximos> lista=devolverLista();
		
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				if("media".equals(radioTripleFga)) {
					return Double.compare(s2.getTriplesIntentadosPartido(), s1.getTriplesIntentadosPartido());
				}else {
					return Double.compare(
							Double.valueOf(s2.getTriplesIntentados()), Double.valueOf(s1.getTriplesIntentados()));
				}
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getTriplesPorcentajePArtido(int porcentajeTriple){
		
		ArrayList<EstadisticasMaximos> lista=devolverLista();
		
		Iterator<EstadisticasMaximos> iterador = lista.iterator();
		while (iterador.hasNext()) {
			EstadisticasMaximos objeto = iterador.next();

		    if (Integer.parseInt(objeto.getTriplesIntentados())<porcentajeTriple) {

		        iterador.remove();
		    }
		}
		
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getTriplesPorcentaje(), s1.getTriplesPorcentaje());
			}
		};
		Collections.sort(lista, comparator);
		
		return lista;
	}
	
///////////////////////// TIROS LIBRES
	
	public ArrayList<EstadisticasMaximos> getTirosLibresMetidosPartido(String radioLibreFg){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				if("media".equals(radioLibreFg)) {
					return Double.compare(s2.getTirosLibresAnotadosPartido(), s1.getTirosLibresAnotadosPartido());
				}else {
					return Double.compare(
							Double.valueOf(s2.getTirosLibresMetidos()), Double.valueOf(s1.getTirosLibresMetidos()));
				}
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getTirosLibresIntentadosPartido(String radioLibreFga){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				if("media".equals(radioLibreFga)) {
					return Double.compare(s2.getTirosLibresIntentadosPartido(), s1.getTirosLibresIntentadosPartido());
				}else {
					return Double.compare(
							Double.valueOf(s2.getTirosLibresIntentados()), Double.valueOf(s1.getTirosLibresIntentados()));
				}
			}
		};
		Collections.sort(listaJugadores, comparator);
		
		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getTirosLibresPorcentajePartido(int porcentajeLibre){
		
		ArrayList<EstadisticasMaximos> lista=devolverLista();
		
		Iterator<EstadisticasMaximos> iterador = lista.iterator();
		while (iterador.hasNext()) {
			EstadisticasMaximos objeto = iterador.next();

		    if (Integer.parseInt(objeto.getTirosLibresIntentados())<porcentajeLibre) {

		        iterador.remove();
		    }
		}
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getTirosLibresPorcentaje(), s1.getTirosLibresPorcentaje());
			}
		};
		Collections.sort(lista, comparator);
		
		return lista;
	}
	
///////////////////////// PUNTOS PARTIDO
	
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
	
///////////////////////// REBOTES
	public ArrayList<EstadisticasMaximos> getRebotesPartido(String radioRebote){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				if("media".equals(radioRebote)) {
					return Double.compare(s2.getRebotesPartido(), s1.getRebotesPartido());
				}else {
					return Double.compare(
							Double.valueOf(s2.getTotalRebotes()), Double.valueOf(s1.getTotalRebotes()));
				}
			}
		};
		Collections.sort(listaJugadores, comparator);

		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getRebotesDefensivosPartido(String radioReboteDefensivo){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				if("media".equals(radioReboteDefensivo)) {
					return Double.compare(s2.getReboteDefensivoPartido(), s1.getReboteDefensivoPartido());
				}else {
					return Double.compare(
							Double.valueOf(s2.getReboteDefensivo()), Double.valueOf(s1.getReboteDefensivo()));
				}
				
			}
		};
		Collections.sort(listaJugadores, comparator);

		return listaJugadores;
	}
	
	public ArrayList<EstadisticasMaximos> getRebotesOfensivosPartido(String radioReboteOfensivo){
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				if("media".equals(radioReboteOfensivo)) {
					return Double.compare(s2.getReboteOfensivoPartido(), s1.getReboteOfensivoPartido());
				}else {
					return Double.compare(
							Double.valueOf(s2.getReboteOfensivo()), Double.valueOf(s1.getReboteOfensivo()));
				}
			}
		};
		Collections.sort(listaJugadores, comparator);

		return listaJugadores;
	}
	
///////////////////////// ASISTENCIAS
	
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
	
///////////////////////// ROBOS
	
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
	
///////////////////////// TAPONES
	
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
	
///////////////////////// PERDIDAS
	
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
	
///////////////////////// PERSONALES
	
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
	
///////////////////////// MINUTOS

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
