package co.edu.unillanos.msg.application.usecase;

import co.edu.unillanos.msg.application.port.InvestmentRepository;
import co.edu.unillanos.msg.application.port.MortgageRepository;
import co.edu.unillanos.msg.application.port.OperatingExpensesRepository;
import co.edu.unillanos.msg.domain.Investment;
import co.edu.unillanos.msg.domain.Mortgage;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Caso de uso: estimar los fondos disponibles para la semana.
 * Rol responsable: Integrador.
 *
 * Regla: esta clase no contiene ninguna fórmula de RN2 a RN7.
 * Le pide cada valor a la clase experta y solo suma.
 */
public final class EstimateFundsForWeek {

    private final MortgageRepository mortgages;
    private final InvestmentRepository investments;
    private final OperatingExpensesRepository expenses;

    public EstimateFundsForWeek(MortgageRepository mortgages,
                                InvestmentRepository investments,
                                OperatingExpensesRepository expenses) {
        this.mortgages = Objects.requireNonNull(mortgages);
        this.investments = Objects.requireNonNull(investments);
        this.expenses = Objects.requireNonNull(expenses);
    }

    public BigDecimal execute() {
        return totalInvestmentIncome()
                .add(totalCoupleContributions())
                .subtract(weeklyOperatingExpenses())
                .subtract(totalEscrowPaid());
    }

    /** Suma del rendimiento semanal de todas las inversiones. */
    private BigDecimal totalInvestmentIncome() {
        return investments.findAll().stream()
                .map(Investment::weeklyReturn)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /** Suma del aporte semanal de todas las parejas. */
    private BigDecimal totalCoupleContributions() {
        return mortgages.findAll().stream()
                .map(Mortgage::weeklyCoupleContribution)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /** Gasto operativo de la semana. */
    private BigDecimal weeklyOperatingExpenses() {
        return expenses.find().weeklyAmount();
    }

    /** Suma del impuesto y seguro semanal de todas las hipotecas (RN8). */
    private BigDecimal totalEscrowPaid() {
        return mortgages.findAll().stream()
                .map(Mortgage::weeklyEscrow)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}