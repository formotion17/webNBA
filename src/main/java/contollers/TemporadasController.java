package contollers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import util.Utilidades;

@ViewScoped
@ManagedBean(name ="temporadas")
@Data
@EqualsAndHashCode(callSuper=false)
public class TemporadasController {

    private String partidosTemporada="";
    private List<SelectItem> selectMenuTemporadas;
    
    
    
    
    public List<SelectItem> getSelectMenuTemporadas() {
        if(selectMenuTemporadas == null){
            selectMenuTemporadas = Utilidades.devolverTemporadas();
        }
        return selectMenuTemporadas;
    }
}
