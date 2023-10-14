
package documents;

public class Livre extends Document {
    
    final private int nombreChapitres;
    final private int nombrePages;
    final private String langue;
    final private int tome;
    final private String type;    // par exemple science
    
    public Livre(String titre, String[] auteur, String editeur, int edition, int nombreExemplaires, String isbn,
                 int nombreChapitres, int nombrePages, String langue, int tome, String type) {
        
        super(titre, auteur, editeur, edition, nombreExemplaires, isbn);
        this.nombreChapitres=nombreChapitres;
        this.nombrePages=nombrePages;
        this.langue=langue;   
        this.tome=tome;
        this.type=type;
    }
    public String toString(){
        return "Le livre possede les informations suivantes"+
                super.toString()+"\n"+
                "nombre de chapitres="+nombreChapitres+"\n"+
                "nombre de pages="+nombrePages+"\n"+
                 "langue="+langue+"\n"+
                "le tome="+tome+"\n"+
                "et le type="+type+"\n";
    } 
    public int getNombreChapitres() {
        return nombreChapitres;
    }
    public int getNombrePages() {
        return nombrePages;
    }
    public String getLangue() {
        return langue;
    }
    public int getTome() {
        return tome;
    }
    public String getType() {
        return type;
    } 

    
}
