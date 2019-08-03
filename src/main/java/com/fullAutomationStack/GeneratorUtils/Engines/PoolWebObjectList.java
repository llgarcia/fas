package com.fullAutomationStack.GeneratorUtils.Engines;

import com.fullAutomationStack.GeneratorUtils.WebObjectList;
import com.fullAutomationStack.WebDriver.WebElementObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lleir on 25/7/18.
 */
public class PoolWebObjectList {

    @Getter
    @Setter
    private static List<WebObjectList> webObjectsLists;


    public static void addWebObjectList(WebObjectList wo_list){
        if(webObjectsLists == null)
            webObjectsLists = new ArrayList<WebObjectList>();

        webObjectsLists.add(wo_list);
    }

    public static WebObjectList getWebObjectByPosAndTagAndAttribute(int pos, String tagname, String attribute){
        return webObjectsLists.get(pos).getWebObjectsByTagNameAndAttribute(tagname, attribute);
    }

    public static WebObjectList  getLastWebObjectList() {
        return webObjectsLists.get(webObjectsLists.size()-1);
    }

}
