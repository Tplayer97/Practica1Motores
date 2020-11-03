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
 * @author usuario
 */
public class Lector {

    ArrayList<SolrInputDocument> doc;

    public Lector() {
        doc = new ArrayList<SolrInputDocument>();
    }

    public void leer(String fich) throws FileNotFoundException, IOException {
        String titulo = "";
        String cuerpo = "";
        File arc = new File(".\\".concat(fich));
        FileReader fr = new FileReader(arc);
        BufferedReader br = new BufferedReader(fr);
        String[] lineas = new String[130];

        String linea = br.readLine();

        while (linea != null) {
            lineas = linea.split(" ");
            titulo = "";
            cuerpo = "";

            SolrInputDocument docu = new SolrInputDocument();
            docu.addField("Document", lineas[lineas.length - 1]);

            linea = br.readLine();
            lineas = linea.split(" ");

            while (!linea.equals("")) {
                titulo = titulo.concat(linea);
                linea = br.readLine();
                lineas = linea.split(" ");
            }

            docu.addField("Titulo", titulo);

            linea = br.readLine();
            lineas = linea.split(" ");
            while (!linea.equals("********************************************")) {
                cuerpo = cuerpo.concat(linea);
                linea = br.readLine();
            }

            docu.addField("Cuerpo", cuerpo);

            doc.add(docu);
            linea = br.readLine();

        }

        fr.close();
        br.close();
    }

    public ArrayList<SolrInputDocument> getdoc() {
        return doc;
    }
}
