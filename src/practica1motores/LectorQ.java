/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1motores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.solr.common.SolrInputDocument;

/**
 *
 * @author Tplayer
 */
public class LectorQ {

    ArrayList<String> Salida;

    public LectorQ() {
        Salida = new ArrayList<String>();
    }

    public void leer(String fi) throws FileNotFoundException, IOException {
        String query;
        File arc = new File(fi);
        FileReader fr = new FileReader(arc);
        BufferedReader br = new BufferedReader(fr);
        String[] lineas = new String[130];

        String linea = br.readLine();

        while (linea != null) {
            query = "";
            lineas = linea.split(" ");
            while (!lineas[lineas.length - 1].equals("#")) {
                linea = br.readLine();
                lineas = linea.split(" ");
                query = query.concat(linea);
            }
            Salida.add(query);
            linea = br.readLine();
        }
        fr.close();
        br.close();
    }

    public ArrayList<String> getSalida() {
        return Salida;
    }
}
