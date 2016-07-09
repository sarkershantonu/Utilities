package org.automation.page;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shantonu on 4/14/16.
 * Part of experiments, target is , creating own page factory
 * kind of decoretor or wrapper over google page factory or yandex page factory
 * this will providede added functionality over default page factory
 * todo => add caching
 */

@Slf4j
public class MyPageFactory {
    private static Map<String, PageBase> pages = new HashMap();
    private MyPageFactory(){}
    public static PageBase getPage(String name){
        return (PageBase)pages.get(name);
    }
    private static void add(String name, PageBase page){
        pages.put(name,page);
    }

}
