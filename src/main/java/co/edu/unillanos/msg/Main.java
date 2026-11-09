package co.edu.unillanos.msg;

import co.edu.unillanos.msg.domain.Mortgage;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== MSG Foundation - Sistema de Gestión de Hipotecas ===");

        // Datos de ejemplo para una pareja solicitante
        String numeroHipoteca = "HIP-2026-001";
        BigDecimal capitalEInteresSemanal = new BigDecimal("150.00"); // P&I semanal
        BigDecimal impuestoPredialAnual = new BigDecimal("1200.00");  // Tax anual
        BigDecimal seguroAnual = new BigDecimal("600.00");            // Insurance anual
        BigDecimal ingresoSemanalPareja = new BigDecimal("500.00");   // Ingreso semanal

        try {
            // Creación de la instancia de la hipoteca
            Mortgage hipoteca = new Mortgage(
                numeroHipoteca,
                capitalEInteresSemanal,
                impuestoPredialAnual,
                seguroAnual,
                ingresoSemanalPareja
            );

            // Imprimir reporte financiero
            System.out.println("Número de Hipoteca: " + hipoteca.mortgageNumber());
            System.out.println("--------------------------------------------------");
            System.out.println("Costo semanal de Impuesto/Seguro (Escrow): $" + hipoteca.weeklyEscrow());
            System.out.println("Pago semanal total requerido:              $" + hipoteca.totalWeeklyPayment());
            System.out.println("Aporte máximo posible de la pareja (28%):   $" + hipoteca.maxCoupleContribution());
            System.out.println("--------------------------------------------------");
            System.out.println("Subsidio semanal concedido por la Fundación: $" + hipoteca.weeklyGrant());
            System.out.println("Aporte semanal neto a pagar por la pareja:   $" + hipoteca.weeklyCoupleContribution());

        } catch (IllegalArgumentException e) {
            System.err.println("Error al registrar la hipoteca: " + e.getMessage());
        }
    }
}
