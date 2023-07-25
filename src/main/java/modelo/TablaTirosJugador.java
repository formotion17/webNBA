package modelo;

import java.util.ArrayList;

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
	
	private String titulo;
	private String tituloTabla;
	private ArrayList<ControllerTiros> listaTiros;
	private ArrayList<ControllerEstadisticaNormal> listadiferentesStats;
	
	private DonutChartModel donutModel1;
	private DonutChartModel donutModel2;
	private DonutChartModel donutModel3;
	
	private String donutModelPorcentaje1;
	private String donutModelPorcentaje2;
	private String donutModelPorcentaje3;

}
