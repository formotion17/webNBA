/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author hatashi
 */
public class Jugador {

    private String nombre = "";
    private String codigo = "";
    private String apellido = "";
    private boolean ver = true;

    public Jugador() {
    }

    public Jugador(String nombre, String id, String apellido, boolean ver) {
        this.nombre = nombre;
        this.codigo = id;
        this.apellido = apellido;
        this.ver = ver;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public Jugador(String apellido, String id, String nombre) {
        this.nombre = nombre;
        this.codigo = id;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String recogerFoto() {
        return "https://www.basketball-reference.com/req/202106291/images/headshots/" + codigo + ".jpg";
    }
}
