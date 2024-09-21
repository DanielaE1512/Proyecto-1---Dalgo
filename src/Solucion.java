public class Solucion {

    public static void main(String[] args) throws Exception {
        int[][] matriz = {
            {0, 9, 1, 10, 0} ,
            {-1, -1, 5, -1, 5},
            {1, 5, 1, 5, 7},
            {5, 5, 5, 15, 2},
            {55, 3, 0, 4, 1}
        };

        exploración(matriz);
    }

    public static void exploración(int [][]M) {

        int filas = M.length;
        int columnas = M[0].length;

        int [][] Indiana = new int [filas][columnas];
        Indiana[0][0] = 0;

        int [][] Marion = new int [filas][columnas];
        Marion[0][columnas -1]= 0;

        int [][] Sallah = new int [filas][columnas];
        
        int mitadC = (int) (Math.ceil(Indiana[0].length/2));
        int mitadF = (int) (Math.ceil(Indiana.length/2));

        for (int i = 0; i <= (Math.ceil(filas/2)); i++) {
            
           
            for (int j = 0; j < columnas; j++) {

                if (j > i ){
                    Indiana[i][j] = Integer.MIN_VALUE;;
                }

                if (j < filas-i-1 ){
                    Marion[i][j] = Integer.MIN_VALUE;;
                }


            }}

        
        // Maximo Indiana y Marion
        for (int i = 0; i <= (Math.ceil(filas/2)); i++) {
            for (int j = 0; j < columnas; j++) {


                boolean compartido = true;
                
                if (i == 0  ){
                    if (j == 0){
                    Indiana[i][j] = 0;}
                    if (j == columnas -1){
                        Marion[i][j] = 0;;}
                }

                else if (M[i][j]!= -1){
                
                //Maximo Indiana
                    //Extremos Indiana
                  if (i >= j){
                    if (j== 0 && Marion[i-1][j] == Integer.MIN_VALUE && Marion[i-1][j+1] == Integer.MIN_VALUE){
                            if (Math.max(Indiana[i-1][j],Indiana[i-1][j+1] )  == Integer.MIN_VALUE){
                                Indiana[i][j] =Integer.MIN_VALUE;
                            }else{
                            Indiana[i][j] = Math.max(Indiana[i-1][j],Indiana[i-1][j+1] ) + M [i][j];}
                            compartido = false;}
                    else if (j == columnas-1 && Marion[i-1][j] == Integer.MIN_VALUE && Marion[i-1][j-1] == Integer.MIN_VALUE){
                            if (Math.max(Indiana[i-1][j],Indiana[i-1][j-1]  ) == Integer.MIN_VALUE){
                                Indiana[i][j] =Integer.MIN_VALUE;
                            }else{
                                Math.max(Indiana[i-1][j],Indiana[i-1][j-1] + M [i][j]);}
                            compartido = false;
                        }
                    // Otras columna

                    else if (Marion[i-1][j] == Integer.MIN_VALUE && Marion[i-1][j-1] == Integer.MIN_VALUE && Marion[i-1][j+1] == Integer.MIN_VALUE) {
                        if (Math.max(Math.max(Indiana[i-1][j],Indiana[i-1][j+1]), Indiana[i-1][j-1] ) == Integer.MIN_VALUE){
                            Indiana[i][j] =Integer.MIN_VALUE;
                        }else{
                                Indiana[i][j] = Math.max(Math.max(Indiana[i-1][j],Indiana[i-1][j+1]), Indiana[i-1][j-1] ) + M [i][j];}
                    compartido = false;
                    }
                } else {compartido = false;}
                
                //Maximo Marion
                    //Extremos Marion
                  if ((filas-1) - i<= j){
                    if (j== 0 && Indiana[i-1][j] == Integer.MIN_VALUE && Indiana[i-1][j+1] == Integer.MIN_VALUE){
                        if (Math.max(Marion[i-1][j],Marion[i-1][j+1] )  == Integer.MIN_VALUE){
                            Marion[i][j] =Integer.MIN_VALUE;
                        }else{
                            Marion[i][j] = Marion[i][j] = Math.max(Marion[i-1][j],Marion[i-1][j+1] ) + M[i][j];}
                        compartido = false;}
                            
                            
                    else if (j == columnas-1 && Indiana[i-1][j] == Integer.MIN_VALUE && Indiana[i-1][j-1] == Integer.MIN_VALUE){
                        if (Math.max(Marion[i-1][j],Marion[i-1][j-1] )   == Integer.MIN_VALUE){
                            Marion[i][j] =Integer.MIN_VALUE;
                        }else{
                            Marion[i][j] = Math.max(Marion[i-1][j],Marion[i-1][j-1] ) + M[i][j];}
                            compartido = false;
                        }
                    // Otras columna

                    else if (Indiana[i-1][j] == Integer.MIN_VALUE && Indiana[i-1][j+1] == Integer.MIN_VALUE && Indiana[i-1][j-1] == Integer.MIN_VALUE){
                        if (Math.max(Math.max(Marion[i-1][j],Marion[i-1][j+1]), Marion[i-1][j-1]  )   == Integer.MIN_VALUE){
                            Marion[i][j] =Integer.MIN_VALUE;
                        }else{
                            Marion[i][j] = Math.max(Math.max(Marion[i-1][j],Marion[i-1][j+1]), Marion[i-1][j-1] ) + M[i][j];}
                        compartido = false;
                    }}
                
                
                //Celdas Compartidas
                if (compartido){
                int MaxMarion = Integer.MIN_VALUE;
                int MaxIndiana = Integer.MIN_VALUE;

                // Extremos 
                if (j == 0) {
                    // Extremo izquierdo
                    MaxIndiana = Math.max(Indiana[i-1][j+1], Indiana[i-1][j]);
                    
                    if (Marion[i-1][j+1] != Integer.MIN_VALUE) {
                        MaxMarion = Math.max(Marion[i-1][j+1],Marion[i-1][j] );
                    }
                } else if (j == columnas - 1) {
                    // Extremo derecho
                    if (Indiana[i-1][j-1] != Integer.MIN_VALUE) {
                        MaxIndiana = Math.max(Indiana[i-1][j], Indiana[i-1][j-1]);
                    }
                    MaxMarion = Math.max(Marion[i-1][j-1],Marion[i-1][j] );
                    }
                else {
                    // Tres posibilidades para columnas intermedias
                    if (Indiana[i-1][j-1] != Integer.MIN_VALUE || Indiana[i-1][j] != Integer.MIN_VALUE || Indiana[i-1][j+1] != Integer.MIN_VALUE) {
                        MaxIndiana = Math.max(Math.max(Indiana[i-1][j-1], Indiana[i-1][j]), Indiana[i-1][j+1]);
                    }
                    if (Marion[i-1][j-1] != Integer.MIN_VALUE || Marion[i-1][j] != Integer.MIN_VALUE || Marion[i-1][j+1] != Integer.MIN_VALUE) {
                        MaxMarion = Math.max(Math.max(Marion[i-1][j-1], Marion[i-1][j]), Marion[i-1][j+1]);
                    }
                }

                // Asignar los valores según los máximos encontrados
                if (MaxIndiana >= MaxMarion) {
                    Indiana[i][j] = MaxIndiana + M[i][j];
                    Marion[i][j] = MaxMarion;
                } else {
                    Indiana[i][j] = MaxIndiana;
                    Marion[i][j] = MaxMarion + M[i][j];
                }}}

                //Posición Actual Trampa
                else {Marion[i][j] = Integer.MIN_VALUE;
                        Indiana[i][j] = Integer.MIN_VALUE;}
                    
                }
            }
        
        
            
        int kd = mitadC; 
        int ki = mitadC;
            
        // Maximo Sallah
        for (int i = filas -1 ; i >= (Math.ceil(filas/2)) ; i--) {
            for (int j = 0; j < columnas; j++) {
                
                
                
                if (i == filas -1 && j!= mitadC){
                    Sallah[i][j] = Integer.MIN_VALUE;
                }
                else if ((j < ki || j> kd) ){
                    Sallah[i][j] = Integer.MIN_VALUE;
                }

                else if (i == filas -1 && j== mitadC){
                    Sallah[i][j] = 0;
                }


                else if (M[i][j]!= -1){

                if (j == 0){
                    if (Math.max(Sallah[i+1][j],Sallah[i+1][j+1]   )   == Integer.MIN_VALUE){
                        Sallah[i][j] =Integer.MIN_VALUE;
                    }else{
                        Sallah[i][j] = Math.max(Sallah[i+1][j],Sallah[i+1][j+1] ) + M[i][j];}
                    
                }

                else if (j == columnas -1){
                    if (Math.max(Sallah[i+1][j],Sallah[i+1][j-1] )  == Integer.MIN_VALUE){
                        Sallah[i][j] =Integer.MIN_VALUE;
                    }else{
                        Sallah[i][j] = Math.max(Sallah[i+1][j],Sallah[i+1][j-1] ) + M[i][j];}
                    
                }
                else {

                    if (Math.max(Math.max(Sallah[i+1][j],Sallah[i+1][j+1]), Sallah[i+1][j-1] )   == Integer.MIN_VALUE){
                        Sallah[i][j] =Integer.MIN_VALUE;
                    }else{
                        Sallah[i][j] = Math.max(Math.max(Sallah[i+1][j],Sallah[i+1][j+1]), Sallah[i+1][j-1] ) + M[i][j];}    
                }
            
                }

                else {Sallah[i][j]= Integer.MIN_VALUE;}

          
            }

            kd += 1;
            ki -= 1;
        }

        
        // Encontrar maximo

        int MaximoT = 0;

        for (int i = 0; i < columnas; i++){
            for (int j = 0; j < columnas; j++){
                for (int k = 0; k < columnas; k++){

                    int maximoL = 0;

                    if ( Indiana[mitadF][i] == Integer.MIN_VALUE && Sallah[mitadF][k] == Integer.MIN_VALUE){
                        maximoL =  Marion[mitadF][j];
                    }

                    else if ( Indiana[mitadF][i] == Integer.MIN_VALUE && Marion[mitadF][j] == Integer.MIN_VALUE){
                        maximoL =  Sallah[mitadF][k];
                    }

                    else if ( Sallah[mitadF][k]== Integer.MIN_VALUE && Marion[mitadF][j] == Integer.MIN_VALUE){
                        maximoL =  Indiana[mitadF][i];
                    }

                    if ( Indiana[mitadF][i] == Integer.MIN_VALUE){
                        maximoL = Marion[mitadF][j] + Sallah[mitadF][k];
                    }
                    else if ( Marion[mitadF][j] == Integer.MIN_VALUE){
                        maximoL = Indiana[mitadF][i] + Sallah[mitadF][k];
                    }

                    else if ( Sallah[mitadF][k] == Integer.MIN_VALUE){
                        maximoL = Indiana[mitadF][i] + Marion[mitadF][j];
                    }

                    else { maximoL = Indiana[mitadF][i] + Marion[mitadF][j] + Sallah[mitadF][k];}

                    
                    
                    if (i == k ){
                        if (Indiana[mitadF][i] > Marion[mitadF][j] ){
                            maximoL -= M[mitadF][k];}
                    }

                    if (j == k ){
                        if (Indiana[mitadF][i] < Marion[mitadF][j] ){
                            maximoL -= M[mitadF][k];}
                    }

                    if (j == k && k == i){
                       
                            maximoL -= M[mitadF][k];
                    }
                    

                    if (maximoL > MaximoT){
                        MaximoT = maximoL;
                    }


                }
            }


        
        }

        System.out.println("El maxímo de tesoros posibles es: " + MaximoT);




}
    
}
