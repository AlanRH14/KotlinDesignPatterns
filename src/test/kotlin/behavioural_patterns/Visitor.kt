package behavioural_patterns

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

interface ReportElement {
    fun <R> accept(visitor: ReportVisitor<R>): R
}

class FixedPriceContract(val costPerYear: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(contract = this)
}

class TimeAndMaterialsContract(val costPerHour: Long, val hour: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(contract = this)
}

class SupportContract(val costPerMonth: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(contract = this)
}

interface ReportVisitor<out R> {
    fun visit(contract: FixedPriceContract): R
    fun visit(contract: TimeAndMaterialsContract): R
    fun visit(contract: SupportContract): R
}

class MonthlyCostReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract): Long = contract.costPerYear / 12

    override fun visit(contract: TimeAndMaterialsContract): Long = contract.costPerHour * contract.hour

    override fun visit(contract: SupportContract): Long = contract.costPerMonth
}

class YearlyCostReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract): Long = contract.costPerYear

    override fun visit(contract: TimeAndMaterialsContract): Long = contract.costPerHour * contract.hour

    override fun visit(contract: SupportContract): Long = contract.costPerMonth * 12
}

class Visitor {
    @Test
    fun visitorTest() {
        val projectAlpha = FixedPriceContract(costPerYear = 10_000)
        val projectBeta = SupportContract(costPerMonth = 500)
        val projectGamma = TimeAndMaterialsContract(costPerHour = 150, hour = 10)
        val projectKappa = TimeAndMaterialsContract(costPerHour = 50, hour = 50)

        val project = arrayListOf(
            projectAlpha,
            projectBeta,
            projectGamma,
            projectKappa
        )

        val monthlyCostVisitor = MonthlyCostReportVisitor()
        val monthlyCost = project.map { it.accept(monthlyCostVisitor) }.sum()
        println("Monthly cost: $monthlyCost")
        assertEquals(
            actual = monthlyCost,
            expected = 5333
        )

        val yearlyCostVisitor = YearlyCostReportVisitor()
        val yearlyCost = project.map { it.accept(yearlyCostVisitor) }.sum()
        println("Yearly cost: $yearlyCost")
        assertEquals(
            actual = yearlyCost,
            expected = 20_000
        )
    }
}