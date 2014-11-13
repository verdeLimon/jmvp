package org.unsch.impl.reflect;

public class AppControllerReflectFactory {
	
	
	
	private static AppControllerReflect appControllerReflect;
	public static AppControllerReflect getAppControllerInstance(){
		if(appControllerReflect==null){
			
			appControllerReflect=new AppControllerReflect();
			
		}
		
		return appControllerReflect;
	}

}
