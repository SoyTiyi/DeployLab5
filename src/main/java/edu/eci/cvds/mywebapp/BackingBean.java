package edu.eci.cvds.mywebapp;

import java.util.ArrayList;
import java.util.Random;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "calculadoraBean")
@ApplicationScoped
public class BackingBean {
    private int numeroAdivinar;
    private ArrayList<Integer> valores;
    private int premioAcumulado;
    private String estado;
    private double media;
    private double desviacionEstandar;
    private double varianza;
    private int moda;
    private ArrayList<ArrayList<Integer>> intentos;


    /* This is the contructor of the class */
    public BackingBean(){
        restart();
    }

    public ArrayList<ArrayList<Integer>> getIntentos(){
        return intentos;
    }

    public void setIntentos(ArrayList<ArrayList<Integer>> intentos){
        this.intentos=intentos;
    }

    public double getMedia(){
        return media;
    }

    public double getDesviacionEstandar(){
        return desviacionEstandar;
    }

    public double getVarianza(){
        return varianza;
    }

    public int getModa(){
        return moda;
    }

    public void setMedia(double media){
        this.media=media;
    }

    public void setDesviacionEstandar(double desviacionEstandar){
        this.desviacionEstandar=desviacionEstandar;
    }

    public void setVarianza(double varianza){
        this.varianza=varianza;
    }

    public void setModa(int moda){
        this.moda=moda;
    }

    /* This method set numeroAdivinar, is the number that the client have to know 
     * @param numeroAdivinar is a Integer
    */
    public void setNumeroAdivinar(int numeroAdivinar){
        this.numeroAdivinar=numeroAdivinar;
    }

    /* This method set Intentos, is the number of  releases in the game
     * @param intentos is a ArrayList of Integers
    */
    public void setValores(ArrayList<Integer> valores){
        this.valores=valores;
    }

    /* This method set premioAcumulado, is the value that the client have in the game
     * @param premioAcumulado is a Integer
    */
    public void setPremioAcumulado(int premioAcumulado){
        this.premioAcumulado=premioAcumulado;
    }

    /* This method set estado
     * @param estado is a String
    */
    public void setEstado(String estado){
        this.estado=estado;
    }

    /* This method return numeroAdivinar
     * @return numeroAdivianar
    */
    public int getNumeroAdivinar(){
        return numeroAdivinar;
    }

    /* This method return the arrayList with the releases that the player did
     * @return intentos
    */
    public ArrayList<Integer> getValores(){
        return valores;
    }

    /* This method return premioAcumulado
     * @retunr premioAcumulado
    */
    public int getPremioAcumulado(){
        return premioAcumulado;
    }

    /* This method return estado    
     * @return estado 
    */
    public String getEstado(){
        return estado;
    }
    
    public void calculate(String lista){
        valores = new ArrayList<Integer>();
        lista+=";";
        String valor="";
        for(int i=0;i<lista.length();i++){
            if(lista.charAt(i)!=';'){
                valor += lista.charAt(i)+"";
            }
            else {
                valores.add(Integer.parseInt(valor));
                valor="";
            }
        }
        intentos.add(valores);
        media=calculateMean(valores);
        desviacionEstandar=calculateStandarDesviation(valores);
        varianza=calculateVariance(valores);
        moda=calculateMode(valores);
    }

    public double calculateMean(ArrayList<Integer> valores){
        int result=0;
        for(int i=0; i<valores.size(); i++){
            result+=valores.get(i);
        }
        return (double) result/valores.size();
    }

    public double calculateStandarDesviation(ArrayList<Integer> valores){
        double varianza = calculateVariance(valores);
        double respuesta = Math.pow(varianza, 0.5);
        return respuesta;
    }

    public double calculateVariance(ArrayList<Integer> valores) {
        double promedio = calculateMean(valores);
        double sumatoria=0.0;
        for(int i=0;i<valores.size();i++){
            double x = valores.get(i)-promedio;
            sumatoria+= (double) Math.pow(x, 2);
        }
        double respuesta = sumatoria/(valores.size()); 
        return respuesta;
    }

    public void restart(){
        valores= new ArrayList<>();
        numeroAdivinar = createNumeroAdivinar();
        media=0;
        desviacionEstandar=0;
        varianza=0;
        moda=0;
        intentos = new ArrayList<ArrayList<Integer>>();
    }

    private int createNumeroAdivinar(){
        Random random = new Random();
        int nuevoNumeroAdivinar = random.nextInt(10);
        return nuevoNumeroAdivinar;
    }

    public int calculateMode(ArrayList<Integer> valores){
        int maximoNumRepeticiones = 0;
        int moda = 0;
        for(int i=0; i<valores.size(); i++){
            int numRepeticiones = 0;
            for(int j=0; j<valores.size(); j++){
                if(valores.get(i)==valores.get(j)){
                    numRepeticiones++;
                }
                if(numRepeticiones>maximoNumRepeticiones){
                    moda = valores.get(i);
                    maximoNumRepeticiones = numRepeticiones;
                }
            }
        }
        return moda;
    }
    
}
