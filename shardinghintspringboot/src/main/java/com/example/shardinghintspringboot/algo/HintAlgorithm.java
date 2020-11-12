package com.example.shardinghintspringboot.algo;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

public class HintAlgorithm implements HintShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
        Collection<String> rs = new ArrayList<>();

        for(Object table: collection){
            for(Object value: hintShardingValue.getValues()){
                if(table.toString().endsWith(String.valueOf((Integer)value % 2))){
                    rs.add(table.toString());
                }
            }
        }
        return rs;
    }
}
