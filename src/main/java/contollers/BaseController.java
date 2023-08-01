/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contollers;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.mongodb.MongoClient;

import Mapper.Atributos;

/**
 *
 * @author hatashi
 */
@ManagedBean(name ="baseController")
@RequestScoped
public class BaseController extends Atributos implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2150948195156093408L;
	
	
	protected static String baseDatos="NBA";
	protected static String host="localhost";
	protected static Integer puertoHost=27017;
	protected String collectionPartidos="partidos";
	protected String collectionTotales="totales";
	
    protected MongoClient mongo = null;
	
	
    protected void iniciarSesion(String host, int port) {
    	mongo = new MongoClient(host,port);
    }
	public MongoClient getMongo() {
		return mongo;
	}
	
	public void setMongo(MongoClient mongo) {
		this.mongo = mongo;
	}
    
	protected String getDecimalSeparatorFromLocale(Locale locale) {
        // Obtén el separador decimal del Locale
        return String.valueOf(".");
    }

    protected void setDecimalSeparatorForJSF(String decimalSeparator) {
        // Configura el separador decimal para JSF
        FacesContext.getCurrentInstance().getApplication().addComponent(
                "javax.faces.DECIMAL_SEPARATOR", decimalSeparator
        );
    }
}
