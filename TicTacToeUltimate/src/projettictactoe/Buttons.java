/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projettictactoe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import static projettictactoe.GameSearch.PROGRAM;

/**
 *
 * @author elham
 */
public class Buttons extends JButton implements Serializable{
    private int idParent;
    private int id;
    private Principale principale;
    private boolean etat = true;
    private int xo = 0;
    public static boolean tour = true;//tour de role des joueurs
    
    public Buttons(int i,Principale principale, int idParent){
        this.id = i;
        this.idParent = idParent;
        this.principale = principale;
        
        
        
        
        
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 if(etat==false){
                }
                else{
                principale.getBackButton().setEnabled(true);
                principale.getForwardButton().setEnabled(false);
                //mode deux joueurs
                if(principale.getSpecifications().get("type").equals("Jeux à deux")){
                    if(tour == true){
                        if(Egalite()){
                            System.out.println("personne n'a gagné");
                            WhoWon whoWon = new WhoWon(null, false,principale.getSpecifications().get("player1"),principale);
                        whoWon.setVisible(true);
                        whoWon.setLocationRelativeTo(null);
                        }
                    setText("<html><font color = blue>X</font></html>"); 
                    xo =1;
                    etat = false;
                    principale.getGrille().get(idParent)[id] = 1;
                    principale.getTicTacToePosition().setBoard(principale.getGrille().get(idParent));
                    
                    principale.getEtapes().add(new int[]{idParent,id,xo});
                    if (wonPosition(principale.getTicTacToePosition(), true)) {
                        System.out.println(principale.getSpecifications().get("player1"));
                        principale.desable();
                        WhoWon whoWon = new WhoWon(null, false,principale.getSpecifications().get("player1"),principale);
                        whoWon.setVisible(true);
                        whoWon.setLocationRelativeTo(null);
                        return;
                    }  
                    principale.changePanel(id);
                    principale.setIdPlay(id);
                    tour = false;
                    principale.getTourJlabel().setText("Tour du joueur O");
                        System.out.println(etat);
                    
                    } else {
                        if(Egalite()){
                            System.out.println("personne n'a gagné");
                            WhoWon whoWon = new WhoWon(null, false,principale.getSpecifications().get("player1"),principale);
                        whoWon.setVisible(true);
                        whoWon.setLocationRelativeTo(null);
                        }
                    setText("<html><font color = red>O</font></html>");// user 
                    xo = -1;
                    etat = false;
                    principale.getGrille().get(idParent)[id] = -1;
                    principale.getTicTacToePosition().setBoard(principale.getGrille().get(idParent));
                       principale.getEtapes().add(new int[]{idParent,id,xo});
                    if (wonPosition(principale.getTicTacToePosition(), false)) {
                        System.out.println(principale.getSpecifications().get("player2"));
                        principale.desable();
                        WhoWon whoWon = new WhoWon(null, false,principale.getSpecifications().get("player2"),principale);
                        whoWon.setVisible(true);
                        whoWon.setLocationRelativeTo(null);
                        return;
                    } 
                    principale.changePanel(id);
                    principale.setIdPlay(id);
                    tour = true;
                    System.out.println(etat);
                    principale.getTourJlabel().setText("Tour du joueur X");
                    }
                    for (int j = 0; j < principale.getEtapes().size(); j++) {
                        for (int k = 0; k < 3; k++) {
                             System.out.println(principale.getEtapes().get(j)[k]);
                        }
                    }
                    
                  //mode Robot
                } else {
           // partie player
                    setText("<html><font color = blue>X</font></html>");
                    xo =1;
                    etat = false;  
                    principale.getGrille().get(idParent)[id] = 1;//vecteur 
            // ajoute -------------issam
                    principale.getTicTacToePosition().setBoard(principale.getGrille().get(idParent));
                    principale.getEtapes().add(new int[]{idParent,id,xo});
                       if (wonPosition(principale.getTicTacToePosition(), true)) {
                        System.out.println(principale.getSpecifications().get("player1"));
                        principale.desable();
                        WhoWon whoWon = new WhoWon(null, false,principale.getSpecifications().get("player1"),principale);
                        whoWon.setVisible(true);
                        whoWon.setLocationRelativeTo(null);
                        return;
                       }
//                       for (int j = 0; j < principale.getEtapes().size(); j++) {
//                        for (int k = 0; k < 3; k++) {
//                             System.out.println(principale.getEtapes().get(j)[k]);
//                        }
//                    }
            //partie Robot         
                    principale.getTicTacToePosition().setBoard(principale.getGrille().get(id));
                    TicTacToePosition t = principale.getTicTacToePosition();
                    TicTacToePosition tmp = (TicTacToePosition)principale.getTicTacToe().playGame(principale.getTicTacToePosition(),principale.isPlayer(), principale.getDepth());
                 
                    for (int i = 0; i < 9; i++) {
                        if(tmp.getBoard()[i] != principale.getGrille().get(id)[i]){
                            principale.getGrille().get(id)[i] = -1;
                            principale.getPanels().get(id).getButtons().get(i).setXo(-1);
                            principale.getPanels().get(id).getButtons().get(i).setEtat(false);
                            principale.getPanels().get(id).getButtons().get(i).setText("<html><font color = red>O</font></html>");
                            principale.getEtapes().add(new int[]{id,i,-1});
                            principale.changePanel(i);
                            principale.setIdPlay(i);
                            break;
                        }
                    }
//                    for (int j = 0; j < principale.getEtapes().size(); j++) {
//                        for (int k = 0; k < 3; k++) {
//                             System.out.println(principale.getEtapes().get(j)[k]);
//                        }
//                    }
                    // ajouter  --------------------issam
                       if (wonPosition(tmp, false)) {
                        System.out.println(principale.getSpecifications().get("player2"));
                        principale.desable();
                        WhoWon whoWon = new WhoWon(null, false,principale.getSpecifications().get("player2"),principale);
                        whoWon.setVisible(true);
                        whoWon.setLocationRelativeTo(null);
                }
                    
                //}   
//                } else {
//                    setText("<html><font color = blue>O</font></html>");//Robot
//                    xo = -1;
//                }
//               
                
//                Buttons tmp = (Buttons)e.getSource();
//                System.out.println(tmp.getId());
                
                
                //principale.setPlayer(!principale.isPlayer());
                
               // System.out.println(idParent);
//                for (int j = 0; j < 9; j++) {
//                    System.out.println("****"+principale.getGrille().get(idParent)[j]);
//                }
                    
                }
              
                
               
            }
            }
        });
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public int getXo() {
        return xo;
    }

    public void setXo(int xo) {
        this.xo = xo;
    }
    public boolean winCheck(int i1, int i2, int i3, boolean player, TicTacToePosition pos) {
        int b = 0;
        if (player ==true) b = 1;
        else        b = -1;
        if (pos.board[i1] == b && pos.board[i2] == b && pos.board[i3] == b)return true;
        return false;
    }
    public boolean wonPosition(Position p, boolean player) {
        if (GameSearch.DEBUG) System.out.println("wonPosition("+p+","+player+")");
        boolean ret = false;
        TicTacToePosition pos = (TicTacToePosition)p;
        if (winCheck(0,1,2, player, pos)) ret = true;
        else if (winCheck(3,4,5, player, pos)) ret = true;
        else if (winCheck(6,7,8, player, pos)) ret = true;
        else if (winCheck(2,5,8, player, pos)) ret = true;
        else if (winCheck(0,4,8, player, pos)) ret = true;
        else if (winCheck(2,4,6, player, pos)) ret = true;
        else if (winCheck(0,3,6, player, pos)) ret = true;
        else if (winCheck(1,4,7, player, pos)) ret = true;
        if (GameSearch.DEBUG) System.out.println("     ret="+ret);
        return ret;
    }
    
    public boolean Egalite() {
        boolean ret = true;
       for(int i=0; i<9; i++){
             for(int j=0; j<9; j++){
                 if(principale.getPanels().get(i).getButtons().get(j).getXo() == 0){
                     ret = false;
                     break;
                 }
             }
           
       }
        return ret;
    }
    
}
