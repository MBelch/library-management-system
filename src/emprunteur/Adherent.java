
package emprunteur;

import java.util.Random;
import java.util.Scanner;

public class Adherent extends Personne implements Emprunter{
    private int nombreEmpruntsMax;
    private  int nombreEmprunts;
    
    public Adherent(String nom, String prenom, int age){
        super(nom,prenom,age); 
        nombreEmpruntsMax=1;
        nombreEmprunts=0;
        char[] password = new char[8];
        password = password();
 
    }

    
    public String toString(){
        return  super.toString()+ "a effectue "+nombreEmprunts+
                " emprunts"; 
    }




    public int getNombreEmpruntsMax() {
        return nombreEmpruntsMax;
    }

    public int getNombreEmprunts() {
        return nombreEmprunts;
    }

    public void setNombreEmpruntsMax(int nombreEmpruntsMax) {
        this.nombreEmpruntsMax = nombreEmpruntsMax;
    }
    
    

    public void emprunter(){
        
    if (this.nombreEmpruntsMax > this.nombreEmprunts){
              this.nombreEmprunts++;
        }
    }
    
    
    public void rendre(){
    if (this.nombreEmprunts > 0){
        
          this.nombreEmprunts--;
        }
    }

    @Override
    public boolean emprunter(String isbn) {
        
    if (this.nombreEmpruntsMax > this.nombreEmprunts){
           this.nombreEmprunts++;
        return true;
    }
    return false;
    }

    @Override
    public boolean rendre(String isbn) {
        
     if (this.nombreEmprunts > 0){
        
          this.nombreEmprunts--;
          
         return true;
    }
       return false;       
    }
    
    public char[] password(){
          
          char[] password = new char[8];
          Random R = new Random();
          
          for (int i = 0; i < 8; i++) {
              
              password[i] = (char)R.nextInt(128);
          }
          
            return  password;
      }

}