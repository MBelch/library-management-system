
package documents;

public class Dictionnaire extends Document {
    
    final private int tome;
    final private String langue;
    
    
    public Dictionnaire(String titre,String[] auteur, String editeur,int edition,
            int nombreExemplaires, String isbn, int tome, String langue){
        super(titre,auteur,editeur, edition,nombreExemplaires,isbn);
        this.tome=tome;
        this.langue=langue;
        
    }
    
    @Override
    public String toString(){
        return "Le dictionnaire possede les informations suivantes"+
                super.toString()+"\n"+
                "La tome="+tome+"\n"+
                "La langue="+langue+"\n";
    }

    public int getTome() {
        return tome;
    }

    public String getLangue() {
        return langue;
    }
    
}
