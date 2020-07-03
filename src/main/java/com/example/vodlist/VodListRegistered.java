package com.example.vodlist;

public class VodListRegistered {
	String eventType;
	Long vodId;
	String vodName;
		
	public VodListRegistered() { 	
		this.eventType = VodListRegistered.class.getSimpleName();
	}
	public Long getVodId() { return vodId;}
	public void setVodId(Long vodId) { this.vodId = vodId;	}
	public String getVodName() { return vodName;	}
	public void setVodName(String vodName) { this.vodName = vodName;	}
	public String getEventType() { return eventType;}
	public void setEventType(String eventType) { this.eventType = eventType;	}
	
	

}
