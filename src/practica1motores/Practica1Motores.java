
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


public class Practica1Motores {

    static HttpSolrClient solr;

    public static void main(String[] args) throws IOException, SolrServerException {





       
        QueryResponse rsp;




        solr = new HttpSolrClient.Builder("http://localhost:8983/solr/pruebas").build();
        //cargar("LISA0.001");
                FileWriter fichero = new FileWriter("./trec_top_file.txt");
            PrintWriter pw = new PrintWriter(fichero);
                SolrDocumentList docs;
                SolrQuery Query = new SolrQuery();
                LectorQ consulta = new LectorQ();
                String linea;  
                String q;
                String[] qt;
                String query;
                ArrayList<String> sal;
        consulta.leer("C:\\Users\\jose_\\Documents\\LISA\\LISA.QUE");
        sal = consulta.getSalida();
        for (int i = 0; i < sal.size() - 1; i++) {
            query = "";
            linea = "";
            q = sal.get(i);
            qt = q.split(" ");
            for (int j = 0; j < 5; j++) {
                query = query + " " + qt[j];
            }
            Query.setQuery("Cuerpo2:" + query);

            Query.setFields("Titulo2", "score");

            rsp = solr.query(Query);
            docs = rsp.getResults();
            System.out.println("query " + i );
            System.out.println(docs.size());
            for (int K = 0; K < docs.size(); ++K) {
                System.out.println(docs.get(K));
                linea = (i + 1) + " Q0 " + (K + 1) + " " + docs.get(K).getFieldValue("score") + " " + "ETSI";
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
