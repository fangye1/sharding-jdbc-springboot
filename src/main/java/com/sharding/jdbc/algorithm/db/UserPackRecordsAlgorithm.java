package com.sharding.jdbc.algorithm.db;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * user_pack_records 分库：阅读时间 start_time 在2018-2019 在 db0,2020-2021 在db1
 */
public class UserPackRecordsAlgorithm implements PreciseShardingAlgorithm<LocalDateTime>, RangeShardingAlgorithm<LocalDateTime> {

    /**
     * 通过年份获取真实数据库
     *
     * @param year 年份
     * @return 真实数据库
     */
    private String getDbByYear(Integer year) {
        if (year == 2018 || year == 2019) {
            return "ds0";
        } else if (year == 2020 || year == 2021) {
            return "ds1";
        }
        throw new IllegalArgumentException();
    }

    /**
     * PreciseShardingAlgorithm 是必选的，用于处理=和IN的分片。
     *
     * @param availableDbNames 可用的数据库集合
     * @param shardingValue    分片键
     * @return 真实数据库
     */
    @Override
    public String doSharding(Collection<String> availableDbNames, PreciseShardingValue<LocalDateTime> shardingValue) {
        int year = shardingValue.getValue().getYear();
        return getDbByYear(year);
    }

    /**
     * RangeShardingAlgorithm 可选，用于处理BETWEEN AND, >, <, >=, <=分片
     *
     * @param availableDbNames   可用的数据库集合
     * @param rangeShardingValue 分片键
     * @return 真实数据库
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableDbNames, RangeShardingValue<LocalDateTime> rangeShardingValue) {
        List<String> dbs = new ArrayList<>();
        int lowerYear = rangeShardingValue.getValueRange().lowerEndpoint().toLocalDate().getYear();
        LocalDate startDate = LocalDate.of(lowerYear, 1, 1);
        LocalDate endDate = rangeShardingValue.getValueRange().upperEndpoint().toLocalDate();
        for (LocalDate curDate = startDate; curDate.isBefore(endDate) || curDate.isEqual(endDate); curDate = curDate.plusYears(1)) {
            dbs.add(getDbByYear(curDate.getYear()));
        }
        return dbs;
    }
}
