import org.apache.flink.api.common.state.ValueState
import org.apache.flink.api.common.state.ValueStateDescriptor
import org.apache.flink.api.common.typeinfo.Types
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector
import org.apache.flink.walkthrough.common.entity.Alert
import org.apache.flink.walkthrough.common.entity.Transaction

class FraudDetector : KeyedProcessFunction<Long?, Transaction?, Alert?>() {
    @Transient
    private var flagState: ValueState<Boolean>? = null

    @Transient
    private var timerState: ValueState<Long>? = null
    override fun open(parameters: Configuration) {
        val flagDescriptor = ValueStateDescriptor(
                "flag",
                Types.BOOLEAN)
        flagState = runtimeContext.getState(flagDescriptor)
        val timerDescriptor = ValueStateDescriptor(
                "timer-state",
                Types.LONG)
        timerState = runtimeContext.getState(timerDescriptor)
    }

    @Throws(Exception::class)
    override fun processElement(transaction: Transaction?, context: Context, collector: Collector<Alert?>?) {

        // Get the current state for the current key
        val lastTransactionWasSmall = flagState!!.value()

        // Check if the flag is set
        if (lastTransactionWasSmall != null) {
            if (transaction!!.amount > LARGE_AMOUNT) {
                //Output an alert downstream
                val alert = Alert()
                alert.id = transaction.accountId
                collector!!.collect(alert)
            }
            // Clean up our state
            cleanUp(context)
        }
        if (transaction!!.amount < SMALL_AMOUNT) {
            // set the flag to true
            flagState!!.update(true)
            val timer = context.timerService().currentProcessingTime() + ONE_MINUTE
            context.timerService().registerProcessingTimeTimer(timer)
            timerState!!.update(timer)
        }
    }

    override fun onTimer(timestamp: Long, ctx: OnTimerContext?, out: Collector<Alert?>?) {
        // remove flag after 1 minute
        timerState!!.clear()
        flagState!!.clear()
    }

    private fun cleanUp(ctx: Context) {
        // delete timer
        val timer = timerState!!.value()
        ctx.timerService().deleteProcessingTimeTimer(timer)

        // clean up all state
        timerState!!.clear()
        flagState!!.clear()
    }

    companion object {
        private const val serialVersionUID = 1L
        private const val SMALL_AMOUNT = 1.00
        private const val LARGE_AMOUNT = 500.00
        private const val ONE_MINUTE = 60 * 1000.toLong()
    }
}