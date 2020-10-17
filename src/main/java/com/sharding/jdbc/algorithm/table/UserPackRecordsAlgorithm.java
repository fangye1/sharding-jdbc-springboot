package com.sharding.jdbc.algorithm.table;

import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPackRecordsAlgorithm implements PreciseShardingAlgorithm<LocalDateTime>, RangeShardingAlgorithm<LocalDateTime> {
    /**
     * 通过年份获取真实表
     *
     * @param year 年份
     * @return 真实表
     */
    private String getTableByYear(Collection<String> availableTargetNames, String year) {
        for (String targetName : availableTargetNames) {
            if (targetName.endsWith(year)) {
                return targetName;
            }
        }
        return null;
    }

    /**
     * PreciseShardingAlgorithm 是必选的，用于处理=和IN的分片。
     *
     * @param availableTargetNames 可用的表集合
     * @param shardingValue        分片键
     * @return 真实表
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<LocalDateTime> shardingValue) {
        LocalDateTime startTime = shardingValue.getValue();
        return getTableByYear(availableTargetNames, String.valueOf(startTime.getYear()));
    }

    /**
     * RangeShardingAlgorithm 可选，用于处理BETWEEN AND, >, <, >=, <=分片
     *
     * @param availableTargetNames 可用的表集合
     * @param rangeShardingValue   分片键
     * @return 真实表
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<LocalDateTime> rangeShardingValue) {
        List<String> tables = new ArrayList<>();
        int lowerYear = rangeShardingValue.getValueRange().lowerEndpoint().toLocalDate().getYear();
        LocalDate startDate = LocalDate.of(lowerYear, 1, 1);
        LocalDate endDate = rangeShardingValue.getValueRange().upperEndpoint().toLocalDate();
        for (LocalDate curDate = startDate; curDate.isBefore(endDate) || curDate.isEqual(endDate); curDate = curDate.plusYears(1)) {
            String table = getTableByYear(availableTargetNames, String.valueOf(curDate.getYear()));
            if (StringUtils.isNotBlank(table)) {
                tables.add(table);
            }
        }
        return tables;
    }
}
