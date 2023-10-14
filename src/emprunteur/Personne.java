
package emprunteur;


public abstract class Personne {
    final private String nom;
    final private String prenom;
    private int age;
    private char[] password;
    
    
    public Personne(String nom, String prenom, int age){
        this.nom=nom;
        this.prenom=prenom;
        this.age=age; 
        password = new char[8];
 
    }
    public String toString(){
        return  "nom=+"+nom+" prenom"+ prenom+ " age de "+
                age+" ans"; 
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    
    
}

