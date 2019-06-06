import java.util.Comparator;


/**
 *  La clase <tt>Transaccion</tt> es un tipo de dato inmutable para encapsular
 *  una transacción comercial con el nombre del cliente, fecha, y cantidad.
 */
public class Transaccion implements Comparable<Transaccion> {
    private final String  quien;      // cliente
    private final Fecha   cuando;     // fecha
    private final double  monto;   // monto


    /**
     * Inicializa una nueva transacción con los argumentos dados.
     *
     * @param  quien la persona involucrada en la transacción
     * @param  cuando la fecha de la transacción
     * @param  monto la cantidad de esta transacción
     * @throws IllegalArgumentException si <tt>monto</tt> 
     *         es <tt>Double.NaN</tt>, <tt>Double.POSITIVE_INFINITY</tt>,
     *         o <tt>Double.NEGATIVE_INFINITY</tt>
     */
    public Transaccion(String quien, Fecha cuando, double monto) {
        if (Double.isNaN(monto) || Double.isInfinite(monto))
            throw new IllegalArgumentException("Monto no puede ser NaN or infinito");
        this.quien    = quien;
        this.cuando   = cuando;
        if (monto == 0.0) this.monto = 0.0;  // para manejar -0.0
        else              this.monto = monto;
    }

    /**
     * Inicializa una nueva transacción analizando una cadena NOMBRE FECHA CANTIDAD.
     *
     * @param  transaccion la cadena a analizar gramaticalmente
     * @throws IllegalArgumentException si <tt>monto</tt> 
     *         es <tt>Double.NaN</tt>, <tt>Double.POSITIVE_INFINITY</tt>,
     *         o <tt>Double.NEGATIVE_INFINITY</tt>
     */
    public Transaccion(String transaccion) {
        String[] a = transaccion.split("\\s+");
        quien    = a[0];
        cuando   = new Fecha(a[1]);
        double valor = Double.parseDouble(a[2]);
        if (valor == 0.0) monto = 0.0;  // convertir -0.0 a 0.0
        else              monto = valor;
        if (Double.isNaN(monto) || Double.isInfinite(monto))
            throw new IllegalArgumentException("Monto no puede ser NaN or infinito");
    }

    /**
     * Devuelve el nombre del cliente involucrado en esta transacción.
     *
     * @return el nombre del cliente involucrado en esta tranasacción
     */
    public String quien() {
        return quien;
    }
 
    /**
     * Devuelve la fecha de esta transacción.
     *
     * @return la fecha de esta transacción
     */
    public Fecha cuando() {
        return cuando;
    }
 
    /**
     * Returns la cantidad de esta transacción.
     *
     * @return la cantidad de esta transacción
     */
    public double monto() {
        return monto;
    }

    /**
     * Devuelve una representación de cadena de esta transacción.
     *
     * @return una representación de cadena de esta transacción
     */
    @Override
    public String toString() {
        return String.format("%-10s %10s %8.2f", quien, cuando, monto);
    }

    /**
     * Compara dos transacciones por monto.
     *
     * @param  otra la otra transacción
     * @return { un entero negativo, cero, un entero positivo}, dependiendo
         si el monto de esta transacción es { menor que,
         igual a, más qrande que } el monto de la otra transaccion
     */
    public int compareTo(Transaccion otra) {
        if      (this.monto < otra.monto) return -1;
        else if (this.monto > otra.monto) return +1;
        else                              return  0;
    }    

    /**
     * Compara esta transacción al objeto especificado.
     *
     * @param  otra la otra transaccion
     * @return true si la transacción es igual a <tt>otra</tt>; false en caso contrario
     */
    @Override
    public boolean equals(Object otra) {
        if (otra == this) return true;
        if (otra == null) return false;
        if (otra.getClass() != this.getClass()) return false;
        Transaccion laOtra = (Transaccion) otra;
        return (this.monto == laOtra.monto) && 
                (this.quien.equals(laOtra.quien)) && 
                (this.cuando.equals(laOtra.cuando));
    }


    /**
     * Devuelve un código hash para esta transacción.
     *
     * @return un código hash para esta transacción
     */
    public int hashCode() {
        int hash = 17;
        hash = 31*hash + quien.hashCode();
        hash = 31*hash + cuando.hashCode();
        hash = 31*hash + ((Double) monto).hashCode();
        return hash;
    }

    /**
     * Compara dos transacciones por nombre del cliente.
     */
    public static class OrdenPorCliente implements Comparator<Transaccion> {

        @Override
        public int compare(Transaccion v, Transaccion w) {
            return v.quien.compareTo(w.quien);
        }
    }

    /**
     * Compara dos transacciones por Fecha.
     */
    public static class OrdenPorFecha implements Comparator<Transaccion> {

        @Override
        public int compare(Transaccion v, Transaccion w) {
            return v.cuando.compareTo(w.cuando);
        }
    }

    /**
     * Compara dos transacciones por monto.
     */
    public static class OrdenPorMonto implements Comparator<Transaccion> {

        @Override
        public int compare(Transaccion v, Transaccion w) {
            if      (v.monto < w.monto) return -1;
            else if (v.monto > w.monto) return +1;
            else                          return  0;
        }
    }
}
