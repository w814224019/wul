package com.select.wuliu.util;

import java.util.HashSet;
import java.util.List;

/**
 * List去重方法
 * @author Admin
 *
 */

public class listTodis {
	
	public static List removeDuplicate(List list) {   
	    HashSet h = new HashSet(list);   
	    list.clear();   
	    list.addAll(h);   
	    return list;   
	}  
	
	
	
}
