/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author hatashi
 */
public class Utilidades {
    
    public static List<SelectItem> devolverMenuAtributos(){
        List<SelectItem> lista = new ArrayList<SelectItem>();
            lista.add(new SelectItem("PTS","Puntos"));
            lista.add(new SelectItem("TRB","Rebotes"));
            lista.add(new SelectItem("AST","Asistencias"));
            lista.add(new SelectItem("STL","Robos"));
            lista.add(new SelectItem("BLK","Tapones"));
            lista.add(new SelectItem("TOV","Perdidas"));
            lista.add(new SelectItem("PF","Faltas Personales"));
            lista.add(new SelectItem("FG","FG"));
            lista.add(new SelectItem("FGA","FGA"));
            lista.add(new SelectItem("FG%","%FG"));
            lista.add(new SelectItem("3P","3PG"));
            lista.add(new SelectItem("3PA","3PGA"));
            lista.add(new SelectItem("3P%","%3PG"));
            lista.add(new SelectItem("FT","FT"));
            lista.add(new SelectItem("FTA","FTA"));
            lista.add(new SelectItem("FT%","%FT"));
            lista.add(new SelectItem("segundos","Minutos"));
            lista.add(new SelectItem("DRB","Rebote Defensivo"));
            lista.add(new SelectItem("ORB","Rebote Ofensivo"));
        return lista;
    }
    
    public static HashMap devolverHashMapAtributos(){
        HashMap<String,String> map = new HashMap<String,String>();
            map.put("PTS","puntos");
            map.put("TRB","totalRebotes");
            map.put("AST","asistencias");
            map.put("STL","robos");
            map.put("BLK","tapones");
            map.put("TOV","perdidas");
            map.put("PF","faltasPersonales");
            map.put("FG","tirosCampoMetidos");
            map.put("FGA","tirosCampoIntentados");
            map.put("FG%","tirosCampoPorcentaje");
            map.put("3P","triplesMetidos");
            map.put("3PA","triplesIntentados");
            map.put("3P%","triplesPorcentaje");
            map.put("FT","tirosLibresMetidos");
            map.put("FTA","tirosLibresIntentados");
            map.put("FT%","tirosLibresPorcentaje");
            map.put("MP","segundos");
            map.put("DRB","reboteDefensivo");
            map.put("ORB","reboteOfensivo");
            
            map.put("partido","boxscore");
            map.put("cuarto1","cuarto1");
            map.put("cuarto2","cuarto2");
            map.put("cuarto3","cuarto3");
            map.put("cuarto4","cuarto4");
            map.put("over1","over1");
            map.put("over2","over2");
            map.put("over3","over3");
            map.put("over4","over4");
        return map;
    }
    
    public static List<SelectItem> devolverMenuTiempoPartido(){
        List<SelectItem> lista = new ArrayList<SelectItem>();
            lista.add(new SelectItem("partido","Partido"));
            lista.add(new SelectItem("cuarto1","1º Cuarto"));
            lista.add(new SelectItem("cuarto2","2º Cuarto"));
            lista.add(new SelectItem("cuarto3","3º Cuarto"));
            lista.add(new SelectItem("cuarto4","4º Cuarto"));
            lista.add(new SelectItem("over1","1º Prorroga"));
            lista.add(new SelectItem("over2","2º Prorroga"));
            lista.add(new SelectItem("over3","3º Prorroga"));
            lista.add(new SelectItem("over4","4º Prorroga"));
        return lista;
    }
    
    public static List<SelectItem> devolverMenuUbicacion(){
        List<SelectItem> lista = new ArrayList<SelectItem>();
            lista.add(new SelectItem("indiferente","Indiferente"));
            lista.add(new SelectItem("local","Local"));
            lista.add(new SelectItem("visitante","Visitante"));
        return lista;
    }
    
    public static List<SelectItem> devolverMenuResultado(){
        List<SelectItem> lista = new ArrayList<SelectItem>();
            lista.add(new SelectItem("","Indiferente"));
            lista.add(new SelectItem("win","Victoria"));
            lista.add(new SelectItem("lose","Derrota"));
        return lista;
    }
    
    public static List<SelectItem> devolverMenuCuando(){
        List<SelectItem> lista = new ArrayList<SelectItem>();
            lista.add(new SelectItem("regular","Temporada Regular"));
            lista.add(new SelectItem("playoff","PlayOff"));
            lista.add(new SelectItem("temporada","Temporada"));
        return lista;
    }
    
     public static List<SelectItem> devolverTemporadas(){
        List<SelectItem> lista = new ArrayList<SelectItem>();
            lista.add(new SelectItem("season20002001","2000/2001"));
            lista.add(new SelectItem("season20012002","2001/2002"));
            lista.add(new SelectItem("season20022003","2002/2003"));
            lista.add(new SelectItem("season20032004","2003/2004"));
            lista.add(new SelectItem("season20042005","2004/2005"));
            lista.add(new SelectItem("season20052006","2005/2006"));
            lista.add(new SelectItem("season20062007","2006/2007"));
            lista.add(new SelectItem("season20072008","2007/2008"));
            lista.add(new SelectItem("season20082009","2008/2009"));
            lista.add(new SelectItem("season20092010","2009/2010"));
            lista.add(new SelectItem("season20102011","2010/2011"));
            lista.add(new SelectItem("season20112012","2011/2012"));
            lista.add(new SelectItem("season20122013","2012/2013"));
            lista.add(new SelectItem("season20132014","2013/2014"));
            lista.add(new SelectItem("season20142015","2014/2015"));
            lista.add(new SelectItem("season20152016","2015/2016"));
            lista.add(new SelectItem("season20162017","2016/2017"));
            lista.add(new SelectItem("season20172018","2017/2018"));
            lista.add(new SelectItem("season20182019","2018/2019"));
            lista.add(new SelectItem("season20192020","2019/2020"));
            lista.add(new SelectItem("season20202021","2020/2021"));
        return lista;
    }
    
}
