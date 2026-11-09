Bueno, te explico, mi proyecto se supone que responde a este enunciado:La Martha Stockton Greengage (MSG) Foundation otorga hipotecas a parejas jóvenes y cubre con su propio dinero la parte de la cuota que la pareja no alcanza a pagar. Cada semana necesita saber cuánto dinero tendrá disponible para nuevas hipotecas. Ese cálculo es el caso de uso de este laboratorio: Estimar fondos disponibles para la semana, junto con Registrar hipoteca, que lo alimenta.
Toma en cuenta las siguientes reglas de trabajo:
RN1 Cada hipoteca tiene un pago semanal de capital e intereses, un impuesto predial anual, un seguro anual y el ingreso semanal bruto de la pareja.
RN2	El costo semanal de impuesto y seguro es (impuesto anual + seguro anual) / 52.
RN3	La cuota semanal total es el pago de capital e intereses más el costo semanal de impuesto y seguro.
RN4	La pareja aporta como máximo el 28 % de su ingreso semanal bruto.
RN5	El subsidio semanal de la fundación es la cuota total menos el máximo que aporta la pareja; si da negativo, es cero.
RN6	Cada inversión de la fundación tiene un rendimiento anual esperado; su rendimiento semanal es ese valor / 52.
RN7	Los gastos operativos anuales de la fundación se distribuyen igual entre 52 semanas.
RN8	La fundación paga el impuesto y el seguro de todas las hipotecas.
Quiero que integres la funcionalidad que me diste, en este Main.java del proyecto:
package co.edu.unillanos.msg;

/**
 * Raíz de composición: el único lugar que conoce todas las capas y las conecta.
 * Rol responsable: Registro.
 *
 * En el Laboratorio 2 esta clase se reemplaza por la aplicación JavaFX.
 *
 * Se ejecuta con: mvn -q compile exec:java
 */
public final class Main {

    public static void main(String[] args) {
        // TODO:
        // 1. Crear los tres repositorios en memoria. Los gastos operativos
        //    anuales del ejemplo son 39000.00.
        // 2. Registrar M-001 y M-002 con RegisterMortgage, usando los datos
        //    del ejemplo de verificación del enunciado.
        // 3. Guardar las inversiones I-01 (52000.00) e I-02 (26000.00).
        // 4. Ejecutar EstimateFundsForWeek e imprimir el resultado.
        //    Esperado después de la integración: 1160.00
        throw new UnsupportedOperationException("TODO");
    }
}

