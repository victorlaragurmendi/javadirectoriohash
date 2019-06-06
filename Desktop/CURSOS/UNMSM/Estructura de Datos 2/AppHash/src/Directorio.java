import java.util.Comparator;


/**
 *  La clase <tt>Transaccion</tt> es un tipo de dato inmutable para encapsular
 *  una transacción comercial con el nombre del cliente, fecha, y cantidad.
 */
public class Directorio implements Comparable<Directorio> {
    private final String  nombre;      // nombre
  //  private final String  apellido;      // 
    private final Fecha   fecha_cumple;     // fecha
    private final NumeroTelefono telf;      //telefono
    private final double  monto;   // monto


    public Directorio(String quien, Fecha cuando,NumeroTelefono telefono, double monto) {
        if (Double.isNaN(monto) || Double.isInfinite(monto))
            throw new IllegalArgumentException("Monto no puede ser NaN or infinito");
        this.nombre    = quien;
        this.fecha_cumple   = cuando;
        this.telf = telefono;
        if (monto == 0.0) this.monto = 0.0;  // para manejar -0.0
        else              this.monto = monto;
    }


    public Directorio(String transaccion) {
        String[] a = transaccion.split("\\s+");
        nombre    = a[0];
        fecha_cumple   = new Fecha(a[1]);
        telf = new NumeroTelefono(a[2]);
        double valor = Double.parseDouble(a[3]);
        if (valor == 0.0) monto = 0.0;  // convertir -0.0 a 0.0
        else              monto = valor;
        if (Double.isNaN(monto) || Double.isInfinite(monto))
            throw new IllegalArgumentException("Monto no puede ser NaN or infinito");
    }


    public String nombre() {
        return nombre;
    }

    public Fecha fecha_cumple() {
        return fecha_cumple;
    }

    public NumeroTelefono telf() {
        return telf;
    }

    public double monto() {
        return monto;
    }


    @Override
    public String toString() {
        return String.format("%-10s %10s %8.2f", nombre, telf, fecha_cumple, monto);
    }


    public int compareTo(Directorio otra) {
        if      (this.monto < otra.monto) return -1;
        else if (this.monto > otra.monto) return +1;
        else                              return  0;
    }    


    @Override
    public boolean equals(Object otra) {
        if (otra == this) return true;
        if (otra == null) return false;
        if (otra.getClass() != this.getClass()) return false;
        Directorio laOtra = (Directorio) otra;
        return (this.monto == laOtra.monto) && 
                (this.nombre.equals(laOtra.nombre)) && 
                (this.fecha_cumple.equals(laOtra.fecha_cumple));
    }



    public int hashCode() {
        int hash = 17;
        hash = 31*hash + nombre.hashCode();
        hash = 31*hash + fecha_cumple.hashCode();
        hash = 31*hash + ((Double) monto).hashCode();
        return hash;
    }


    public static class OrdenPorCliente implements Comparator<Directorio> {

        @Override
        public int compare(Directorio v, Directorio w) {
            return v.nombre.compareTo(w.nombre);
        }
    }

    public static class OrdenPorFecha implements Comparator<Directorio> {

        @Override
        public int compare(Directorio v, Directorio w) {
            return v.fecha_cumple.compareTo(w.fecha_cumple);
        }
    }

    /**
     * Compara dos transacciones por monto.
     */
    public static class OrdenPorMonto implements Comparator<Directorio> {

        @Override
        public int compare(Directorio v, Directorio w) {
            if      (v.monto < w.monto) return -1;
            else if (v.monto > w.monto) return +1;
            else                          return  0;
        }
    }
}
