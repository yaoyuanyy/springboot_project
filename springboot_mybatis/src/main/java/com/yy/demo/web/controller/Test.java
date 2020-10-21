package com.yy.demo.web.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020-01-12 at 19:50
 */
public class Test {

    public static void main(String[] args) {
        int[] ints = {12,2,234,5,6567,767};

        //Arrays.sort((ints));

        ArrayList list = new ArrayList<Integer>();
        list.add(12);
        list.add(2);
        list.add(234);
        list.add(6567);
        list.add(5);
        list.add(767);
        Collections.sort(list);
    }
}
