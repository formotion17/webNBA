/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import lombok.Data;

/**
 *
 * @author hatashi
 */

@Data
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

    public Jugador(String apellido, String id, String nombre) {
        this.nombre = nombre;
        this.codigo = id;
        this.apellido = apellido;
    }

    public String recogerFoto() {
        return "https://www.basketball-reference.com/req/202106291/images/headshots/" + codigo + ".jpg";
    }
}
