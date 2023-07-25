package modelo;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import clases.ControllerEstadisticaNormal;
import clases.ControllerTiros;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TablaTirosJugador {
	
	private String titulo="";
	private String tituloTabla="";
	private ArrayList<ControllerTiros> listaTiros = new ArrayList<>();
	private ArrayList<ControllerEstadisticaNormal> listadiferentesStats = new ArrayList<>();
	
	private DonutChartModel donutModel1 = new DonutChartModel();
	private DonutChartModel donutModel2 = new DonutChartModel();
	private DonutChartModel donutModel3 = new DonutChartModel();
	
	private String donutModelPorcentaje1="";
	private String donutModelPorcentaje2="";
	private String donutModelPorcentaje3="";
	
	public void rellenarGraficaDonutGenerico(DonutChartModel grafica,int dentro, int fuera,String fueraLabel, String dentroLabel) {

    	ChartData data = new ChartData();

    	DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(fuera);
        values.add(dentro);
        dataSet.setData(values);
        
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(148, 152, 147)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add(fueraLabel);
        labels.add(dentroLabel);
        data.setLabels(labels);
                 
        grafica.setData(data);
    	
    }
	
    private String actualizarPorcentajeTirosDonut(int dentro, int fuera) {
    	String porcentaje = new java.text.DecimalFormat("0.##").format(Double.valueOf((double) dentro/(dentro+fuera)));
    	
    	if(porcentaje.equals("NaN")) {
    		return "";
    	}
    	if(fuera==0) {
    		return "0";
    	}
    	return (new java.text.DecimalFormat("0.##").format(Double.parseDouble(porcentaje.replace(",", "."))*100))+"%";
    }
    
    public void setPorcentaje1(int dentro, int fuera) {
    	this.donutModelPorcentaje1 = actualizarPorcentajeTirosDonut(dentro, fuera);
    }
    
    public void setPorcentaje2(int dentro, int fuera) {
    	this.donutModelPorcentaje2 = actualizarPorcentajeTirosDonut(dentro, fuera);
    }
    
    public void setPorcentaje3(int dentro, int fuera) {
    	this.donutModelPorcentaje3 = actualizarPorcentajeTirosDonut(dentro, fuera);
    }
    
}
