/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contollers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.mongodb.MongoClient;

/**
 *
 * @author hatashi
 */
@ManagedBean(name ="baseController")
@RequestScoped
public class BaseController implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2150948195156093408L;
	
	
	protected static String baseDatos="NBA";
	protected String host="localhost";
	protected Integer puertoHost=27017;
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
    
}
