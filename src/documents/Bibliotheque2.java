
package documents;
import emprunteur.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Info
 */
public class Bibliotheque2 {
    ArrayList<Adherent> ListeAdherent;
    ArrayList<Document> ListeDocument;
    
    
    public Bibliotheque2(){
        
        ListeAdherent = new ArrayList<Adherent>();
        ListeDocument = new ArrayList<Document>();
    }
    
    public String toString(){
    
        String ch="";
       ch=ch+"**************************************\n"  ;       
       ch=ch+"********  Liste Des Documents ********\n"  ;  
       ch=ch+"**************************************\n"  ;
       for(int i=0;i<ListeDocument.size();i++){
           ch=ch+"\t Document "+i+":\n";
           ch=ch+"\t"+ListeDocument.get(i).toString();
       }
       ch=ch+"\n**************************************\n";       
       ch=ch+"********  Liste Des Adherents ********\n"  ;  
       ch=ch+"**************************************\n"  ;
       for(int i=0; i<ListeAdherent.size(); i++){
           ch=ch+"\t Adherent "+i+":\n";
           ch=ch+"\t"+ListeAdherent.get(i).toString();
       } 
       
         return ch;
    }
   
    
   ArrayList<Document> getDocumentByEditeur(String editeur){
        ArrayList<Document> td = new ArrayList<Document>();
        
        for(int i=0; i<ListeDocument.size(); i++){
          if(ListeDocument.get(i).getEditeur().equals(editeur)){
              td.add(ListeDocument.get(i));}
        }
        return td;
    }
   
   
    ArrayList<Document> getDocumentByAuteur(String auteur){
   
      ArrayList<Document> td=new ArrayList<Document>();
      
        for(int i=0; i<ListeDocument.size(); i++){
            for(int j=0;j<ListeDocument.get(i).getAuteur().length;j++)
                 if(ListeDocument.get(i).getAuteur()[j].equals(auteur)){
                        td.add(ListeDocument.get(i));
          }
        }
        return td; 
    }
    
    
    int getDocumentByIsbn(String isbn){
        for(int i=0; i<ListeDocument.size(); i++){
          if(ListeDocument.get(i).getIsbn().equals(isbn)){
              return i;
          }
        }
        return -1;
    }
    
    int getDocumentByTitre(String titre){
        for(int i=0;i<ListeDocument.size();i++){
          if(ListeDocument.get(i).getTitre().equals(titre)){
              return i;
          }
        }
        return -1;    
    }
    
    int getEtudiantByCne(String cne){
      
        for(int i=0; i<ListeAdherent.size(); i++){
          if( ListeAdherent.get(i) instanceof Etudiant &&  ((Etudiant)ListeAdherent.get(i)).getCne().equals(cne))
              return i;
        }
        return -1; 
    }
    
     int getProfesseurByCin(String cin){
      
        for(int i=0;i<ListeAdherent.size();i++){
          if( ListeAdherent.get(i) instanceof Professeur &&  ((Professeur)ListeAdherent.get(i)).getCin().equals(cin))
              return i;
        }
        return -1; 
    }
 
    boolean SupprimerDocument(String titre) {
        
        if (getDocumentByTitre(titre)!=-1){
            
                ListeDocument.remove(getDocumentByTitre(titre));
                 return true;
             }
        else
            return false;
    }
    
    boolean SupprimerEtudiant(String cne) {
        
        if (getEtudiantByCne(cne)!=-1){
            
                ListeAdherent.remove(getEtudiantByCne(cne));
         
            return true;
        }
        else
            return false;
    }
    
    boolean SupprimerProfesseur(String cin) {
        
      if (getProfesseurByCin(cin)!= -1){
          
                ListeAdherent.remove(getProfesseurByCin(cin));
        
            return true;
        }
        else
            return false;
    }
    
    boolean  AjouterDocument(Document d){
        
        if (getDocumentByIsbn(d.getIsbn())!= -1){
            ListeDocument.get(getDocumentByIsbn(d.getIsbn())).AjouterExemplaires(1);
            return true;
        }
        else{
            
                ListeDocument.add(d);
                return true;
            }     
        }      
    
    boolean AjouterAdherent(Adherent a){
        
        if( (a instanceof Etudiant) && getEtudiantByCne(((Etudiant)a).getCne()) == -1){
                ListeAdherent.add((Etudiant)a);
                return true;
            }         
        else if ( (a instanceof Professeur) && getProfesseurByCin(((Professeur)a).getCin())== -1){
             ListeAdherent.add((Professeur)a);
                return true;
        }
        else
            return false;
            
    }
    
      char[] password(){
          
          char[] password = new char[8];
          Random R = new Random();
          
          for (int i = 0; i < 8; i++) {
              
              password[i] = (char)R.nextInt(128);
          }
          
            return  password;
      }
    
    
    Adherent Inscription(boolean type){
        
        Adherent Adh = null;
        Scanner Sc =new Scanner(System.in);
        System.out.println("Donner le nom");
        String Nom = Sc.nextLine();
        
        System.out.println("Donner le prenom");
        String Prenom = Sc.nextLine();
        
        System.out.println("Saisir ton age");
        int age = Sc.nextInt();
        // lire les informations
        // nom, prenom, age
        // definir son password
        
        if(type){
            
         System.out.println("Donner le cin");
         String cin = Sc.next();
         
        System.out.println("Donner la matiere");
        String matier = Sc.next();
        
            Adh = new Professeur(cin,matier,Nom,Prenom,age);
        }
        
        else{

         System.out.println("Donner le cne");
         String cne = Sc.next();
         
        System.out.println("Donner la filier");
        String filiere = Sc.next();
        
          Adh = new Etudiant(cne,filiere,Nom,Prenom,age);
    }
       
            
            AjouterAdherent(Adh);
                
            
        
        
        return Adh;
                
             
    }
    
    
    boolean Authentification(boolean type){
       
    
            boolean test = false;

        Scanner Sc = new Scanner(System.in);
       
    if(type){  // professeur type = true
        
        System.out.println("Donner le cin");
        String Cin = Sc.next();
        System.out.println("Donner le password");
        String password = Sc.next();  
        
        if(getProfesseurByCin(Cin) != -1){
            
           // if(ListeAdherent.get(getProfesseurByCin(Cin)).getPassword().equals(password)){
                
              //  return ListeAdherent.get(getProfesseurByCin(Cin));
                test = true;
            //}      
        }    
         
              
  }
    
    else{  // etudiant
                
        System.out.println("Donner le cne");
        String Cne = Sc.next();
        System.out.println("Donner le password");
        String password = Sc.next();  
        
        if(getEtudiantByCne(Cne) != -1){
            
          //   if(ListeAdherent.get(getEtudiantByCne(Cne)).getPassword().equals(password)){
                 
                   //return ListeAdherent.get(getEtudiantByCne(Cne));
                   test = true;
          //   }
            
            
        }    
         
                        
     }  
                
  
        return test;
  
 }    
    
  
    static int Menu(){
 
            
            int choix;
        Scanner SC = new Scanner(System.in);
        
        do{
                    System.out.println("**********  Taper 1, Se desabonner                             **********");
                    System.out.println("**********  Taper 2, Changer mot de passe                      **********");
                    System.out.println("**********  Taper 3, Emprunter un document                     **********");
                    System.out.println("**********  Taper 4, Rendre un document                        **********");
                    System.out.println("**********  Taper 5, Ajouter un document                       **********");
                    System.out.println("**********  Taper 6, Supprimer un document                     **********");
                    System.out.println("**********  Taper 7, Chercher document                         **********");
                    System.out.println("**********  Taper 8, Afficher les documents et les adherents   **********");
                    System.out.println("**********  Taper 0, Se deconnecter                            **********");
            
                        System.out.println("=====>Saisir votre choix : ");
                        choix = SC.nextInt();    
                                              
    	}while(choix < 0 ||  choix > 8);
    	
    	
           return choix;
        
        
        
    }
    
    
 static int Menu_Document(){
    	int choix;
    	Scanner Sc =new Scanner(System.in);
    	do{
    		  System.out.println("**********  Taper 1, Recherche par Isbn     **********");
    		  System.out.println("**********  Taper 2, Recherche par Titre   **********");
    		  System.out.println("**********  Taper 3, Recherche par Editeur **********");	  
    		  System.out.println("**********  Taper 0, Revenir en Arriere    **********");
                  
                        System.out.println("=====>Saisir votre choix : ");
                        choix = Sc.nextInt(); 
                  
    	}while(choix < 0 || choix > 3);
    	
    	return choix;
    }
    
    static int MenuPrincipal(){
    	int choix;
    	Scanner Sc = new Scanner(System.in);
    	do{
  		  System.out.println("**********  Taper 1, Pour s'authentifier  **********  ");
  		  System.out.println("**********  Taper 2, Pour s'inscrire      ********** ");
                  
                        System.out.println("=====>Saisir votre choix : ");
                        choix = Sc.nextInt();
                        
  	    }while(choix < 1 || choix > 2);
    	
    	return choix;
    }


 
  public static void main(String[] args){
         
        Bibliotheque2 Biblio = new Bibliotheque2();
Connection_BD db=new Connection_BD();
        int choix, choix2, choix3; 
        Adherent Adh = null;
        boolean test = false;
        String NewMp;
        String chaine;
        int val;
        Scanner sc = new Scanner(System.in);
        
        do{
           
             choix = MenuPrincipal();
             
             switch(choix){
                 
                case 1:      // Authentification
                     
                     if(Biblio == null){
                         System.out.println("la bibliotheque est vide");
                     }
                     else{
                         
                    System.out.println("Si vous etes un Professeur  Taper 1");
                    System.out.println("Si vous etes un Etudiant    Taper 2");
                    
                     val = sc.nextInt();                         
                         
                  if(val == 1){
                      test = Biblio.Authentification(true);
                  }
                  else if(val == 2) {
                      
                      test = Biblio.Authentification(false);
                  }                       
                     
                  if(test == false){
                      
    	            System.out.println("\n password incorrect ou compte inexistent");
    	            break;                      
                  }
                  
                  else{
                    
                     do{
                       choix2 = Menu();
                      
                      switch(choix2){
                          
                          case 1:  // supprimer un Adherent
                                   Biblio.ListeAdherent.remove(Adh);
	                           System.out.println("\n L'adherent est supprime avec succes !!");                              
                                   break;
                                   
                          case 2:    // changement de mot passe
                              
                                   System.out.println("\n Saisir le nouveau mot de passe : ");
	    	                   NewMp=sc.next();
	    	                   Adh.setPassword(NewMp.toCharArray());
	    	                   break;
                                   
                                   
                          case 3:   // Emprunter un document
                                                            
                                 System.out.print("\n Saisir l'isbn du document : ");
	    	                 chaine=sc.next();
	    	                           
	                            if(Biblio.getDocumentByIsbn(chaine) != -1){
                              
                                        test= Adh.emprunter(chaine);
	    	                        		 
	    	                    }
	    	                       if(test=false){
                                               
	    	                      	   System.out.println("\n Cet isb " + chaine + " est incorrect ");
	    	                       }
                                       else{
                                           
                                           System.out.println("\n le document est emprunter");
                                       }
	    	                        break;
                                        
                          case 4:  // Rendre un document
                                                            
                                 System.out.print("\n Saisir l'isbn du document : ");
	    	                 chaine=sc.next();
	    	                           
	                            if(Biblio.getDocumentByIsbn(chaine) != -1){
                              
                                        test= Adh.rendre(chaine);
	    	                        		 
	    	                    }
	    	                       if(test=false){
                                               
	    	                      	   System.out.println("\n Cet isb " + chaine + " est incorrect");
	    	                       }
	    	                        break;                              
                              
                              
                          case 5:   // Ajouter un document 
                                String[] auteurs;
                            
                                System.out.println("\n Saisir le titre");
                                String titre = sc.next();
                       
                                System.out.println("\n Saisir l'editeur");
                                String editeur = sc.next();
                                
                                System.out.println("\n Saisir l'annee de l'edition");
                                int edition = sc.nextInt();                                
                                
                                System.out.println("\n Saisir le nombre d'exemplaire");
                                int annee = sc.nextInt();

                                System.out.println("\n Saisir le ISBN");
                                String isbn = sc.next();
                                
                                System.out.println("\n Saisir le nombre des auteur");
                                int N = sc.nextInt();
                                
                                auteurs = new String[N];
                                
                                for (int i = 0; i < N; i++) {
                                    
                                System.out.println("\n Saisir l'auteur " + (i+1));
                                auteurs[i] = sc.next();
                              }
                                
                                Document Doc = new Document(titre,auteurs,editeur,edition,annee,isbn);
                                 
                                    if(Biblio.AjouterDocument(Doc)){
                              
                                        System.out.println("\n l'ajout a ete efectuer avec succee");
                                    }
                                    else{
                                        System.out.println("\n l'ajout est echouer");
                                    }
                                
                                   
                          case 6:   // supprimer un document

                                System.out.println("\n Saisir le titre");
                                chaine = sc.next();

                                     if(Biblio.SupprimerDocument(chaine)){
                                        
                                         System.out.println("\n le document a ete supprimer");
                                
                                     }                             
                                     else{
                                         
                                         System.out.println("\n la suppression a ete echouer");
                                     }
                                     
                          case 7: // Chercher un document
                              
                              do{
                                 
                                 choix3 = Menu_Document();
                              
                            
                                
                                switch(choix3){
                                  
	    	                        case 1: 
                                                  System.out.print("\n Saisir le ISBN :");
	    	                                   chaine = sc.next();
	    	                           if(Biblio.getDocumentByIsbn(chaine) != -1){
                                               
                                                   System.out.println(Biblio.getDocumentByIsbn(chaine));
	    	                        		 
	    	                           }
                                           
                                           else{
                                               
                                               System.out.println("\n Ce document " + chaine + " n'existe pas" );
                                           
                                           }
	    	                                    break;		  
	    	                                  
                                                    
	    	                        case 2: 
                                               
                                               System.out.println("\n Saisir le Titre du document :");
	    	                                      chaine=sc.next();
	    	                           if(Biblio.getDocumentByTitre(chaine) != -1){
                                               
                                                   System.out.println(Biblio.getDocumentByTitre(chaine));
	    	                        		 
	    	                           }
                                           
                                           else{
                                               
                                               System.out.println("\n Ce document " + chaine + " n'existe pas" );
                                           
                                           }
                                           
                                              break;
                                              
                                           
	    	                        case 3: 
                                               
                                               System.out.println("\n Saisir le nom de l'editeur");
	    	                                   chaine=sc.next();
                                                   
                                                       for(int i=0; i<Biblio.ListeDocument.size(); i++){
                                                         if(Biblio.ListeDocument.get(i).getEditeur().equals(chaine)){
                                                             
                                                            System.out.println("\n la liste de document par editeur %s sont :"+ chaine);
                                                            
                                                                 Biblio.getDocumentByEditeur(chaine);
                                                                 
                                                      }
                                                     }    
                              
                                               System.out.println("\n Ce document " + chaine + " n'existe pas" );
                                                      break;
                                                      
                                                  
                                                      
	    	                        case 4: 
                                               
                                               System.out.println("\n Saisir le nom de l'auteur");
	    	                                   chaine=sc.next();
                                                   
                                                       for(int i=0; i<Biblio.ListeDocument.size(); i++){
                                                         if(Biblio.ListeDocument.get(i).getAuteur().equals(chaine)){
                                                             
                                                            System.out.println("\n la liste de document par l'auteur %s sont :"+ chaine);
                                                            
                                                                 Biblio.getDocumentByAuteur(chaine);
                                                                 
                                                      }
                                                     }    
                              
                                               System.out.println("\n Ce document " + chaine +" n'existe pas" );
                                                      break;                                       
                                           
                              } 
                                
                              if (choix == 0){
                    
                                    System.out.println("\n Voullez vous vraiment quitter le programme si oui Taper 0 sinon une autre valeur");
                                     choix = sc.nextInt();                
                             }                                
                                
                         
                              }while(choix3 != 0);

                     
                        case 8:   // affichage les adherents et les documents de la bibliotheque Biblio
                                            
                            System.out.println(Biblio);       // appelle implicite de la methode toString()                              
                              
                    }           
                                 
                      }while(choix2 != 0);
                      
                }      
                               
                               
              } // fin if (Biblio == null)
                  
                     break;
            
            case 2:  // Inscription
                
                    System.out.println("Bienvenu !!!");
                    System.out.println("Si vous etes un Professeur  Taper 1");
                    System.out.println("Si vous etes un Etudiant    Taper 2");
    	            
		    int lire =sc.nextInt();                
                
                 if(lire == 1){
                     
                    Adh = Biblio.Inscription(true);   // professeur
                 }
                 
                 else{
                     
                     Adh = Biblio.Inscription(false);  // Etudiant
                 }
              
                 if(Adh != null ){
                      System.out.println("\n Inscription a ete efuctuer avec succes");
                      System.out.println("\n Votre mot de passe \n"+ Adh.getPassword());
                 }
                 else{
                     System.out.println("\n Inscription a ete echouer");
                 }
                 
                 break;
              
        }// fin switch1
             
                if (choix == 0){
                    
                    System.out.println("\nVoullez vous vraiment quitter le programme si oui Taper 0 sinon une autre valeur");
                    choix = sc.nextInt();                
                }
           
       }while(choix !=0);
       
        
    

    
    
  }

}
