/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1motores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

/**
 *
 * @author Tplayer
 */
public class Practica1Motores {

    static HttpSolrClient solr;

    public static void main(String[] args) throws IOException, SolrServerException {
        FileWriter fichero = new FileWriter("./trec_top_file.txt");
        PrintWriter pw = new PrintWriter(fichero);

        String linea;
        String q;
        String[] qt;
        String query;
        SolrQuery Query = new SolrQuery();
        QueryResponse rsp;
        SolrDocumentList docs;
        ArrayList<String> sal;
        LectorQ consulta = new LectorQ();

        solr = new HttpSolrClient.Builder("http://localhost:8983/solr/micoleccion").build();
        //cargar("LISA0.001"); 
        consulta.leer();
        sal = consulta.getSalida();
        for (int i = 0; i < sal.size() - 1; i++) {
            query = "";
            linea = "";
            q = sal.get(i);
            qt = q.split(" ");
            for (int j = 0; j < 5; j++) {
                query = query + " " + qt[j];
            }
            Query.setQuery("Cuerpo:" + query);

            Query.setFields("Titulo", "score");

            rsp = solr.query(Query);
            docs = rsp.getResults();
            System.out.println(docs.size());
            for (int K = 0; K < docs.size(); ++K) {
                System.out.println(docs.get(K));
                linea = i + 1 + " Q0 " + K + 1 + " " + docs.get(K).getFieldValue("score") + " " + "ETSI";
                pw.println(linea);
            }

        }
        fichero.close();
    }

    static public void cargar(String n) throws IOException, SolrServerException {
        SolrInputDocument docu = new SolrInputDocument();
        Lector leer = new Lector();
        leer.leer(n);
        ArrayList<SolrInputDocument> doc = new ArrayList<SolrInputDocument>();
        doc = leer.getdoc();
        for (int i = 0; i < doc.size(); i++) {
            docu = doc.get(i);
            solr.add(docu);
        }
        solr.commit();
    }

}
