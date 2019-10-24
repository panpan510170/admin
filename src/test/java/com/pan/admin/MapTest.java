package com.pan.admin;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.pan.base.utils.life.LifeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pan
 * @date 2019/9/26 14:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapTest {

    @Test
    public void testMap(){
        Map<String, Object> param = new HashMap<>();
        param.put("key","123456789");
        param.put("id","wer");
        System.out.println(LifeUtils.buildParamByLife(param));
    }

    /**
     * 双键map
     */
    @Test
    public void forList() {
        Table<String, Integer, String> aTable = HashBasedTable.create();

        for (char a = 'A'; a <= 'C'; ++a) {
            for (Integer b = 1; b <= 3; ++b) {
                aTable.put(Character.toString(a), b, String.format("%c%d", a, b));
            }
        }
        System.out.println(aTable);

        System.out.println(aTable.column(2));
        System.out.println(aTable.row("B"));
        System.out.println(aTable.get("B", 2));

        System.out.println(aTable.contains("D", 1));
        System.out.println(aTable.containsColumn(3));
        System.out.println(aTable.containsRow("C"));
        System.out.println(aTable.columnMap());
        System.out.println(aTable.rowMap());

        System.out.println(aTable.remove("B", 3));
    }
}
