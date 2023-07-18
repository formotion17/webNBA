/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contollers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import clases.ControllerPartido;
import lombok.Data;
import util.MapJavaMongo;

@ViewScoped
@RequestScoped
@ManagedBean(name ="index")
@Data
public class IndexController extends BaseController{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1666879989209306824L;

    private ArrayList<ControllerPartido> listaPartidos = new ArrayList<ControllerPartido>();
    private Date fechaPartidos = Calendar.getInstance().getTime();
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private String dia;
    private String mes;
    private String year;
    private Date diaAnterior= Calendar.getInstance().getTime();
    private Date diaPosterior= Calendar.getInstance().getTime();
    private Date diaMaximo = Calendar.getInstance().getTime();
    private String urlFoto="https://www.basketball-reference.com/req/202106291/images/headshots/";
    
    private String jugadorLocalElegido="";
    private String jugadorVisitanteElegido="";
    private String cuartoLocalElegido="";
    private String cuartoVisitanteElegido="";
    
    
    @PostConstruct
    public void init(){
        System.out.println("Iniciamos aplicación NBA INFO STATS");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK,0);
        fechaPartidos = calendar.getTime();
        restar1dia(calendar);
        sumar2dia(calendar);
        iniciarSesion(HOST, PUERTO_HOST);
        rellenarPartidos();
        System.out.println(listaPartidos.size());
    }
    
    private void sumar1dia(Calendar calendar){
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        diaPosterior = calendar.getTime();
    }
    
    private void sumar2dia(Calendar calendar){
        calendar.add(Calendar.DAY_OF_WEEK, 2);
        diaPosterior = calendar.getTime();
    }
    
    private void restar1dia(Calendar calendar){
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        diaAnterior = calendar.getTime();
    }
    
    private void restar2dias(Calendar calendar){
        calendar.add(Calendar.DAY_OF_WEEK, -2);
        diaAnterior = calendar.getTime();
    }
    
    public void irDiaDespues(){
        iniciarSesion(HOST,PUERTO_HOST);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaPartidos);
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        fechaPartidos = calendar.getTime();
        sumar1dia(calendar);
        restar2dias(calendar);
        rellenarPartidos();
    }
    
    public void irDiaAntes(){
        iniciarSesion(HOST, PUERTO_HOST);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaPartidos);
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        fechaPartidos = calendar.getTime();
        sumar1dia(calendar);
        restar2dias(calendar);
        rellenarPartidos();
    }
    
    public void rellenarPartidos(){
    	
    	listaPartidos.clear();
                
        if(mongo!=null) {
            
            setDiaMesYear();

            MongoDatabase db = mongo.getDatabase(BASE_DATOS);

            // Select the collection
            MongoCollection<Document> collection = db.getCollection("partidos");

            Document findDocument = new Document("dia",devolverFechaSinCero(Integer.toString(Integer.parseInt(dia))));
            findDocument.put("mes", mes);
            findDocument.put("year", year);


            MongoCursor<Document> lista = collection.find(findDocument).iterator();

            while(lista.hasNext()) {
                    listaPartidos.add(MapJavaMongo.rellenarPartido((Document)lista.next()));
            }
        }
    }
    
    private void setDiaMesYear(){
        String[]parteFecha = formatter.format(fechaPartidos).split("/");
        dia=devolverFechaSinCero(parteFecha[0]);
        mes=devolverFechaSinCero(parteFecha[1]);
        year=parteFecha[2];
    }
    
    private String devolverFechaSinCero(String obj){
        if(obj.equals("0")){
            obj="01";
        }
        if("0".equals(obj.substring(0,1))){
            return obj.substring(1,2);
        }
        return obj;
    }
    
    public String devolverDiaAnterior(){
        return formatter.format(diaAnterior);
    }
    
    public String devolverDiaSiguiente(){
        return formatter.format(diaPosterior);
    }
    
    public void buscarPartidos(){

        if(mongo!=null) {
            
            setDiaMesYear();
            listaPartidos.clear();
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaPartidos);
            sumar1dia(calendar);
            restar2dias(calendar);

            MongoDatabase db = mongo.getDatabase(BASE_DATOS);

            // Select the collection
            MongoCollection<Document> collection = db.getCollection("partidos");

            Document findDocument = new Document("dia",dia);
            findDocument.put("mes", mes);
            findDocument.put("year", year);
            System.out.println("Buscando el día: :"+dia+" - "+mes+" - "+year);

            MongoCursor<Document> lista = collection.find(findDocument).iterator();

            while(lista.hasNext()) {
                    listaPartidos.add(MapJavaMongo.rellenarPartido((Document)lista.next()));
            }
        }
    }
    
	public String formatValue(int value) {
		System.out.println(value);
	    int formattedValue = Math.abs(value);
	    return String.valueOf(formattedValue);
	}
    
    public String devolverFechaElegida(){
        return formatter.format(fechaPartidos);
    }
}
