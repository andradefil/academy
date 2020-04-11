import org.apache.flink.api.java.functions.KeySelector
import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.walkthrough.common.entity.Alert
import org.apache.flink.walkthrough.common.entity.Transaction
import org.apache.flink.walkthrough.common.sink.AlertSink
import org.apache.flink.walkthrough.common.source.TransactionSource

fun main(args: Array<String>) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment()
    val transactions: DataStream<Transaction> = env
            .addSource(TransactionSource())
            .name("transactions")

    /**
     * Limitation in flink discuss {@link <a href="https://discuss.kotlinlang.org/t/cannot-use-lambdas-with-apache-flink/2306"Forum discussion</a>
     */
    val keySelector = KeySelector<Transaction, Long> { transaction -> transaction.accountId }

    val alerts: SingleOutputStreamOperator<Alert?>? = transactions
            .keyBy(keySelector)
            .process(FraudDetector())
            .name("fraud-detector")

    alerts?.addSink(AlertSink())?.name("send-alerts")
    env.execute("Fraud Detection")
}