package it.hellokitty.gt.entity.utils;

import java.util.HashMap;
import java.util.Map;

public class LanguageUtils {
	private Map<String, String> contentMap = new HashMap<String, String>();
	private Map<String, String> descriptionMap = new HashMap<String, String>();
	private Map<String, String> titleMap = new HashMap<String, String>();
	
	LanguageUtils(){
		contentMap.put("it", "getContentIt()");
		contentMap.put("en", "getContentEn()");
		contentMap.put("fr", "getContentFr()");
		contentMap.put("es", "getContentEs()");
		contentMap.put("de", "getContentDe()");
		contentMap.put("jp", "getContentJp()");
		contentMap.put("zh", "getContentZh()");
		
		descriptionMap.put("it", "getDescriptionIt()");
		descriptionMap.put("en", "getDescriptionEn()");
		descriptionMap.put("fr", "getDescriptionFr()");
		descriptionMap.put("es", "getDescriptionEs()");
		descriptionMap.put("de", "getDescriptionDe()");
		descriptionMap.put("jp", "getDescriptionJp()");
		descriptionMap.put("zh", "getDescriptionZh()");
		
		titleMap.put("it", "getTitleIt()");
		titleMap.put("en", "getTitleEn()");
		titleMap.put("fr", "getTitleFr()");
		titleMap.put("es", "getTitleEs()");
		titleMap.put("de", "getTitleDe()");
		titleMap.put("jp", "getTitleJp()");
		titleMap.put("zh", "getTitleZh()");
	}

	protected Map<String, String> getContentMap() {
		return contentMap;
	}

	protected void setContentMap(Map<String, String> contentMap) {
		this.contentMap = contentMap;
	}

	protected Map<String, String> getDescriptionMap() {
		return descriptionMap;
	}

	protected void setDescriptionMap(Map<String, String> descriptionMap) {
		this.descriptionMap = descriptionMap;
	}

	protected Map<String, String> getTitleMap() {
		return titleMap;
	}

	protected void setTitleMap(Map<String, String> titleMap) {
		this.titleMap = titleMap;
	}
}
