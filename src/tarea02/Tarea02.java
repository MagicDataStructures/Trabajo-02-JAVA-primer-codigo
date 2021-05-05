package tarea02;
import java.util.Scanner;
import java.util.*;
public class Tarea02 {
    public static void main(String[] args) {
        System.out.println("Aquí comienza el ordenamiento de contenedores, se comienza con un total de 988 contenedores");
        System.out.println("Principalmente los 988 contenedores están identificados por un serial de la siguiente manera: ");
        System.out.println("El dígito de la pila donde está ubicado menos uno (0,197) y su ubicación en la pila menos uno (0,4), Por ejemplo:");
        
        Scanner entrada = new Scanner(System.in);
        LinkedList<String> contenedores = new LinkedList<String>();
        
        LinkedList<String> Pilaregistro = new LinkedList<String>();
        
        LinkedList<Integer> ConPilas = new LinkedList<Integer>();
        LinkedList<Integer> PilaVacia = new LinkedList<Integer>();
        LinkedList<Integer> PilaCupo = new LinkedList<Integer>();
        
        List<List<String>> Con = new ArrayList<List<String>>();
        
        /* Asignados los datos para la cola de vehiculos*/
        for(int k = 0 ; k  < 200 ; k++){
            Con.add(new ArrayList<String>());
        }
        for(int i = 0 ; i < 198 ; i++){
            for(int j = 0 ; j < 5; j++){
                Con.get(i).add(""+i+j);
                Pilaregistro.add(""+i+j);
                ConPilas.add(i+1);
            }
        }
        Con.get(197).remove(4);
        Con.get(197).remove(3);
        
        Pilaregistro.remove(989);
        Pilaregistro.remove(988);
        
        for(int i = 0 ; i < 12 ; i++){
            Pilaregistro.add("");
        }
        for(int i = 0; i< 10 ; i++){
            if(i < 5){
                ConPilas.add(199);
            }
            else{
                ConPilas.add(200);
            }
        }
        
        
        PilaVacia.add(200);
        PilaVacia.add(199);
        PilaCupo.add(198);
        
        System.out.println("Contenedores pila 1: "+ Con.get(0));
        System.out.println("Contenedores pila 50: "+ Con.get(49));
        System.out.println("Contenedores pila 150: "+ Con.get(149));

        
        while(true){
            System.out.println("Qué función desea realizar ?, Mover, Entregar o Agregar");
            String x = entrada.next();
            if(x.equals("Mover") || x.equals("mover") || x.equals("MOVER")){
                System.out.println("Dijite el codigo del contenedor que desee mover");
                String Pila_code = entrada.next();
                int z = Pilaregistro.indexOf(Pila_code);
                while(z == -1){
                    System.out.println("Este codigo no está registrado, ingrese un codigo registrado");
                    Pila_code = entrada.next();
                    z = Pilaregistro.indexOf(Pila_code);
                
                }
                int PilaPasada = ConPilas.get(z);
                
                int pila_i_time = Con.get(PilaPasada - 1).indexOf(Pila_code);
                int pila_i_tama = Con.get(PilaPasada - 1).size();
                
                System.out.println("El el contenedor con el codigo "+ Pila_code + " está en la pila  "+ PilaPasada);
                
                System.out.println("A que pila desea mover el contenedor ? ");
                int PilaFutura = entrada.nextInt();
                while(PilaFutura >200 || PilaFutura < 1){
                    System.out.println("Esta pila no existe, dijite una en el intervalo de 1 a 200");
                    PilaFutura = entrada.nextInt();
                }
                int TamPilaFutura = Con.get(PilaFutura-1).size();
                while(TamPilaFutura == 5){
                    System.out.println("Esta pila esta completa, debe usar otra. Las pilass vacías son: "+PilaVacia+" Las pilas con cupo son: "+PilaCupo);
                    PilaFutura = entrada.nextInt();
                    TamPilaFutura = Con.get(PilaFutura-1).size();
                }
                
                Con.get(PilaFutura-1).add(Pila_code);
                Con.get(PilaPasada -1).remove(Pila_code);
                

                Pilaregistro.remove(Pila_code);

                
                int pila_llegada = ConPilas.indexOf(PilaFutura) + pila_i_time ;
                Pilaregistro.add(pila_llegada,Pila_code);

                
                
                System.out.println("Pila anterior "+ Con.get(PilaPasada-1));
                System.out.println("Pila Actual  "+ Con.get(PilaFutura -1));
                
                if(Con.get(PilaPasada-1).isEmpty()){
                    PilaVacia.add(PilaPasada);
                }
                else if(Con.get(PilaPasada-1).size() < 5 && Con.get(PilaPasada-1).size() >0 ){
                    if(PilaCupo.indexOf(PilaPasada) == -1){
                        PilaCupo.add(PilaPasada);
                    }
                }
                
                if(Con.get(PilaFutura-1).size() == 5){
                    PilaCupo.remove(PilaCupo.indexOf(PilaFutura));
                }
                else if(Con.get(PilaFutura-1).size() < 5 && Con.get(PilaFutura-1).size() >0){
                    if(PilaCupo.indexOf(PilaFutura) == -1){
                        PilaCupo.add(PilaFutura);
                    }
                    if(Con.get(PilaFutura-1).size() == 1){
                        PilaVacia.remove(PilaVacia.indexOf(PilaFutura));
                    }                    
                }
                
                if(PilaCupo.indexOf(PilaPasada)!= -1 && PilaVacia.indexOf(PilaPasada) != -1){
                    PilaCupo.remove(PilaCupo.indexOf(PilaPasada));
                }
                
                /* Tiempo de movención */
                System.out.println("El contenedor fue movido con éxito, los demás contenedores que se tuvieron que mover fueron indexados otra vez a la pila");
                int tiempo_mover_pila = 60 + (pila_i_tama -1 - pila_i_time)*(60);
                System.out.println("El tiempo que se demoro en mover el contenedor "+Pila_code+" a la pila  "+PilaFutura+" Es :" + tiempo_mover_pila+" Segundos ");
                
                 
                
                /* Entrega */
            }
            else if(x.equals("Entregar") || x.equals("entregar") || x.equals("ENTREGAR")){
                System.out.println("Dijite el código del contenedor que desea entregar");
                String Pila_code = entrada.next();
                int z = Pilaregistro.indexOf(Pila_code);
                while(z == -1){
                    System.out.println("Este codigo no está registrado, ingrese un codigo registrado");
                    Pila_code = entrada.next();
                    z = Pilaregistro.indexOf(Pila_code);                
                }

                int PilaPasada = ConPilas.get(z);
                int pila_i_time = Con.get(PilaPasada - 1).indexOf(Pila_code);
                int pila_i_tama = Con.get(PilaPasada - 1).size();
                System.out.println("El el contenedor con el codigo "+ Pila_code + " está en la pila  "+ PilaPasada);
                Con.get(PilaPasada -1).remove(Pila_code);
                
                Pilaregistro.remove(Pila_code);
                Pilaregistro.add(z,"");
                
                System.out.println("Pila anterior "+ Con.get(PilaPasada-1));
                
                if(Con.get(PilaPasada-1).isEmpty()){
                    PilaVacia.add(PilaPasada);
                }                
                else if(Con.get(PilaPasada-1).size() == 4){
                    PilaCupo.add(PilaPasada);
                }

                
                
                
                System.out.println("El contenedor con el codigo  "+ Pila_code + "  fue entregado con éxito, los demás contenedores que se tuvieron que mover fueron indexados otra vez a la pila");
                int tiempo_entregar_pila = 180 + (pila_i_tama -1 - pila_i_time)*(60);
                System.out.println("El tiempo que se demoro en entregar el contenedor "+Pila_code+" Es de :" + tiempo_entregar_pila+" Segundos ");
                
                
                
            }
            
            /* Agregar */
            
            else if(x.equals("Agregar") || x.equals("agregar") || x.equals("AGREGAR")){
                int totalC = 0;
                int Total = 1000;
                for(int i =0 ; i < 1000 ; i++){
                    String condata = Pilaregistro.get(i);
                    if(condata.equals("")){
                        totalC = totalC + 1;
                    }
                }
                Total = Total - totalC;
                System.out.println("Total de contenedores " + Total);
                if(Total == 990){
                    System.out.println("¡ CUIDADO ! , YA ESTÁ EN EL LÍMITE DE CONTENEDORES DISPONIBLES 990");
                }
                else{
                    System.out.println("Dijite el código del contenedor que desea agregar");
                    String Pila_code = entrada.next();
                    int z = Pilaregistro.indexOf(Pila_code);                    
                    while(z != -1){                        
                        System.out.println("Este codigo YA está registrado, ingrese un codigo  NUEVO");
                        Pila_code = entrada.next();
                        z = Pilaregistro.indexOf(Pila_code);                
                    }

                    System.out.println("En que pila lo desea agregar ?, Pilas vacías:  "+PilaVacia+"  Pilas con Cupo: "+PilaCupo);
                    int PilaFutura = entrada.nextInt();
                    while(PilaFutura >200 || PilaFutura < 1){                        
                        System.out.println("Esta pila no existe, dijite una en el intervalo de 1 a 200");
                        PilaFutura = entrada.nextInt();
                    }
                    int TamPilaFutura = Con.get(PilaFutura-1).size();
                    while(TamPilaFutura == 5){                        
                        System.out.println("Esta pila esta completa, debe usar otra. Las pilass vacías son: "+PilaVacia+" Las pilas con cupo son: "+PilaCupo);
                        PilaFutura = entrada.nextInt();
                        TamPilaFutura = Con.get(PilaFutura-1).size();
                    } 
                    
                    Con.get(PilaFutura-1).add(Pila_code);
                    int pila_i_time = Con.get(PilaFutura - 1).indexOf(Pila_code);
                    int pila_llegada = ConPilas.indexOf(PilaFutura) + pila_i_time ;

                    Pilaregistro.remove(pila_llegada);
                    Pilaregistro.add(pila_llegada,Pila_code);
                                       
                    System.out.println("Pila Actual  "+ Con.get(PilaFutura -1));
                    
                    if(Con.get(PilaFutura-1).size() == 5){
                        PilaCupo.remove(PilaCupo.indexOf(PilaFutura));
                    }
                    else if(Con.get(PilaFutura-1).size() == 1){
                        PilaVacia.remove(PilaVacia.indexOf(PilaFutura));
                        PilaCupo.add(PilaFutura);
                    }
                     
                    System.out.println("El apilamiento del contenedor con el código "+ Pila_code + " A la pila "+PilaFutura+ " Fue un éxito y demoró 180 Segundos");
                }
            }
            
            System.out.println("Desea saber el número de contenedores en el puerto hasta el momento ?, Contestar Si o No");            
            int totalC = 0;
            int Total = 1000;
            for(int i =0 ; i < 1000 ; i++){
               
                String condata = Pilaregistro.get(i);
                if(condata.equals("")){
                    totalC = totalC + 1;
                }
            }
            Total = Total - totalC;            
            String pregunta1 = entrada.next();
            
            if(pregunta1.equals("Si")   || pregunta1.equals("si") || pregunta1.equals("SI")){
                System.out.println("El número total de contenedores es: "+Total);
            }
            
            System.out.println("Desea acceder a la información de alguna pila de contenedores ?, en caso de que SI quiera, Conteste Si o No");
            String pregunta2 = entrada.next();
            if(pregunta2.equals("Si") || pregunta2.equals("SI") || pregunta2.equals("si")){
                System.out.println("Dijite el número de la pila que quiere consultar, el numero debe ser de uno a docientos (1,200)");
                int pre2 = entrada.nextInt();
                while(pre2 > 200 || pre2 < 1){
                    System.out.println("Solo se permiten numeros de 1 a 200");
                    pre2 = entrada.nextInt();
                }
                System.out.println("Los contenedores en la pila "+ pre2 +" son "+Con.get(pre2-1));
            }
            
            System.out.println("Desea terminar con el proceso de ordenamiento de los contenedores y pasar al de los automoviles ?, Conteste Sí o No");
            String pregunta3 = entrada.next();
            if(pregunta3.equals("Si")||pregunta3.equals("SI") ||pregunta3.equals("si")){
                break;
            }
            
        }    
    }    
}
