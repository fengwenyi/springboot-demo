//package com.fengwenyi.demo.springboot.shardingjdbc.algorithm;
//
//import com.fengwenyi.javalib.convert.DateTimeUtils;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
//
//import java.util.Collection;
//import java.util.Date;
//
///**
// * @author <a href="https://fengwenyi.com">Erwin Feng</a>
// * @since 2024-01-18
// */
//public class LogTablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
//    @Override
//    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
//
//        Date value = preciseShardingValue.getValue();
//        return "t_log_" + DateTimeUtils.format(value, "yyyyMM");
//    }
//}
