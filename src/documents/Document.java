package documents;

    public class Document {
    final private String titre;
    final private String[] auteur;
    final private String editeur;
    final private int edition;     // année de l'édition
    private int nombreExemplaires;
    final private String isbn;
    
      Document(String titre,String[] auteur, String editeur,int edition,int nombreExemplaires, String isbn ){
        this.titre=titre;
        this.auteur=auteur;
        this.editeur=editeur;
        this.nombreExemplaires=nombreExemplaires;
        this.isbn=isbn;
        this.edition=edition;  
    }
      
    @Override
    public String toString(){
        
        String auteurListe="";
        
        for(int i=0;i<auteur.length;i++){
            if (auteur[i]!=null)
                    auteurListe=auteurListe+" "+auteur[i];
        }
                
        return "isbn="+isbn+"\n"+
                " titre="+titre+"\n"+
                " auteur="+auteurListe+"\n"+
                " editeur="+editeur+"\n"+
                " edition="+edition+"\n"+
                " nombre d'exemplaires="+nombreExemplaires;   
    }
    public String getTitre() {
        return titre;
    }

    public String[] getAuteur() {
        return auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public int getEdition() {
        return edition;
    }

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }
    
    public void AjouterExemplaires(int n){
        nombreExemplaires = nombreExemplaires + n;
    }
    
    public void SupprimerExemplaires(int n){
         if (this.getNombreExemplaires() >= n)
             nombreExemplaires = nombreExemplaires - n;
    }
    
     public void finalize()     // déstructeur
     {
          System.out.println("Objet nettoyé de la mémoire");   
     }
    
     
    public static void main(String[] args){
        
        String[] auteurs1={"auteur1","auteur2"};
        String[] auteurs2={"auteur3","auteur4"};
        Document d1=new Document("titre1",auteurs1, "editeur1",2009,3, "sdsds");
        Document d2=new Document("titre2",auteurs2, "editeur2",2009,3, "sdsds");
        System.out.println(d2);
        d2=d1;
        System.out.println(d2);
        
        
    }

    
}
