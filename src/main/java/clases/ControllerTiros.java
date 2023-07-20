package clases;

import lombok.Data;

@Data
public class ControllerTiros {

    private String cuarto;
    private boolean dentro=false;
    private Integer distancia = 0;
    private Integer posicionLeft = 0;
    private Integer posicionTop = 0;
    private String situacionAntes = "";
	private String situacionDespues = "";
    private String tanteo="";
    private String tanteoEquipo="";
    private String tanteoRival="";
    private Integer tiempoRestante = 0;
    private String tipo = "";
    private String clase="";
    
    public ControllerTiros(){
        
    }
    public ControllerTiros(Integer posicionTop, Integer posicionLeft){
        this.posicionTop=posicionTop;
        this.posicionLeft=posicionLeft;
    }
    
    public ControllerTiros(Integer posicionTop, Integer posicionLeft,boolean dentro) {
        this.posicionTop=posicionTop;
        this.posicionLeft=posicionLeft;
        this.dentro=dentro;
    }
    
    public ControllerTiros(Integer posicionTop, Integer posicionLeft,boolean dentro,String cuarto) {
        this.posicionTop=posicionTop;
        this.posicionLeft=posicionLeft;
        this.dentro=dentro;
        this.cuarto=cuarto;
    }

    public void setDentro(boolean dentro) {
        this.dentro = dentro;
        if(dentro){
            clase = "make";
        }else{
            clase ="miss";
        }
    }
}
