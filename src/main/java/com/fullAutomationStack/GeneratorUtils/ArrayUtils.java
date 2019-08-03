package com.fullAutomationStack.GeneratorUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArrayUtils<Type> {

    public static <Type> List<?> INIT() {
        return new ArrayList<Type>();
    }

    public static <Type> List<?> convertList(List<Type> list){
        List<Type> res = new ArrayList<>();

        for(Type e : list)
            res.add((Type) e);

        return res;
    }

//    public static <Type> String[] convertList(List<Type> list){
//
//        String[] strArray = new String[list.size()];
//
//        for(int inx = 0; inx < list.size(); inx++) {
//            strArray[inx] = (String) list.get(inx);
//        }
//
//        return strArray;
//    }

    public static <Type> List<?> convertList(Type list){
        List<Type> res = new ArrayList<>();

        String[] listString = (String[]) list;

        for(int indx = 0; indx < listString.length; indx++)
            res.add((Type) listString[indx]);

        return res;
    }

    public static <Type> List<?> convertList(Set<Type> list) {
        List<Type> res = new ArrayList<>();

        for(Type e : list)
            res.add((Type)e);

        return res;
    }

    public static <Type> boolean contains(Type arr, String value) {
        String containsRes = null;

        if(arr instanceof String[]){
           List<String> ar = (List<String>) convertList(arr);

//           containsRes = ar.stream().filter(str -> ar.contains(value)).toString();
            containsRes = ar.stream()
                    .filter( c -> c.contains(value))
                    .findAny()
                    .map(v -> value)
                    .orElse(null);


       }

       return containsRes != null ? true : false;
    }

    public static <Type> Map separateArrayInMap(Type key1, Type key2, int from, int to, List<Type> whileList) {

        Map<Type, List<Type>> mapp = new HashMap<Type, List<Type>>();

        List<Type> between = new ArrayList<Type>();
        List<Type> outside = new ArrayList<Type>();

        for(int e = 0; e < whileList.size(); e++){
            Type res = whileList.get(e);

            if (e >= from && e <= to)
                between.add(res);
            else
                outside.add(res);

        }

        mapp.put(key1, between);
        mapp.put(key2, outside);

        return mapp;
    }

    public static <Type> List<?> shortReverse(List<Type> list){
        List<Type> tmp = new ArrayList<>();

        for(int a = list.size(); a > 0; a--){
            tmp.add((Type)list.get(a-1));
        }

        return tmp;
    }


}
