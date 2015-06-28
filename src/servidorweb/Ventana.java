
package servidorweb;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
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
        on = new javax.swing.JRadioButton();
        of = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextClientes = new javax.swing.JTextArea();
        ButtonBanear = new javax.swing.JButton();
        ButtonMostrarCliente = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        TextMIME = new javax.swing.JTextField();
        TextContent = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ButtonMIME = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        TextNombreDir = new javax.swing.JTextField();
        TextNombrePagPrin = new javax.swing.JTextField();
        TextNombrePagErr = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ButtonDirecVirt = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        TextRespuestas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        EnviarRespuestas = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TextPaneRespuestas = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Estado"));

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
                .addGap(33, 33, 33)
                .addComponent(of)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(on)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(of)
                    .addComponent(on))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setText("Log:");

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Añadir tipo MIME"));

        TextMIME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextMIMEActionPerformed(evt);
            }
        });

        jLabel7.setText("Content-Type:");

        jLabel6.setText("Extension:");

        ButtonMIME.setText("Aceptar");
        ButtonMIME.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonMIMEMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TextMIME, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextContent, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ButtonMIME, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextMIME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TextContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ButtonMIME)
                .addGap(18, 18, 18))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Crear directorio virtual: "));

        jLabel4.setText("Nombre: ");

        jLabel8.setText("Pagina Principal:");

        jLabel9.setText("Pagina Error: ");

        ButtonDirecVirt.setText("Aceptar");
        ButtonDirecVirt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonDirecVirtMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonDirecVirt)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(TextNombreDir)
                        .addComponent(TextNombrePagPrin)
                        .addComponent(TextNombrePagErr, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextNombreDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextNombrePagPrin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextNombrePagErr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(ButtonDirecVirt))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Editar nº de respuestas"));

        jLabel5.setText("Nº respuestas:");

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(EnviarRespuestas)
                    .addComponent(TextRespuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TextRespuestas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(EnviarRespuestas)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Respuestas Máximas"));

        TextPaneRespuestas.setEditable(false);
        TextPaneRespuestas.setBackground(new java.awt.Color(240, 240, 240));
        TextPaneRespuestas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TextPaneRespuestas.setText("50");
        jScrollPane3.setViewportView(TextPaneRespuestas);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(ButtonMostrarCliente)
                                .addGap(18, 18, 18)
                                .addComponent(ButtonBanear)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonMostrarCliente)
                            .addComponent(ButtonBanear))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
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
        DirectorioVirtual.ArchivarDirectorioVirtual(DirectorioVirtual.directorios);
        TipoMIME.ArchivartiposMime(TipoMIME.getTiposMime());
 
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
            Cliente.ArchivarClientes(Cliente.getClientes());
            DirectorioVirtual.ArchivarDirectorioVirtual(DirectorioVirtual.directorios);
            TipoMIME.ArchivartiposMime(TipoMIME.getTiposMime());
            
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
        String clien="Clientes:\r\n";
        for (Cliente cli : Cliente.getClientes()){
           clien= clien +cli.cliente2CSV()+"\r\n";          
       }
       TextClientes.setText(clien);
    }//GEN-LAST:event_ButtonMostrarClienteMouseClicked

    private void ButtonDirecVirtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonDirecVirtMouseClicked
        String NombreDirVirt = TextNombreDir.getText();
        String PagPrinc = TextNombrePagPrin.getText();
        String PagErr = TextNombrePagErr.getText();
        TextNombreDir.setText("");
        TextNombrePagPrin.setText("");
        TextNombrePagErr.setText("");
        DirectorioVirtual dv = new DirectorioVirtual(NombreDirVirt, PagPrinc, PagErr);
        try {
            File directorio = new File(NombreDirVirt);
            directorio.mkdir();
            File archivoPagPrin = new File(directorio, PagPrinc);
            archivoPagPrin.createNewFile();
            File archivoPagError = new File(directorio, PagErr);
            archivoPagError.createNewFile();
            DirectorioVirtual.directorios.add(dv);
            DirectorioVirtual.ArchivarDirectorioVirtual(DirectorioVirtual.directorios);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }//GEN-LAST:event_ButtonDirecVirtMouseClicked

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
    private javax.swing.JToggleButton ButtonDirecVirt;
    private javax.swing.JButton ButtonMIME;
    private javax.swing.JButton ButtonMostrarCliente;
    private javax.swing.JButton EnviarRespuestas;
    private javax.swing.ButtonGroup Estado;
    private javax.swing.JTextArea TextClientes;
    private javax.swing.JTextField TextContent;
    private javax.swing.JTextField TextMIME;
    private javax.swing.JTextField TextNombreDir;
    private javax.swing.JTextField TextNombrePagErr;
    private javax.swing.JTextField TextNombrePagPrin;
    private javax.swing.JTextPane TextPaneRespuestas;
    private javax.swing.JTextField TextRespuestas;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTextArea jTextArea1;
    private javax.swing.JRadioButton of;
    private javax.swing.JRadioButton on;
    // End of variables declaration//GEN-END:variables
}
