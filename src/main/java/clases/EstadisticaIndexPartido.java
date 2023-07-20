package clases;

import lombok.Data;

@Data
public class EstadisticaIndexPartido {
	
	 private String atributo;
     private String visitante;
     private String local;

     public EstadisticaIndexPartido(String atributo, String visitante, String local) {
         this.atributo = atributo;
         this.visitante = visitante;
         this.local = local;
     }

}
