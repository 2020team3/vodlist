package com.example.vodlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableBinding(Processor.class)
public class VodListApplication {

	public static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(VodListApplication.class, args);
		
		VodListRepository vodListRepository = 
				VodListApplication.applicationContext.getBean(VodListRepository.class);	
		String[] name = {"Alive!", "ToBusan"};
	
		for(int i=0;i<name.length;i++) {
			VodList list = new VodList();
			
			list.setId((long) (i+1));
			list.setVodId((long) (i+1));
			list.setVodName(name[i]);
			vodListRepository.save(list);
		}
	}
	@StreamListener(Processor.INPUT)
	public void notting() { }
}
