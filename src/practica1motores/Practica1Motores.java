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
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

/**
 *
 * @author Tplayer
 */
public class Practica1Motores {

    static HttpSolrClient solr;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SolrServerException {
        String q;
        String[] qt;
        String query;
        SolrQuery Query = new SolrQuery();
        QueryResponse rsp;
        SolrDocumentList docs;
        ArrayList<String> sal;
        Lector leer = new Lector();
        LectorQ consulta = new LectorQ();
        
        solr = new HttpSolrClient.Builder("http://localhost:8983/solr/micoleccion").build();
        //cargar("LISA0.001"); 
        consulta.leer();
        sal = consulta.getSalida();
        for (int i = 0; i < sal.size()-1; i++) {
            q = sal.get(i);
            qt = q.split(" ");
            for (int j = 0; j < 5; j++) {
            query = qt[j];
            Query.setQuery("Cuerpo:".concat(query));
            System.out.println(query);     

            rsp = solr.query(Query);
            docs = rsp.getResults();
            System.out.println(docs.size());

	for (int K = 0; K < docs.size(); ++K) {
            System.out.println(docs.get(K));
        }
            }
            /*
            Query.setQuery(query);
            System.out.println(query);
            rsp = solr.query(Query);
            docs = rsp.getResults();
            System.out.println(docs.size());
	for (int K = 0; K < docs.size(); ++K) {
            System.out.println(docs.get(K));
        }
*/
        }

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
