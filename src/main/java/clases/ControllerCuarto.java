/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author hatashi
 */
public class ControllerCuarto {
    
    private String cuarto="";
    private int tanteoLocal=0;
    private int tanteoVisitante=0;
    
    public ControllerCuarto(){
    }
    
    public ControllerCuarto(String cuarto, int tanteoLocal, int tanteoVisitante){
        this.cuarto=cuarto;
        this.tanteoLocal=tanteoLocal;
        this.tanteoVisitante=tanteoVisitante;
    }

    public String getCuarto() {
        return cuarto;
    }

    public void setCuarto(String cuarto) {
        this.cuarto = cuarto;
    }

    public int getTanteoLocal() {
        return tanteoLocal;
    }

    public void setTanteoLocal(int tanteoLocal) {
        this.tanteoLocal = tanteoLocal;
    }

    public int getTanteoVisitante() {
        return tanteoVisitante;
    }

    public void setTanteoVisitante(int tanteoVisitante) {
        this.tanteoVisitante = tanteoVisitante;
    }
    
    
}
