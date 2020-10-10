/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projettictactoe;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author elham
 */
public class Principale extends javax.swing.JFrame {
     int idPlay=-1;
    private HashMap<String,String> specifications = new HashMap<>();   
    private PrincipalPanel panel = new PrincipalPanel();
    private HashMap<Integer, TictactoePanel> panels = new HashMap<>();
    private boolean player = true ; 
    private TicTacToePosition ticTacToePosition  = new TicTacToePosition();
    private TicTacToe ticTacToe = new TicTacToe();
    private Vector<int[]> grille = new Vector();
    private List<int[]> etapes = new ArrayList<>();
    private static final String gamesFolder = "./games/";
    private  boolean firstserialisation=true;
    private String nomFichier;
    private int[] tmp = new int[3];
    private int[] tmp1 = new int[3];
    private int depth = 0;
    public Principale(HashMap<String,String> specifications, int depth, boolean robotStart) {
        
        this.depth = depth;
        for (int i = 0; i < 9; i++) {
            panels.put(i, new TictactoePanel(this));
            grille.add(new int[9]);
        }
        this.specifications = specifications;
        initComponents();
        panel.setLocation(300, 70);
        panel.setSize(340, 340);
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setVisible(true);
        this.backButton.setEnabled(false);
        this.forwardButton.setEnabled(false);
        this.add(this.panel);

        this.addPanels();
         this.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        if (JOptionPane.showConfirmDialog(null, "Voulez vous sauvegarder cette partie?", "Sauvegarder", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            serialise();
            System.exit(0);
        }
    }
});

        
        if(robotStart==true && specifications.get("player2").equals("Robot")){
        RobotPlay();
        }
     animation.setVisible(false);
     if(!specifications.get("player2").equals("Robot")){
         this.tourJlabel.setText("Tour du joueur X");
     }

    }
    public Principale(String nomFichier,List<int[]> etapes,HashMap<String,String> specifications,Vector<int[]> t, int depth ) {
        
        this.depth = depth; 
        this.nomFichier = nomFichier;
        //this.firstserialisation = false ;
        this.specifications = specifications;
        this.grille = t;
        this.etapes = etapes;
        
        for (int i = 0; i < 9; i++) {
            panels.put(i, new TictactoePanel(this,etapes,specifications));
            grille.add(new int[9]);
        }
        
        initComponents();
        panel.setLocation(300, 70);
        panel.setSize(340, 340);
        panel.setVisible(true);
        this.backButton.setEnabled(false);
        this.forwardButton.setEnabled(false);
        this.add(this.panel);

        this.addPanels();
       this.changePanel(TictactoePanel.lastelement);
       this.firstserialisation=false;
        if (etapes.isEmpty()) {
            enableAll();
        }
        for (int i = 0; i < 9; i++) {
            
            if (wonPosition(panels.get(i))) {
                desable();
                break;
            }
        }
        animation.setVisible(true);
         if(!specifications.get("player2").equals("Robot")){
             if(etapes.get(etapes.size()-1)[2]==1)
         this.tourJlabel.setText("Tour du joueur O");
             else
                 this.tourJlabel.setText("Tour du joueur X");
             
     }
    }
    
//        public Principale(List<int[]> etapes, HashMap<String,String> specifications) {
//        
//        this.etapes = etapes;
//        for (int i = 0; i < 9; i++) {
//            panels.put(i, new TictactoePanel(this));
//            grille.add(new int[9]);
//        }
//        this.specifications = specifications;
//        initComponents();
//        panel.setLocation(350, 70);
//        panel.setSize(400, 400);
//        panel.setVisible(true);
//        this.add(this.panel);
//
//        this.addPanels();
//
//    }
    public Principale(){}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        animation = new javax.swing.JButton();
        forwardButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        rejouer = new javax.swing.JButton();
        enregistrer = new javax.swing.JButton();
        helpe = new javax.swing.JButton();
        tourJlabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        animation.setText("||");
        animation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animationActionPerformed(evt);
            }
        });

        forwardButton.setText("->");
        forwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButtonActionPerformed(evt);
            }
        });

        backButton.setText("<-");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        rejouer.setText("rejouer");
        rejouer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejouerActionPerformed(evt);
            }
        });

        enregistrer.setText("Enregistrer");
        enregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enregistrerActionPerformed(evt);
            }
        });

        helpe.setText("HELP");
        helpe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helpe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(enregistrer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rejouer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(forwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(animation, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forwardButton)
                    .addComponent(animation)
                    .addComponent(backButton))
                .addGap(59, 59, 59)
                .addComponent(enregistrer)
                .addGap(18, 18, 18)
                .addComponent(rejouer)
                .addGap(18, 18, 18)
                .addComponent(helpe)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        tourJlabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tourJlabel.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236)
                .addComponent(tourJlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(tourJlabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enregistrerActionPerformed
serialisation();
        
    }//GEN-LAST:event_enregistrerActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        if(specifications.get("type").equals("Jeux à deux")){
     System.out.println("etapes taille : "+etapes.size());
        tmp[0] = etapes.get(etapes.size()-1)[0];//position panel    
        tmp[1] = etapes.get(etapes.size()-1)[1];//position boutton
        tmp[2] = etapes.get(etapes.size()-1)[2];//la valeur du bouton 1 -1 0
        System.out.println("tmp[0] : "+tmp[0]);
        System.out.println("tmp[1]"+tmp[1]);
        System.out.println("tmp[2]"+tmp[2]);
        etapes.remove(etapes.size()-1);
        this.panels.get(tmp[0]).getButtons().get(tmp[1]).setText("");
        this.panels.get(tmp[0]).getButtons().get(tmp[1]).setEtat(true);
        this.panels.get(tmp[0]).getButtons().get(tmp[1]).setXo(0);
        this.grille.get(tmp[0])[tmp[1]]=0;
        this.changePanel(tmp[0]);
        idPlay=tmp[0];
        Buttons.tour = !Buttons.tour;
        backButton.setEnabled(false);
        forwardButton.setEnabled(true);
}   
if(specifications.get("type").equals("Robot")){
     System.out.println("etapes taille*** : "+etapes.size());
        tmp[0] = etapes.get(etapes.size()-1)[0];//position panel    
        tmp[1] = etapes.get(etapes.size()-1)[1];//position boutton
        tmp[2] = etapes.get(etapes.size()-1)[2];
        tmp1[0] = etapes.get(etapes.size()-2)[0];//position panel    
        tmp1[1] = etapes.get(etapes.size()-2)[1];//position boutton
        tmp1[2] = etapes.get(etapes.size()-2)[2];
        etapes.remove(etapes.size()-1);
        etapes.remove(etapes.size()-1);
        this.panels.get(tmp[0]).getButtons().get(tmp[1]).setText("");
        this.panels.get(tmp[0]).getButtons().get(tmp[1]).setEtat(true);
        this.panels.get(tmp[0]).getButtons().get(tmp[1]).setXo(0);
        this.grille.get(tmp[0])[tmp[1]]=0;
        this.panels.get(tmp1[0]).getButtons().get(tmp1[1]).setText("");
        this.panels.get(tmp1[0]).getButtons().get(tmp1[1]).setEtat(true);
        this.panels.get(tmp1[0]).getButtons().get(tmp1[1]).setXo(0);
        this.grille.get(tmp1[0])[tmp1[1]]=0;
        this.changePanel(tmp1[0]);
        idPlay=tmp[0];
         Buttons.tour = !Buttons.tour;
        backButton.setEnabled(false);
        forwardButton.setEnabled(true);
}
    }//GEN-LAST:event_backButtonActionPerformed

    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButtonActionPerformed
if(specifications.get("type").equals("Jeux à deux")){
        etapes.add(tmp);
        if(tmp[2] == 1)this.panels.get(tmp[0]).getButtons().get(tmp[1]).setText("<html><font color = blue>X</font></html>");
        if(tmp[2] == -1)this.panels.get(tmp[0]).getButtons().get(tmp[1]).setText("<html><font color = blue>O</font></html>");
        this.panels.get(tmp[0]).getButtons().get(tmp[1]).setEtat(false);
        this.panels.get(tmp[0]).getButtons().get(tmp[1]).setXo(tmp[2]);
        this.grille.get(tmp[0])[tmp[1]]=tmp[2];
        this.changePanel(tmp[1]);
        idPlay=tmp[1];
        Buttons.tour = !Buttons.tour;
        backButton.setEnabled(true);
        forwardButton.setEnabled(false);
} 
if(specifications.get("type").equals("Robot")){
        etapes.add(tmp1);
        etapes.add(tmp);
        if(tmp[2] == 1)this.panels.get(tmp[0]).getButtons().get(tmp[1]).setText("<html><font color = blue>X</font></html>");
        if(tmp[2] == -1)this.panels.get(tmp[0]).getButtons().get(tmp[1]).setText("<html><font color = blue>O</font></html>");
        if(tmp1[2] == 1)this.panels.get(tmp1[0]).getButtons().get(tmp1[1]).setText("<html><font color = blue>X</font></html>");
        if(tmp1[2] == -1)this.panels.get(tmp1[0]).getButtons().get(tmp1[1]).setText("<html><font color = blue>O</font></html>");
        this.panels.get(tmp[0]).getButtons().get(tmp[1]).setEtat(false);
        this.panels.get(tmp[0]).getButtons().get(tmp[1]).setXo(tmp[2]);
        this.grille.get(tmp[0])[tmp[1]]=tmp[2];
        this.panels.get(tmp1[0]).getButtons().get(tmp1[1]).setEtat(false);
        this.panels.get(tmp1[0]).getButtons().get(tmp1[1]).setXo(tmp1[2]);
        this.grille.get(tmp1[0])[tmp1[1]]=tmp1[2];
        this.changePanel(tmp1[1]);
        idPlay=tmp[1];
        Buttons.tour = !Buttons.tour;
        backButton.setEnabled(true);
        forwardButton.setEnabled(false);
}
    }//GEN-LAST:event_forwardButtonActionPerformed

    private void rejouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejouerActionPerformed
rejouer();
    }//GEN-LAST:event_rejouerActionPerformed

    private void animationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_animationActionPerformed
       //issam-----------------
         for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                panels.get(i).getButtons().get(j).setText("");
                panels.get(i).getButtons().get(j).setXo(0);
                panels.get(i).getButtons().get(j).setEtat(true);
            }
            enableAll();
        }
//        

Runnable m = new Runnable() {
             @Override
             public void run() {
                       for (int i = 0; i < etapes.size(); i++) {
            int[] get = etapes.get(i);
            
            for (int j = 0; j < 9; j++) {
                System.out.println(panels.get(j).getIdp());
                if (get[0]==panels.get(j).getIdp()) {
              
                    
                    try {
                        panels.get(j).getButtons().get(get[1]).setXo(get[2]);
                        panels.get(j).getButtons().get(get[1]).setEtat(false);
                        if (get[2]==1) {
                            panels.get(j).getButtons().get(get[1]).setText("<html><font color = blue>X</font></html>");
                            changePanel(get[1]);
                        Thread.sleep(3000);
                        }
                        if (get[2]==-1) {
                            panels.get(j).getButtons().get(get[1]).setText("<html><font color = red>O</font></html>");
                            changePanel(get[1]);
                        Thread.sleep(3000);
                        }
                              if (wonPosition(panels.get(j))) {
                desable();
                break;
            }
                        
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Principale.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                        break;
                        
                        
                    } 
                
                
                
            }
            }  
             }
         };
        
new Thread(m).start();

    }//GEN-LAST:event_animationActionPerformed

    private void helpeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpeActionPerformed
        // TODO add your handling code here:
         TicTacToePosition pos = new TicTacToePosition();
       pos.setBoard(this.grille.get(idPlay));
       TicTacToe tic = new TicTacToe();
       pos = (TicTacToePosition)tic.playGame(pos, true, 9);
        for (int i = 0; i < 9; i++) {
            if(this.grille.get(idPlay)[i]!=pos.getBoard()[i])
           panels.get(idPlay).getButtons().get(i).setBackground(Color.yellow);
        }    
    }//GEN-LAST:event_helpeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principale().setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton animation;
    private javax.swing.JButton backButton;
    private javax.swing.JButton enregistrer;
    private javax.swing.JButton forwardButton;
    private javax.swing.JButton helpe;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton rejouer;
    private javax.swing.JLabel tourJlabel;
    // End of variables declaration//GEN-END:variables
    public void addPanels() {
        this.panels.get(0).setLocation(10, 10);
        this.panels.get(1).setLocation(120, 10);
        this.panels.get(2).setLocation(230, 10);
        this.panels.get(3).setLocation(10, 120);
        this.panels.get(4).setLocation(120, 120);
        this.panels.get(5).setLocation(230, 120);
        this.panels.get(6).setLocation(10, 230);
        this.panels.get(7).setLocation(120, 230);
        this.panels.get(8).setLocation(230, 230);

        this.panel.add(panels.get(0));
        panels.get(0).setBorder(BorderFactory.createEtchedBorder());
        this.panel.add(panels.get(1));
        panels.get(1).setBorder(BorderFactory.createEtchedBorder());
        this.panel.add(panels.get(2));
        panels.get(2).setBorder(BorderFactory.createEtchedBorder());
        this.panel.add(panels.get(3));
        panels.get(3).setBorder(BorderFactory.createEtchedBorder());
        this.panel.add(panels.get(4));
        panels.get(4).setBorder(BorderFactory.createEtchedBorder());
        this.panel.add(panels.get(5));
        panels.get(5).setBorder(BorderFactory.createEtchedBorder());
        this.panel.add(panels.get(6));
        panels.get(6).setBorder(BorderFactory.createEtchedBorder());
        this.panel.add(panels.get(7));
        panels.get(7).setBorder(BorderFactory.createEtchedBorder());
        this.panel.add(panels.get(8));
        panels.get(8).setBorder(BorderFactory.createEtchedBorder());
    }

    public void changePanel(int id) {
        if(plein(id)){
            enableAll();
        } else {
            for (int i = 0; i < 9; i++) {
            desablePanel(panels.get(i));
        }
            
for (int i = 0; i < 9; i++) {
                panels.get(id).getButtons().get(i).setBackground(null);
            }
        enablePanel(panels.get(id));
            
        
        }
        
        
    }

    public void enablePanel(TictactoePanel pan) {
        Component[] com = pan.getComponents();
        for (int i = 0; i < com.length; i++) {
            com[i].setEnabled(true);
        }
    }

    public void desablePanel(TictactoePanel pan) {
        Component[] com = pan.getComponents();
        for (int i = 0; i < com.length; i++) {
            com[i].setEnabled(false);
        }

    }
    public void desable(){
        for (int i = 0; i < 9; i++) {
            desablePanel(panels.get(i));
            
        }
    }
    public void enableAll(){
        
         for (int i = 0; i < 9; i++) {
              for (int j = 0; j < 9; j++) {
                  if(panels.get(i).getButtons().get(j).isEtat()){
                     panels.get(i).getButtons().get(j).setEnabled(true);
                  } else {
                     panels.get(i).getButtons().get(j).setEnabled(false);

                  }
              }
         }
        
    }
    
    public boolean plein(int id){
        boolean res = true;
        for (int i = 0; i < 9; i++) {
            if(this.panels.get(id).getButtons().get(i).getXo() == 0){
                res = false;
                break;
            }
        }
    return res;
    }
    
    

    public HashMap<Integer, TictactoePanel> getPanels() {
        return panels;
    }

    public void setPanels(HashMap<Integer, TictactoePanel> panels) {
        this.panels = panels;
    }

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public TicTacToePosition getTicTacToePosition() {
        return ticTacToePosition;
    }

    public void setTicTacToePosition(TicTacToePosition ticTacToePosition) {
        this.ticTacToePosition = ticTacToePosition;
    }

    public TicTacToe getTicTacToe() {
        return ticTacToe;
    }

    public void setTicTacToe(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
    }

    public Vector<int[]> getGrille() {
        return grille;
    }

    public void setGrille(Vector<int[]> grille) {
        this.grille = grille;
    }

    public HashMap<String, String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(HashMap<String, String> specifications) {
        this.specifications = specifications;
    }

    public List<int[]> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<int[]> etapes) {
        this.etapes = etapes;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    //issam
    public void rejouer(){
        
        Buttons.tour=true;
        //this.grille.clear();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grille.get(i)[j]=0;
            }
        }
        this.etapes.clear();
        
        
        System.out.println(etapes);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                panels.get(i).getButtons().get(j).setText("");
                panels.get(i).getButtons().get(j).setXo(0);
                panels.get(i).getButtons().get(j).setEtat(true);
                panels.get(i).getButtons().get(j).setBackground(null);
            }
            enableAll();
        }
        
    
    }

    public boolean isFirstserialisation() {
        return firstserialisation;
    }

    public void setFirstserialisation(boolean firstserialisation) {
        this.firstserialisation = firstserialisation;
    }
   
    public void serialisation(){
           if (firstserialisation==true) {
               try {
            System.err.println("************************************************");
            // player 1  est notre utilisateur
                String date = new Date().toString();
                String[] d = date.split(" ");
                String[] e = d[3].split(":");
                this.nomFichier = specifications.get("player1")+d[0]+d[1]+d[2]+"_"+e[0]+"_"+e[1]+"_"+e[2]+".ahi";
                
              FileOutputStream ots = new FileOutputStream(gamesFolder+this.nomFichier);
                ObjectOutputStream obj = new ObjectOutputStream(ots);
      obj.writeInt(this.depth);          
      obj.writeObject(this.etapes);
      obj.writeObject(this.specifications);
      obj.writeObject(this.grille);
      obj.writeObject(this.firstserialisation);
      
      //obj.writeObject(this.panels.get(0).getButtons());

      obj.close();
      
                  try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe", "root", "");
            PreparedStatement state = cnx.prepareStatement("INSERT INTO  partie (user, nomadv, type,nomfichier) VALUES (?,?,?,?)");
            state.setString(1,specifications.get("player1"));
             state.setString(2, specifications.get("player2"));
            state.setString(3, specifications.get("type"));
            state.setString(4, this.nomFichier);
            

            int rs = state.executeUpdate();
            state.close();
            cnx.close();
            firstserialisation=false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
      
        } catch (Exception e) {
                   System.out.println(e.getStackTrace() );
        }
        }else{
             try {                
              FileOutputStream ots = new FileOutputStream(gamesFolder+this.nomFichier);
                ObjectOutputStream obj = new ObjectOutputStream(ots);
      obj.writeInt(this.depth); 
      obj.writeObject(this.etapes);
      obj.writeObject(this.specifications);
      obj.writeObject(this.grille);
      obj.writeObject(this.firstserialisation);
      
      obj.close();
      firstserialisation=false;
      
        } catch (Exception e) {
        }
        }
    }
    public boolean winCheck(int i1, int i2, int i3, TictactoePanel pos) {
        
        if (pos.getButtons().get(i1).getXo() == 1 && pos.getButtons().get(i2).getXo() == 1 && pos.getButtons().get(i3).getXo() == 1)return true;
        if (pos.getButtons().get(i1).getXo() == -1 && pos.getButtons().get(i2).getXo() == -1 && pos.getButtons().get(i3).getXo() == -1)return true;
        return false;
    }
    public boolean wonPosition(TictactoePanel p) {
        boolean ret = false;
        if (winCheck(0,1,2, p)) ret = true;
        else if (winCheck(3,4,5, p)) ret = true;
        else if (winCheck(6,7,8,p)) ret = true;
        else if (winCheck(2,5,8, p)) ret = true;
        else if (winCheck(0,4,8, p)) ret = true;
        else if (winCheck(2,4,6, p)) ret = true;
        else if (winCheck(0,3,6, p)) ret = true;
        else if (winCheck(1,4,7,p)) ret = true;
        
        return ret;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JButton getForwardButton() {
        return forwardButton;
    }

    public void setForwardButton(JButton forwardButton) {
        this.forwardButton = forwardButton;
    }
    
       public void serialise(){
        
        if (firstserialisation==true) {
               try {
            System.err.println("************************************************");
            // player 1  est notre utilisateur
                String date = new Date().toString();
                String[] d = date.split(" ");
                String[] e = d[3].split(":");
                this.nomFichier = specifications.get("player1")+d[0]+d[1]+d[2]+"_"+e[0]+"_"+e[1]+"_"+e[2]+".ahi";
                
              FileOutputStream ots = new FileOutputStream(gamesFolder+this.nomFichier);
                ObjectOutputStream obj = new ObjectOutputStream(ots);
      obj.writeInt(this.depth);          
      obj.writeObject(this.etapes);
      obj.writeObject(this.specifications);
      obj.writeObject(this.grille);
      
      //obj.writeObject(this.panels.get(0).getButtons());

      obj.close();
      firstserialisation=false;
                  try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe", "root", "");
            PreparedStatement state = cnx.prepareStatement("INSERT INTO  partie (user, nomadv, type,nomfichier) VALUES (?,?,?,?)");
            state.setString(1,specifications.get("player1"));
             state.setString(2, specifications.get("player2"));
            state.setString(3, specifications.get("type"));
            state.setString(4, this.nomFichier);
            

            int rs = state.executeUpdate();
            state.close();
            cnx.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
      
        } catch (Exception e) {
                   System.out.println(e.getStackTrace() );
        }
        }else{
             try {                
              FileOutputStream ots = new FileOutputStream(gamesFolder+this.nomFichier);
                ObjectOutputStream obj = new ObjectOutputStream(ots);
      obj.writeInt(this.depth); 
      obj.writeObject(this.etapes);
      obj.writeObject(this.specifications);
      obj.writeObject(this.grille);
      
      obj.close();
      firstserialisation=false;
      
        } catch (Exception e) {
        }
        }
    }
       
        public void RobotPlay(){
        
          TicTacToePosition pos = new TicTacToePosition();
       pos.setBoard(this.grille.get(0));
       TicTacToe tic = new TicTacToe();
       pos = (TicTacToePosition)tic.playGame(pos, true, depth);
                    for (int i = 0; i < 9; i++) {
                        System.err.print("|"+getGrille().get(0)[i]);
                        if(pos.getBoard()[i] != getGrille().get(0)[i]){
                            System.out.println("------"+i);
                            getGrille().get(0)[i] = -1;
                            getPanels().get(0).getButtons().get(i).setXo(-1);
                            getPanels().get(0).getButtons().get(i).setEtat(false);
                            getPanels().get(0).getButtons().get(i).setText("<html><font color = red>O</font></html>");
                            getEtapes().add(new int[]{0,i,-1});
                            changePanel(i);
                            idPlay=i;
                            
                            break;
                        }
                    }
    }
        
        public int getIdPlay() {
        return idPlay;
    }

    public void setIdPlay(int idPlay) {
        this.idPlay = idPlay;
    }

    public JLabel getTourJlabel() {
        return tourJlabel;
    }

    public void setTourJlabel(JLabel tourJlabel) {
        this.tourJlabel = tourJlabel;
    }
    
    
}
