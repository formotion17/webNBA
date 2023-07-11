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
	static String nombreJugador;

    public static String getNombreJugador() {
        return nombreJugador;
    }

    public static void setNombreJugador(String nombreJugador) {
        BaseController.nombreJugador = nombreJugador;
    }
    
    protected static MongoClient mongo = null;
	
	
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
