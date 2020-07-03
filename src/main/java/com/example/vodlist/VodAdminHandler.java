//package com.example.vodlist;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Processor;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//@Service
//public class VodAdminHandler {
//
//	@Autowired
//	VodListRepository vodAdminRepository;
//	
//	@StreamListener(Processor.INPUT)
//	public void onVodRegister(@Payload VodRegistRequested vodRegistRequested){
//		
//	    if( vodRegistRequested.getEventType().equals("VodRegistRequested")){
//	        System.out.println("======================");
//
//	        Long id = vodRegistRequested.getVodId();
//	        System.out.println("My ID :" + id);
//	        
//	        Optional<VodList> vodById = vodAdminRepository.findById(id);
//	        VodList p = vodById.get();
//	        p.setVodName(vodRegistRequested.getVodName());
//	        
//	        vodAdminRepository.save(p);
//	        System.out.println("======================");
//	    }
//	}
//}
