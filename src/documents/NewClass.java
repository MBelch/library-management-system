/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documents;

/**
 *
 * @author Info
 */
public class NewClass {
      static int Menu(){
 
            
            int choix;
        Scanner SC = new Scanner(System.in);
        
        do{
                    System.out.println("**********  Taper 1, Se desabonner             **********");
                    System.out.println("**********  Taper 2, Changer mot de passe      **********");
                    System.out.println("**********  Taper 3, Rendre un document        **********");
                    System.out.println("**********  Taper 4, Chercher document         **********");
                    System.out.println("**********  Taper 0, Se deconnecter **********");
            
                        System.out.println("=====>Saisir votre choix : ");
                        choix = SC.nextInt();    
                                              
    	}while(choix > 0 || choix < 1 || choix > 4);
    	
    	
           return choix;
        
        
        
    }
    
    
    static int Menu_Document(){
    	int choix;
    	Scanner Sc =new Scanner(System.in);
    	do{
    		  System.out.println("**********  Taper 1, Recherche par Isn     **********");
    		  System.out.println("**********  Taper 2, Recherche par Titre   **********");
    		  System.out.println("**********  Taper 3, Recherche par Editeur **********");
    		  System.out.println("**********  Taper 4, Recherche par Auteur **********");                  
    		  System.out.println("**********  Taper 0, Revenir en Arriere    **********");
                  
                        System.out.println("=====>Saisir votre choix : ");
                        choix = Sc.nextInt(); 
                  
    	}while(choix > 0 || choix < 1 || choix > 4);
    	
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
                        
  	    }while(choix <1 || choix>2);
    	
    	return choix;
    }



  
     public static void main(String[] args){
         
 Bibliotheque2 Biblio = new Bibliotheque2();

       int choix; 
       Adherent Adh = null;
       boolean etat, test;
       String NewMp;
       String chaine;
       int val;
      
       
       while(true){
    	
            choix = MenuPrincipal();
            
    	   switch(choix){
    	    
               case 1 :   // pour s'authentifier
                  
                    System.out.println("Si vous etes un Professeur  Taper 1");
                    System.out.println("Si vous etes un Etudiant    Taper 2");
                    
                    Scanner Sca=new Scanner(System.in);
                     val = Sca.nextInt();
                  if(val == 1){
                      Adh = Biblio.Authentification();
                  }
                  else if(val == 2) {
                      
                       Adh = Biblio.Authentification();
                  }
                       // Supposons un professeur
    	              if(Adh == null){
    	            	  System.out.println("password incorrect ou compte inexistent");
    	            	  break;
    	              }
                      // sinon le mot de passe est correct
                      
    	              Scanner Sc=new Scanner(System.in);
                      
    	              while(choix != 0){
                          
    	            	  choix = Menu();
                          
	    	              switch(choix){
	    	                   case 1: 
                                       Biblio.ListeAdherent.remove(Adh);
	                                   System.out.println("L'adherent est supprime avec succes !!");
	                                   choix=0;
	                                   break;
	    	                   case 2: 
                                       System.out.println("Saisir le nouveau mot de passe : ");
	    	                           NewMp=Sc.next();
	    	                           Adh.setPassword(NewMp.toCharArray());
	    	                           break;
	    	                   case 3:
                                       
                                       System.out.print("Saisir l'isbn du document : ");
	    	                        chaine=Sc.next();
	    	                           
	    	                           if(Biblio.getDocumentByIsbn(chaine) != -1){
                                               
	    	                                   test= Adh.rendre(chaine);
	    	                        		 
	    	                           }
	    	                           if(test=false){
                                               
	    	                        	   System.out.println("Cet isb %s est incorrect "+ chaine);
	    	                           }
	    	                           break;
                                           
	    	                   case 4: 
                                       
                                       choix= Menu_Document();
                                       
	    	                           switch(choix){
                                               
	    	                              case 1: 
                                                  System.out.print("Saisir le ISBN :");
	    	                                      chaine = Sc.next();
	    	                           if(Biblio.getDocumentByIsbn(chaine) != -1){
                                               
                                                   System.out.println(Biblio.getDocumentByIsbn(chaine));
	    	                        		 
	    	                           }
                                           
                                           else{
                                               
                                               System.out.println("Ce document %s, n'existe pas" + chaine);
                                           
                                           }
	    	                                    break;		  
	    	                                      
	    	                                      
                                                      
	    	                           case 2: System.out.println("Saisir le Titre du document :");
	    	                                      chaine=Sc.next();
	    	                           if(Biblio.getDocumentByTitre(chaine) != -1){
                                               
                                                   System.out.println(Biblio.getDocumentByTitre(chaine));
	    	                        		 
	    	                           }
                                           
                                           else{
                                               
                                               System.out.println("Ce document %s, n'existe pas" + chaine);
                                           
                                           }
                                           
                                              break;
                                              
                                           
	    	                           case 3: 
                                               
                                               System.out.println("Saisir le nom de l'editeur");
	    	                                   chaine=Sc.next();
                                                   
                                                       for(int i=0; i<Biblio.ListeDocument.size(); i++){
                                                         if(Biblio.ListeDocument.get(i).getEditeur().equals(chaine)){
                                                             
                                                            System.out.println("la liste de document par editeur %s sont :"+ chaine);
                                                            
                                                                 Biblio.getDocumentByEditeur(chaine);
                                                                 
                                                      }
                                                     }    
                              
                                               System.out.println("Ce document %s, n'existe pas" + chaine);
                                                      break;
                                                      
                                                  
                                                      
	    	                           case 4: 
                                               
                                               System.out.println("Saisir le nom de l'auteur");
	    	                                   chaine=Sc.next();
                                                   
                                                       for(int i=0; i<Biblio.ListeDocument.size(); i++){
                                                         if(Biblio.ListeDocument.get(i).getAuteur().equals(chaine)){
                                                             
                                                            System.out.println("la liste de document par l'auteur %s sont :"+ chaine);
                                                            
                                                                 Biblio.getDocumentByAuteur(chaine);
                                                                 
                                                      }
                                                     }    
                              
                                               System.out.println("Ce document %s, n'existe pas" + chaine);
                                                      break; 
                                                      
	    	                              case 0: 
                                                  break;        
	    	                           }
                                           
	    	                           break;
	    	                   case 0: 
	    	                           break;
	    	              }
    	              } 
    	              break;
                      
                      
		case 2:  
                    
                    System.out.println("Bienvenu !!!");
                    System.out.println("Si vous etes un Professeur  Taper 1");
                    System.out.println("Si vous etes un Etudiant    Taper 2");
    	            
                    Scanner sc=new Scanner(System.in);
		    choix=sc.nextInt();
                   
                    /*
                     if(choix !=1 || choix !=2){
                    
                     do{
                        
                     System.out.println("vous etes trompu essayer encore une autre fois, sinon Taper 0 pour Anuler l'inscription!!!");
                     System.out.println("Si vous etes un Professeur  Taper 1");
                     System.out.println("Si vous etes un Etudiant    Taper 2");
    	            
		     choix=Sc.nextInt();                    
                         
                     }while(choix != 0 || choix !=1 || choix !=2);
                     
                  }  
                    */
                    
    	              if(choix==0){
                          
                          System.out.println("Vous etes en dehor de programme !!!!");
                      }
                          
                      else if(choix==1){
                          
                          Adh = Biblio.Inscription(true);
                          if(Adh != null){
                              System.out.println("Votre mot de passe \n"+ Adh.getPassword());  
                          }
                          else{
                             System.out.println("Inscrition echoue");
                          }
                         
                      }
                      
                      else{
                          Adh = Biblio.Inscription(false);
                          if(Adh != null){
                            System.out.println("Votre mot de passe \n"+ Adh.getPassword()); 
                          }
                          else{
                             System.out.println("Inscrition echoue");
                          }
                      }

                     
    	               break;
               }             
               
           //System.out.println(Biblio);
    	   }
       
              
    	   
       }
       
        
    }
    
}
