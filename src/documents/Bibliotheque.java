package documents;

import emprunteur.*;

public class Bibliotheque {
    
    Adherent[] ListeAdherent;
    Document[] ListeDocument;
    int iAdherent;  //indice de la premiere case vide dans ListeAdherent
    int iDocument;  //indice de la premiere case vide dans ListeDocument
    
    public Bibliotheque(int n, int m){
        ListeAdherent= new Adherent[n];
        ListeDocument=new Document[m];
        iAdherent=0;
        iDocument=0; 
    }
    
    public String toString(){
       String ch="";
       ch=ch+"**************************************\n"  ;       
       ch=ch+"********  Liste Des Documents ********\n"  ;  
       ch=ch+"**************************************\n"  ;
       for(int i=0;i<iDocument;i++){
           ch=ch+"\t Document "+(i+1)+":\n";
           ch=ch+"\t"+ListeDocument[i].toString();}
       ch=ch+"\n**************************************\n";       
       ch=ch+"********  Liste Des Adherents ********\n"  ;  
       ch=ch+"**************************************\n"  ;
       for(int i=0;i<iAdherent;i++){
           ch=ch+"\t Adherent "+(i+1)+":\n";
           ch=ch+"\t"+ListeAdherent[i].toString();
       } 
       return ch;
    }
   
    Document[] getDocumentByEditeur(String editeur){
        //Compter le nombre des documents de l'editeur
        int n=0;
        for(int i=0;i<iDocument;i++){
          if(ListeDocument[i].getEditeur().equals(editeur)){
              n++; }
        }     
        //retourner les documents de l'editeur
        Document[] td = new  Document[n];
        int j=0;
        for(int i=0;i<iDocument;i++){
          if(ListeDocument[i].getEditeur().equals(editeur)){
              td[j] = ListeDocument[i];
              j++;  
          }
        }
        return td;
    }
    Document[] getDocumentByAuteur(String auteur){  // Chaque Document avoir un auteur ou un liste auteur donc 
                                                    // il faut parcourir d'abord la liste des documents
           //Compter le nombre des documents de l'auteur
        int n=0;
        for(int i=0;i<iDocument;i++){
            for(int j=0;j<ListeDocument[i].getAuteur().length;j++)
                 if(ListeDocument[i].getAuteur()[j].equals(auteur)){   // ListeDocument[i].getAuteur()
                      n++; }                                           // retourne un tableau d'auteur
        }     
        //retourner les documents de l'auteur
        Document[] td = new Document[n];
        int k=0;
        for(int i=0;i<iDocument;i++){
            for(int j=0;j<ListeDocument[i].getAuteur().length;j++)
                 if(ListeDocument[i].getAuteur()[j].equals(auteur)){
                        td[k] = ListeDocument[i];
                                 k++;  
          }
        }
        return td; 
    }
   
    int getDocumentByIsbn(String isbn){
        for(int i=0;i<iDocument;i++){
          if(ListeDocument[i].getIsbn().equals(isbn)){
              return i;}
        }
        return -1;
    }
    
    int getDocumentByTitre(String titre){
        for(int i=0;i<iDocument;i++){
          if(ListeDocument[i].getTitre().equals(titre)){
              return i;}
        }
        return -1;    
    }
    
    int  getEtudiantByCne(String cne){
      
        for(int i=0;i<iAdherent;i++){
          if( (ListeAdherent[i] instanceof Etudiant) &&  (((Etudiant)ListeAdherent[i]).getCne().equals(cne))   )
              return i;
        }
        return -1; 
    }
     int getProfesseurByCin(String cin){
      
        for(int i=0;i<iAdherent;i++){
          if( (ListeAdherent[i] instanceof Professeur) &&  (((Professeur)ListeAdherent[i]).getCin().equals(cin))  )
              return i;
        }
        return -1; 
    }     
     
    boolean SupprimerDocument(String titre) {
        if (getDocumentByTitre( titre)!=-1){
            for(int i=getDocumentByTitre(titre);i<iDocument-1;i++){
                ListeDocument[i]=ListeDocument[i+1];
            }
            iDocument--;
            return true;
        }
        else
            return false;
    }
    boolean SupprimerEtudiant(String cne) {
   if (getEtudiantByCne(cne) !=-1){
            for(int i=getEtudiantByCne(cne);i<iAdherent-1;i++){
                ListeAdherent[i]=ListeAdherent[i+1];
            }
            iAdherent--;
            return true;
        }
        else
            return false;
    }
    boolean SupprimerProfesseur(String cin) {
      if (getProfesseurByCin(cin)!=-1){
            for(int i=getProfesseurByCin(cin);i<iAdherent-1;i++){
                ListeAdherent[i]=ListeAdherent[i+1];
            }
            iAdherent--;
            return true;
        }
        else
            return false;
    }
    
    boolean  AjouterDocument(Document d){      // Methode qui permet un Document à un Adherent
        if (getDocumentByIsbn(d.getIsbn())!=-1){
            ListeDocument[getDocumentByIsbn(d.getIsbn())].AjouterExemplaires(1);
            return true;
        }
        else{
            if(ListeDocument.length==iDocument){
                return false;
            }
            else{
                ListeDocument[iDocument]=d;
                iDocument++;
                return true;
            }     
        }      
    }
    
    boolean AjouterAdherent(Adherent a){
        if( (a instanceof Etudiant) && getEtudiantByCne(((Etudiant)a).getCne())== -1){
           if(ListeAdherent.length==iAdherent){
                return false;
            }
            else{
                ListeAdherent[iAdherent]=(Etudiant)a;
                iAdherent++;
                return true;
            }         
        }
        else if ( (a instanceof Professeur) && getProfesseurByCin(((Professeur)a).getCin())==-1){
            if(ListeAdherent.length==iAdherent){
                return false;
            }
            else{
                ListeAdherent[iAdherent]=(Professeur)a;
                iAdherent++;
                return true;
            }    
        }
        else
            return false;
            
    }

  
    public static void main(String[] args){
        Bibliotheque b= new Bibliotheque(10,10);
        System.out.println(b);
    }    
}
