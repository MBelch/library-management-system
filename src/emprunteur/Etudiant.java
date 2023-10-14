
package emprunteur;

public class Etudiant extends Adherent{
    
    private String cne;
    private String filier;

    public Etudiant(String cne, String filier, String nom, String prenom, int age) {
        super(nom, prenom, age);
        super.setNombreEmpruntsMax(3);  //etudiant peut emprunter au maximum 3 livres
        this.cne = cne;
        this.filier = filier;
        
    }
    
 
    public String toString(){
        
        return "les informations de l'etudiant :" +super.toString()+ " le CNE :" + this.cne 
                + "la filier :"+ this.filier; 
      
    }

     

/*
    public Etudiant(String cne, String filier, Personne Pers) {
        super(Pers);
        this.cne = cne;
        this.filier = filier;
    }
  */  

    public String getCne() {
        return cne;
    }

    public String getFilier() {
        return filier;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public void setFilier(String filier) {
        this.filier = filier;
    }
/*
    public static int getN() {
        return n;
    }
  */  
    
    
    
}
