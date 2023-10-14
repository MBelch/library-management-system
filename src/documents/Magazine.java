
package documents;

public class Magazine extends Document{
    private String periodicite;
    final private int mois;
    final private int jour;
    
    
    public Magazine(String titre,String[] auteur, String editeur,int edition,
            int nombreExemplaires, String isbn, String periodicite, int mois, int jour){
        super( titre,auteur,editeur,edition, nombreExemplaires,  isbn);
        this.periodicite=periodicite;
        this.mois=mois;
        this.jour=jour; 
    }
    
    public String toString(){
       return "La magazine possede les informations suivantes"+
                super.toString()+"\n"+
                "La periodecite="+periodicite+"\n"+
                "Le mois d'edition="+mois+"\n"+
                 " et Le jour d'edition="+jour+"\n";
           
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public int getMois() {
        return mois;
    }

    public int getJour() {
        return jour;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }
    
    
    
}

