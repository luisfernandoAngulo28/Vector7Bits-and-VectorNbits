/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDeNegocio;

/**
 *
 * @author ferna
 */
public class VectorNBits {
       //Atributos
    int V[];
   int cantidad;
   int CantidadBits;
   //constructor           //10
   public VectorNBits(int numeroElementos,int cantidadDeBits){
       int Numerobits=numeroElementos*cantidadDeBits;//CantidadBits0
       int NumeroEntero=Numerobits/32;//2
       if((Numerobits%32)!=0){
           NumeroEntero++;
       }
       V=new int[NumeroEntero];
       cantidad=numeroElementos;
       this.CantidadBits=cantidadDeBits;
   }
   
   
   
           //                   81        5
   public void insertar(int elemento,int posicion){
       if (posicion<=cantidad) {
           int ele1=elemento;
           int mask=(int) (Math.pow(2, CantidadBits)-1);//12CantidadBits=1111111
           int NumeroBits=calcularNumeroBits(posicion);//14//28
           int NumeroEntero=calcularNumeroDeEntero(posicion);//0
           mask=mask<<NumeroBits;
           mask=~mask;
          /// System.out.println(mask+"="+Integer.toBinaryString(mask));
           V[NumeroEntero]=mask&V[NumeroEntero];
          
           elemento=elemento<<NumeroBits;
           V[NumeroEntero]=V[NumeroEntero]|elemento;
            //System.out.println(Integer.toBinaryString(V[0]));
            
            if ((NumeroBits+CantidadBits)>32) {
              int mask1=(int) (Math.pow(2, CantidadBits)-1);//12CantidadBits
              mask1=mask1>>>32-NumeroBits;//32-28=4
               mask1=~mask1;
               V[NumeroEntero+1]=V[NumeroEntero+1]&mask1;//limpiamos
               ele1=ele1>>>(32-NumeroBits);
               V[NumeroEntero+1]=V[NumeroEntero+1]|ele1;
           }
       }
     //  System.out.println(Integer.toBinaryString(V[0]));
      // System.out.println(Integer.toBinaryString(V[1]));
   }

    private int calcularNumeroBits(int posicion) {
       return ((posicion-1)*CantidadBits)%32;
    }

    private int calcularNumeroDeEntero(int posicion) {
        return ((posicion-1)*CantidadBits)/32;//0
    }
    
    public int sacar(int posicion){
           int mask=(int) (Math.pow(2, CantidadBits)-1);//12CantidadBits=1111111
           int NumeroBits=calcularNumeroBits(posicion);//14//28
           int NumeroEntero=calcularNumeroDeEntero(posicion);//0
           mask=mask<<NumeroBits;
           mask=mask&V[NumeroEntero];
           mask=mask>>>NumeroBits;
           if ((NumeroBits+CantidadBits)>32) {
              int mask1=(int) (Math.pow(2, CantidadBits)-1);//12CantidadBits
              mask1=mask1>>>32-NumeroBits;//32-28=4
              mask1=mask1&V[NumeroEntero+1];
              mask1=mask1<<(32-NumeroBits);
              mask=mask|mask1;
           }
           return mask;
    }
    
    
    
    public String toString(){
        String S="V=[";
        for (int i = 1; i <=cantidad; i++) {
            S=S+sacar(i)+",";
        }
        S=S.substring(0, S.length()-1);
        return S+"]";
    }
    public static void main(String[] args) {
        VectorNBits A=new VectorNBits(10,7);
        A.insertar(10, 3);
        A.insertar(81, 5);
        System.out.println(A.sacar(5));
        System.out.println(A.toString());
    
    }
}
