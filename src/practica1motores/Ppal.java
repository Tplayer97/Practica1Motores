/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1motores;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class Ppal extends javax.swing.JFrame {

    File[] archivosC;
    File[] archivosQ;
    static HttpSolrClient solr;

    public Ppal() {
        initComponents();
        solr = new HttpSolrClient.Builder("http://localhost:8983/solr/pruebas").build();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Cargar Corpus");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Realizar Queries");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Apagar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Ejecuta Queries");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(45, 45, 45)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser filec = new JFileChooser();
        filec.setCurrentDirectory(new File("C:\\"));
        filec.setMultiSelectionEnabled(true);
        filec.showOpenDialog(this);
        archivosC = filec.getSelectedFiles();
        for (int i = 0; i < archivosC.length; i++) {
            try {
                cargar(archivosC[i].getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SolrServerException ex) {
                Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fileq = new JFileChooser();
        fileq.setCurrentDirectory(new File("C:\\"));
        fileq.setMultiSelectionEnabled(true);
        fileq.showOpenDialog(this);
        archivosQ = fileq.getSelectedFiles();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String[] cmd = {"C:\\Users\\jose_\\Documents\\solr-8.7.0\\bin\\solr.cmd", "stop", "-all"};
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe);
    }//GEN-LAST:event_jButton3ActionPerformed
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String query;
        String linea;
        String Documento = "";
        String q;
        String Salida;
        String[] qt;

        ArrayList<String> sal;
        QueryResponse rsp;
        for (int k = 0; k < archivosQ.length; k++) {
            try {
                FileWriter fichero;
                fichero = new FileWriter("./trec_top_file" + k + ".txt");
                PrintWriter pw = new PrintWriter(fichero);
                SolrDocumentList docs;
                SolrQuery Query = new SolrQuery();
                LectorQ consulta = new LectorQ();
                consulta.leer(archivosQ[k].getAbsolutePath());
                sal = consulta.getSalida();
                for (int i = 0; i < sal.size() - 1; i++) {
                    query = "";
                    linea = "";
                    Salida = "Query " + i + "\n";
                    q = sal.get(i);
                    qt = q.split(" ");
                    for (int j = 0; j < 5; j++) {
                        query = query + " " + qt[j];
                    }
                    Query.setQuery("Cuerpo2:" + query);

                    Query.setFields("Titulo2", "score", "Document");

                    rsp = solr.query(Query);

                    docs = rsp.getResults();

                    for (int K = 0; K < docs.size(); ++K) {

                        Salida = Salida + docs.get(K).toString() + "\n";

                        linea = (i + 1) + " Q0 " + docs.get(K).getFieldValue("Document").toString().substring(1, docs.get(K).getFieldValue("Document").toString().length() - 1) + " " + (K + 1) + " " + docs.get(K).getFieldValue("score") + " " + "ETSI";
                        pw.println(linea);
                    }

                    jTextArea1.append(Salida);
                }
                fichero.close();
            } catch (IOException ex) {
                Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SolrServerException ex) {
                Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public static void main(String args[]) {

        try {
            String[] cmd = {"C:\\Users\\jose_\\Documents\\solr-8.7.0\\bin\\solr.cmd", "start"}; //Comando de apagado en windows
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ppal().setVisible(true);
            }
        });

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
