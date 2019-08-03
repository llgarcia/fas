package com.fullAutomationStack.GeneratorUtils;

import com.fullAutomationStack.WebDriver.WebElementObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lleir on 25/7/18.
 */
public class WebObjectList {

    private String site;

    @Getter
    @Setter
    private List<WebElementObject> wo_list;
    
    public WebObjectList() {
        wo_list = new ArrayList<WebElementObject>();
    } 
    

    public WebElementObject addToWoList(WebElementObject wo) {
        if(isNotNull())
            wo_list.add(wo);
        return wo;
    }

    public void addWoListToList(WebObjectList wo_list){
        this.wo_list.addAll(wo_list.getWo_list().stream().collect(Collectors.toList()));
    }

    public WebElementObject get(int pos) {
        if(isNotNull())
            return wo_list.get(pos);
        else
            return null;
    }

    public void remove(int pos) {
        if(isNotNull())
            wo_list.remove(pos);
    }


    public boolean isNotNull() {
        return wo_list != null;
    }

    public int size(){
        if(isNotNull())
            return wo_list.size();
        else
            return -1;
    }

    public WebObjectList getWebObjectsByTagNameAndAttribute(String tagname, String attrName) {
        WebObjectList wo_list = new WebObjectList();

        for(WebElementObject wo : this.wo_list)
            if(StringUtils.equalsIgnoreCase(wo.getTagName(), tagname))
                if(wo.existAttributeName(attrName))
                    wo_list.addToWoList(wo);

        return wo_list;
    }

    public WebObjectList getWebObjectsByAttribute(String attrName) {
        WebObjectList wo_list = new WebObjectList();

        for(WebElementObject wo : this.wo_list)
            if(wo.existAttributeName(attrName))
                wo_list.addToWoList(wo);

        return wo_list;
    }


}
