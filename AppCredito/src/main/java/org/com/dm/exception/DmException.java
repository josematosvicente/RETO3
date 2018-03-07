package org.com.dm.exception;

public class DmException  extends Exception{

	private String code, message;
	
	public DmException(){
		super();
	}
	
    public DmException(String code, String message) {
        super();
    	//System.err.println(getStackTrace());
        System.out.println(code + ":" + message);
        //System.out.println(getCause());
        this.code = code;
        this.message = message;
        printStackTrace();
    }
    
    public DmException(String code, String message, Object[] params) {
    	String messageFinal = message;
    	int i = 0;
    	for(Object param : params){
    		messageFinal = messageFinal.replaceAll("{"+i+"}", params[i].toString());
    		i++;
    	}
        //System.err.println(getStackTrace());
        System.out.println(code + ":" + messageFinal);
        //System.out.println(getCause());
        printStackTrace();
    }

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
