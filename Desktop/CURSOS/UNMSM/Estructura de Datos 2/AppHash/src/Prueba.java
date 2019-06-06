
import java.util.Arrays;

public class Prueba {
    public static void main(String[] args) {

        
        Directorio[] d1 = new Directorio[4];
        
        
        d1[0] = new Directorio("Pedro 609-258-4455  17/6/1990  644.08");
        d1[1] = new Directorio("Jose 609-258-4455 b 26/3/2002 4121.85");
        d1[2] = new Directorio("Carlos  609-258-4455  14/6/1999  288.34");
        d1[3] = new Directorio("Abel 609-258-4455 22/8/2007 2678.40");

        System.out.println("Desordenado");
        for (int i = 0; i < d1.length; i++)
            System.out.println(d1[i]);
        System.out.println();
        
        System.out.println("Ordenado por Fecha");
        Arrays.sort(d1, new Directorio.OrdenPorFecha());
        for (int i = 0; i < d1.length; i++)
            System.out.println(d1[i]);
        System.out.println();

        System.out.println("Ordenado por Cliente");
        Arrays.sort(d1, new Directorio.OrdenPorCliente());
        for (int i = 0; i < d1.length; i++)
            System.out.println(d1[i]);
        System.out.println();

        System.out.println("Ordenado por Monto");
        Arrays.sort(d1, new Directorio.OrdenPorMonto());
        for (int i = 0; i < d1.length; i++)
            System.out.println(d1[i]);
        System.out.println();
    }    
}
