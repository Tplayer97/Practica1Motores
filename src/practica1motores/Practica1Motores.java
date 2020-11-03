/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1motores;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

/**
 *
 * @author Tplayer
 */
public class Practica1Motores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SolrServerException {
        
        HttpSolrClient solr = new HttpSolrClient.Builder("http://localhost:8983/solr/micoleccion").build();
        SolrInputDocument docu = new SolrInputDocument();
        Lector leer = new Lector();
        
        leer.leer("LISA0.001");
        ArrayList<SolrInputDocument> doc = new ArrayList<SolrInputDocument>();
        doc = leer.getdoc();
        for (int i = 0; i < doc.size(); i++) {
            docu = doc.get(i);
            solr.add(docu);
        }
            solr.commit();
    }
    
}
