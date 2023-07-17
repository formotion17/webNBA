package clases;

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
    
    public String getCuarto() {
        return cuarto;
    }

    public void setCuarto(String cuarto) {
        this.cuarto = cuarto;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public Integer getPosicionLeft() {
        return posicionLeft;
    }

    public void setPosicionLeft(Integer posicionLeft) {
        this.posicionLeft = posicionLeft;
    }

    public Integer getPosicionTop() {
        return posicionTop;
    }

    public void setPosicionTop(Integer posicionTop) {
        this.posicionTop = posicionTop;
    }

    public Integer getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(Integer tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public boolean isDentro() {
        return dentro;
    }

    public void setDentro(boolean dentro) {
        this.dentro = dentro;
        if(dentro){
            clase = "make";
        }else{
            clase ="miss";
        }
    }

    public String getTanteo() {
        return tanteo;
    }

    public void setTanteo(String tanteo) {
        this.tanteo = tanteo;
    }
    

    public String getSituacionAntes() {
		return situacionAntes;
	}
	public void setSituacionAntes(String situacionAntes) {
		this.situacionAntes = situacionAntes;
	}
	public String getSituacionDespues() {
		return situacionDespues;
	}
	public void setSituacionDespues(String situacionDespues) {
		this.situacionDespues = situacionDespues;
	}
	public String getTanteoEquipo() {
		return tanteoEquipo;
	}
	public void setTanteoEquipo(String tanteoEquipo) {
		this.tanteoEquipo = tanteoEquipo;
	}
	public String getTanteoRival() {
		return tanteoRival;
	}
	public void setTanteoRival(String tanteoRival) {
		this.tanteoRival = tanteoRival;
	}
}
