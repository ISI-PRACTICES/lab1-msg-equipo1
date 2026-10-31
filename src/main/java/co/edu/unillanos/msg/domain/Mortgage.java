package co.edu.unillanos.msg.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Hipoteca otorgada por la MSG Foundation a una pareja.
 * Rol responsable: Hipotecas.
 */
public final class Mortgage {

    private static final BigDecimal WEEKS_PER_YEAR = new BigDecimal("52");
    private static final BigDecimal MAX_COUPLE_CONTRIBUTION_PERCENTAGE = new BigDecimal("0.28");
    private static final BigDecimal ZERO = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_EVEN);

    

    private final String mortgageNumber;
    private final BigDecimal weeklyPrincipalAndInterest;
    private final BigDecimal annualPropertyTax;
    private final BigDecimal annualInsurancePremium;
    private final BigDecimal coupleWeeklyIncome;

    // Decisión D2 (abierta): ¿el subsidio y el aporte de la pareja se guardan como
    // atributos al construir la hipoteca, o se calculan cada vez que se piden?

    public Mortgage(String mortgageNumber, BigDecimal weeklyPrincipalAndInterest,
                    BigDecimal annualPropertyTax, BigDecimal annualInsurancePremium,
                    BigDecimal coupleWeeklyIncome) {
        this.mortgageNumber = Objects.requireNonNull(mortgageNumber);
        this.weeklyPrincipalAndInterest = Objects.requireNonNull(weeklyPrincipalAndInterest);
        this.annualPropertyTax = Objects.requireNonNull(annualPropertyTax);
        this.annualInsurancePremium = Objects.requireNonNull(annualInsurancePremium);
        this.coupleWeeklyIncome = Objects.requireNonNull(coupleWeeklyIncome);
        // TODO (R6): rechazar con IllegalArgumentException un ingreso semanal
        //            de la pareja menor o igual a cero.
        if (coupleWeeklyIncome.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El ingreso semanal no puede ser negativo");
        }
    }

    public String mortgageNumber() {
        return mortgageNumber;
    }

    /** RN2: costo semanal de impuesto predial y seguro. Ejemplo resuelto. */
    public BigDecimal weeklyEscrow() {
        return annualPropertyTax.add(annualInsurancePremium)
                .divide(WEEKS_PER_YEAR, 2, RoundingMode.HALF_EVEN);
    }

    /** RN3: capital e intereses más el costo semanal de impuesto y seguro. */
    public BigDecimal totalWeeklyPayment() {
        return weeklyPrincipalAndInterest.add(weeklyEscrow());
    }

    // RN4: La pareja aporta como máximo el 28 % de su ingreso semanal bruto.
    public BigDecimal maxCoupleContribution() {
        return coupleWeeklyIncome.multiply(MAX_COUPLE_CONTRIBUTION_PERCENTAGE)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    // RN5: El subsidio semanal de la fundación es la cuota total menos el máximo que aporta la pareja; si da negativo, es cero.
    public BigDecimal weeklyGrant() {
        BigDecimal grant = totalWeeklyPayment().subtract(maxCoupleContribution());
        if (grant.compareTo(BigDecimal.ZERO) < 0) {
            return ZERO;
        }
        return grant.setScale(2, RoundingMode.HALF_EVEN);
    }

    // Aporte real de la pareja: cuota total menos el subsidio
    public BigDecimal weeklyCoupleContribution() {
        BigDecimal contribution = totalWeeklyPayment().subtract(weeklyGrant());
        return contribution.setScale(2, RoundingMode.HALF_EVEN);
    }
}
