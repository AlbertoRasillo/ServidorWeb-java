
package servidorweb;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

public class Ventana extends javax.swing.JFrame {

   ServidorWeb sw=null;
   TareasSegundoPlano Tarea= null;
    public Ventana() {
        initComponents();
        redireccionarSystemStreams();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Estado = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        on = new javax.swing.JRadioButton();
        of = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        TextRespuestas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EnviarRespuestas = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        TextMIME = new javax.swing.JTextField();
        ButtonMIME = new javax.swing.JButton();
        TextContent = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextClientes = new javax.swing.JTextArea();
        ButtonBanear = new javax.swing.JButton();
        ButtonMostrarCliente = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TextPaneRespuestas = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Estado");

        Estado.add(on);
        on.setText("ON");
        on.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onMouseClicked(evt);
            }
        });
        on.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onActionPerformed(evt);
            }
        });

        Estado.add(of);
        of.setSelected(true);
        of.setText("OF");
        of.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ofMouseClicked(evt);
            }
        });
        of.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ofActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(of)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(on)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(of)
                    .addComponent(on))
                .addContainerGap())
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Nº de respuestas:");

        jLabel3.setText("Log:");

        EnviarRespuestas.setText("Aceptar");
        EnviarRespuestas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EnviarRespuestasMouseClicked(evt);
            }
        });
        EnviarRespuestas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarRespuestasActionPerformed(evt);
            }
        });

        jLabel4.setText("Añadir tipo MIME:");

        TextMIME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextMIMEActionPerformed(evt);
            }
        });

        ButtonMIME.setText("Aceptar");
        ButtonMIME.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonMIMEMouseClicked(evt);
            }
        });

        TextClientes.setColumns(20);
        TextClientes.setRows(5);
        jScrollPane2.setViewportView(TextClientes);

        ButtonBanear.setText("Banear Cliente");
        ButtonBanear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonBanearMouseClicked(evt);
            }
        });
        ButtonBanear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBanearActionPerformed(evt);
            }
        });

        ButtonMostrarCliente.setText("Mostrar Clientes");
        ButtonMostrarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonMostrarClienteMouseClicked(evt);
            }
        });
        ButtonMostrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonMostrarClienteActionPerformed(evt);
            }
        });

        TextPaneRespuestas.setText("50");
        jScrollPane3.setViewportView(TextPaneRespuestas);

        jLabel5.setText("Cambiar Nº respuestas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 544, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(TextRespuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(TextMIME, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TextContent, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ButtonMIME, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EnviarRespuestas)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ButtonMostrarCliente)
                            .addGap(18, 18, 18)
                            .addComponent(ButtonBanear))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonBanear)
                            .addComponent(ButtonMostrarCliente))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TextRespuestas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EnviarRespuestas)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TextMIME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TextContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ButtonMIME)
                                    .addComponent(jLabel4))))))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_onActionPerformed

    private void ofMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ofMouseClicked
        Cliente.ArchivarClientes(Cliente.getClientes());
        Tarea.suspend();
        sw.suspend();
        System.out.println("server apagado");
        
    }//GEN-LAST:event_ofMouseClicked

    private void onMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onMouseClicked
        if (sw == null){
            try {
                this.sw = new ServidorWeb();
                this.Tarea= new TareasSegundoPlano();
                System.out.println("server arrancado");
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);          
            }
        }
        else{
            sw.resume();
            Tarea.resume();
            System.out.println("server arrancado");
        }
    }//GEN-LAST:event_onMouseClicked

    private void ofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ofActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ofActionPerformed

    private void EnviarRespuestasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarRespuestasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EnviarRespuestasActionPerformed

    private void EnviarRespuestasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnviarRespuestasMouseClicked
        try{
            int peticionesActivas = 0;
            peticionesActivas =Integer.parseInt(TextRespuestas.getText());
            TextRespuestas.setText("");
            ExecutorService exec= ServidorWeb.getExec();
            exec = Executors.newFixedThreadPool(peticionesActivas);
            TextPaneRespuestas.setText(Integer.toString(peticionesActivas));
        }catch(NumberFormatException e){}
    }//GEN-LAST:event_EnviarRespuestasMouseClicked

    private void ButtonMIMEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonMIMEMouseClicked
        String extension =TextMIME.getText();
        TextMIME.setText("");
        System.out.println(extension);
        String content =TextContent.getText();
        TextContent.setText("");
        System.out.println(content);
        TipoMIME mime = new TipoMIME(extension, content);
        TipoMIME.getTiposMime().add(mime);
        TipoMIME.ArchivartiposMime(TipoMIME.getTiposMime());
    }//GEN-LAST:event_ButtonMIMEMouseClicked

    private void TextMIMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextMIMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextMIMEActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int cerrar = JOptionPane.showConfirmDialog(null, "Seguro que quiere salir?");
        if (cerrar==0){
            TipoMIME.ArchivartiposMime(TipoMIME.getTiposMime());
            Cliente.ArchivarClientes(Cliente.getClientes());
            //DirectorioVirtual.(DirectorioVirtual.Directorios); Crear archivar Directorios
            System.exit(0);
        }
         
    }//GEN-LAST:event_formWindowClosing

    private void ButtonBanearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBanearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonBanearActionPerformed

    private void ButtonMostrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonMostrarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonMostrarClienteActionPerformed

    private void ButtonBanearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonBanearMouseClicked
        JFrame frame = new JFrame("Banear");
        String coockie = JOptionPane.showInputDialog(frame, "Introduce la coockie a banear");
        if(Cliente.ExisteCliente(coockie, Cliente.getClientes())){
            //Cliente.getClientesBaneados().add(Cliente.StringToCliente(coockie));
            Cliente cli =Cliente.StringToCliente(coockie);
            Cliente.banear(cli);
            
        }
        else {
            System.out.println("El cliente no existe o está baneado");
        }
    }//GEN-LAST:event_ButtonBanearMouseClicked

    private void ButtonMostrarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonMostrarClienteMouseClicked
       for (Cliente cli : Cliente.getClientes()){
           TextClientes.setText(cli.cliente2CSV());
       }
    }//GEN-LAST:event_ButtonMostrarClienteMouseClicked

    private void actualizarTextArea(final String texto) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              jTextArea1.append(texto);
            }
        });
    }

    private void redireccionarSystemStreams() {
    OutputStream out = new OutputStream() {
    @Override
    public void write(int b) throws IOException {
      actualizarTextArea(String.valueOf((char) b));
    }
 
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
      actualizarTextArea(new String(b, off, len));
    }
 
    @Override
    public void write(byte[] b) throws IOException {
      write(b, 0, b.length);
      }
    };
 
    System.setOut(new PrintStream(out, true));
    System.setErr(new PrintStream(out, true));
}
    
    
    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonBanear;
    private javax.swing.JButton ButtonMIME;
    private javax.swing.JButton ButtonMostrarCliente;
    private javax.swing.JButton EnviarRespuestas;
    private javax.swing.ButtonGroup Estado;
    private javax.swing.JTextArea TextClientes;
    private javax.swing.JTextField TextContent;
    private javax.swing.JTextField TextMIME;
    private javax.swing.JTextPane TextPaneRespuestas;
    private javax.swing.JTextField TextRespuestas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTextArea jTextArea1;
    private javax.swing.JRadioButton of;
    private javax.swing.JRadioButton on;
    // End of variables declaration//GEN-END:variables
}
