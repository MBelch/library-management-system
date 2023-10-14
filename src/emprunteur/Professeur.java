
package emprunteur;

import javax.swing.text.StyledEditorKit;

public class Professeur extends Adherent {
    
    private String cin;
    private String matiere;

    public Professeur(String cin, String matiere, String nom, String prenom, int age) {
        super(nom, prenom, age);
        super.setNombreEmpruntsMax(5);  //Professeur peut emprunter au maximum 5 livres
        this.cin = cin;
        this.matiere = matiere;
    }



    @Override
     public String toString(){
         
         return "les informations de professeur :" +super.toString()+ " le CIN :" + this.cin 
                + "la matiere :"+ this.matiere; 
     }

    public String getCin() {
        return cin;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    
/*
    public static int getN() {
        return n;
    }
   */ 
     
}
