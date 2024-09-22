public class Punto {

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

        int [][] Sallah = new int [filas][columnas];
        
        int mitadC = (int) (Math.ceil(M[0].length/2));
        int mitadF = (int) (Math.ceil(M.length/2));

        boolean [][] In = new boolean [mitadF + 1][columnas] ;
        boolean [][] Ma = new boolean [mitadF + 1][columnas];

        int kd = mitadC; 
        int ki = mitadC;

        int[][][] dp = new int[mitadF+1][columnas][columnas];

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

        

        //Bloquear casillas que no son posibles
        for (int i = 0; i <= (Math.ceil(filas/2)); i++) {
            for (int IndianaCol = 0; IndianaCol < columnas; IndianaCol++) {
                for (int MarionCol = 0; MarionCol < columnas; MarionCol++) {
                    
                    if(M[i][MarionCol] != -1){
                        
                        if (MarionCol >= columnas-i-1){
                            Ma[i][MarionCol]= true;

                            
                        }

                        if(i != 0){
                        if (MarionCol == 0 ){
                            Ma[i][MarionCol]= Ma[i-1][MarionCol] || Ma[i-1][MarionCol+1];
                        }

                        else if (MarionCol == columnas-1 ){
                            Ma[i][MarionCol]= Ma[i-1][MarionCol] || Ma[i-1][MarionCol-1];
                        }

                        else  { Ma[i][MarionCol]= Ma[i-1][MarionCol] || Ma[i-1][MarionCol-1] || Ma[i-1][MarionCol+1];}}}

                    //Indiana

                    if (M[i][IndianaCol] != -1){
                        
                
                    if (IndianaCol <= i){
                        In[i][IndianaCol]= true;
                    }
                    
                    if(i != 0){

                         if (IndianaCol == 0 ){
                        In[i][IndianaCol]= In[i-1][IndianaCol] || In[i-1][IndianaCol+1];
                    }

                    else if (IndianaCol == columnas-1){
                        In[i][IndianaCol]= In[i-1][IndianaCol] || In[i-1][IndianaCol-1];
                    }

                    else {In[i][IndianaCol]= In[i-1][IndianaCol] || In[i-1][IndianaCol-1] || In[i-1][IndianaCol+1];}}}



                    if (IndianaCol > i && MarionCol < columnas-i-1 ){
                        dp[i][IndianaCol][MarionCol] = Integer.MIN_VALUE;
                    }

                    if (M[i][IndianaCol] == -1 && M[i][MarionCol] == -1 )
                    {
                        dp[i][IndianaCol][MarionCol] = Integer.MIN_VALUE;
                    }
            }}}

        // Inicializar la fila de la mitad(base de la solución)
        for (int IndianaCol = 0; IndianaCol <columnas; IndianaCol++) {
            for (int MarionCol = 0; MarionCol < columnas; MarionCol++) {
                int maxTesoros = 0;
                for (int SallahCol = 0 ; SallahCol < columnas; SallahCol++){
                    int tesoros =0;

                    int i = M[mitadF][IndianaCol];
                    int m = M[mitadF][MarionCol];
                    int s = Sallah[mitadF][SallahCol];
                    
                    if (!In[mitadF][IndianaCol] ){
                        i = 0;
                    }

                    if (!Ma[mitadF][MarionCol] ){
                        m = 0;
                    }

                    if (M[mitadF][SallahCol] == -1){
                        s = 0;
                    }

                if ( dp[mitadF ][IndianaCol][MarionCol] != Integer.MIN_VALUE){
                    if (IndianaCol == MarionCol && MarionCol == SallahCol) {
                        dp[mitadF ][IndianaCol][MarionCol] = s;}
                    
                    else if (IndianaCol == MarionCol){
                        tesoros= s + Math.max(m,i);
                    }

                    else if (IndianaCol == SallahCol){
                        tesoros = s + m;
                    }

                    else if (SallahCol == MarionCol){
                        tesoros = s + i;
                    }
                    
                    else { tesoros= s + i + m;}
                }

                if (tesoros > maxTesoros){
                    maxTesoros = tesoros;
                }
            }

                dp[mitadF ][IndianaCol][MarionCol] = maxTesoros;
        }}

        
       // Rellenar la tabla DP desde la penúltima fila hasta la primera
for (int i = mitadF - 1; i >= 0; i--) {
    for (int IndianaCol = 0; IndianaCol < columnas; IndianaCol++) {
        for (int MarionCol = 0; MarionCol < columnas; MarionCol++) {

            if(Ma[i][MarionCol] ){
                        
               if(i != 0){
                if (MarionCol == 0 ){
                    Ma[i][MarionCol]= Ma[i+1][MarionCol] || Ma[i+1][MarionCol+1];
                }

                else if (MarionCol == columnas-1 ){
                    Ma[i][MarionCol]= Ma[i+1][MarionCol] || Ma[i+1][MarionCol-1];
                }

                else  { Ma[i][MarionCol]= Ma[i+1][MarionCol] || Ma[i+1][MarionCol-1] || Ma[i+1][MarionCol+1];}}}
                

            //Indiana

                if (In[i][IndianaCol] ){
                    
            
                if (IndianaCol <= i){
                    In[i][IndianaCol]= true;
                }
                
                else if(i != 0){

                    if (IndianaCol == 0 ){
                    In[i][IndianaCol]= In[i+1][IndianaCol] || In[i-1][IndianaCol+1];
                }

                else if (IndianaCol == columnas-1){
                    In[i][IndianaCol]= In[i+1][IndianaCol] || In[i+1][IndianaCol-1];
                }

                else {In[i][IndianaCol]= In[i+1][IndianaCol] || In[i+1][IndianaCol-1] || In[i+1][IndianaCol+1];}}}

            // Revisar si la celda actual no está bloqueada para alguno de los dos
            if (In[i][IndianaCol] || Ma[i][MarionCol] ) {
                int maxTesoros = Integer.MIN_VALUE;

                // Considerar todas las combinaciones de movimientos para ambos robots
                for (int dIndiana = -1; dIndiana <= 1; dIndiana++) {
                    for (int dMarion = -1; dMarion <= 1; dMarion++) {
                        int newIndianaCol = IndianaCol + dIndiana;
                        int newMarionCol = MarionCol + dMarion;

                        // Asegurarse de que ambos permanezcan dentro de los límites de la matriz
                        if (newIndianaCol >= 0 && newIndianaCol < columnas && newMarionCol >= 0 && newMarionCol < columnas) {
                            int tesoros = 0;

                            // Verificar si Indiana y Marion pueden alcanzar las celdas en la fila anterior
                            boolean indianaBlocked = In[i+1][newIndianaCol];
                            boolean marionBlocked = Ma[i+1][newMarionCol];

                            // Condiciones individuales para Indiana y Marion
                            if (indianaBlocked && marionBlocked) {
                                // Ambos pueden recoger tesoros
                                if (MarionCol == IndianaCol) {
                                    tesoros = M[i][IndianaCol]; // Mismo lugar, un solo robot recoge
                                } else {
                                    tesoros = M[i][IndianaCol] + M[i][MarionCol]; // Ambos recogen
                                }
                            } else if (indianaBlocked) {
                                // Solo Indiana puede recoger tesoros
                                tesoros = M[i][IndianaCol];
                            } else if (marionBlocked) {
                                // Solo Marion puede recoger tesoros
                                tesoros = M[i][MarionCol];
                            }

                            // Añadir los tesoros de la siguiente fila si ninguno está bloqueado
                            if (indianaBlocked && marionBlocked) {
                                tesoros += dp[i + 1][newIndianaCol][newMarionCol];
                            } else if (indianaBlocked) {
                                tesoros += dp[i + 1][newIndianaCol][MarionCol];
                            } else if (marionBlocked) {
                                tesoros += dp[i + 1][IndianaCol][newMarionCol];
                            }

                            // Actualizar el máximo de tesoros
                            maxTesoros = Math.max(maxTesoros, tesoros);
                        }
                    }
                }
                // Guardar el resultado máximo para la posición actual en dp
                dp[i][IndianaCol][MarionCol] = maxTesoros;
            } 
        }
    }
}

    System.out.println(dp[0][0][4]);




}

}
