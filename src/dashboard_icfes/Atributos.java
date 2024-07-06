/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dashboard_icfes;

/**
 *
 * @author santi
 */
public class Atributos {
    int Lcritica, Cnaturales, Matematicas, Csociales, Ingles, PGlobal, PerGlobal, año; //puntaje
    int Per_Lcritica, Per_Cnaturales, Per_Matematicas, Per_Sociales, Per_Ingles; //percentil
    
    String Genero, Estrato, Residencia, Naturaleza, nivel_ingles;
    
    public Atributos(){
        Lcritica = Cnaturales = Matematicas = Csociales = Ingles = PGlobal = PerGlobal = año = 0;
         Per_Lcritica = Per_Cnaturales = Per_Matematicas =Per_Sociales = Per_Ingles = 0;
         Genero = Residencia = Estrato = Naturaleza = nivel_ingles = "-";
    }

    public Atributos(String Genero, String Residencia, String Estrato, String Naturaleza, 
            int Lcritica, int Per_Lcritica, int Matematicas, int Per_Matematicas, int Cnaturales, int Per_Cnaturales, 
            int Csociales, int Per_Sociales, int Ingles, int Per_Ingles, String nivel_ingles, int PGlobal, int PerGlobal, int año) {
        this.Lcritica = Lcritica;
        this.Cnaturales = Cnaturales;
        this.Matematicas = Matematicas;
        this.Csociales = Csociales;
        this.Ingles = Ingles;
        this.PGlobal = PGlobal;
        this.PerGlobal = PerGlobal;
        this.año = año;
        this.Per_Lcritica = Per_Lcritica;
        this.Per_Cnaturales = Per_Cnaturales;
        this.Per_Matematicas = Per_Matematicas;
        this.Per_Sociales = Per_Sociales;
        this.Per_Ingles = Per_Ingles;
        this.Genero = Genero;
        this.Estrato = Estrato;
        this.Residencia = Residencia;
        this.Naturaleza = Naturaleza;
        this.nivel_ingles = nivel_ingles;
    }

    public int getLcritica() {
        return Lcritica;
    }

    public int getCnaturales() {
        return Cnaturales;
    }

    public int getMatematicas() {
        return Matematicas;
    }

    public int getCsociales() {
        return Csociales;
    }

    public int getIngles() {
        return Ingles;
    }

    public int getPGlobal() {
        return PGlobal;
    }

    public int getPerGlobal() {
        return PerGlobal;
    }

    public int getAño() {
        return año;
    }

    public int getPer_Lcritica() {
        return Per_Lcritica;
    }

    public int getPer_Cnaturales() {
        return Per_Cnaturales;
    }

    public int getPer_Matematicas() {
        return Per_Matematicas;
    }

    public int getPer_Sociales() {
        return Per_Sociales;
    }

    public int getPer_Ingles() {
        return Per_Ingles;
    }

    

    public String getGenero() {
        return Genero;
    }

    public String getEstrato() {
        return Estrato;
    }

    public String getResidencia() {
        return Residencia;
    }

    public String getNaturaleza() {
        return Naturaleza;
    }

    public String getNivel_ingles() {
        return nivel_ingles;
    }
    
    

   

    

}
    