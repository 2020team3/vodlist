package com.example.vodlist;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="VodList_table")
public class VodList {

	@Id @GeneratedValue     // id자동생성
	private Long Id;
	private Long vodId;
	private String vodName;
	
	public Long getId() {return Id;	}
	public void setId(Long id) {Id = id;}
	public Long getVodId() {	return vodId;}
	public void setVodId(Long vodId) {this.vodId = vodId;	}
	public String getVodName() {return vodName;}
	public void setVodName(String vodName) {	this.vodName = vodName;	}

	@PostPersist
	public void vodregist() {
		VodListRepository vodListRepository = 
				VodListApplication.applicationContext.getBean(VodListRepository.class);
		Optional<VodList> opt = vodListRepository.findById(this.getId());
		VodList vods = opt.get();
		
		VodListRegistered vodRegistered = new VodListRegistered();
		vodRegistered.setVodId(vods.getVodId());
		vodRegistered.setVodName(vods.getVodName());
		
	    ObjectMapper objectMapper = new ObjectMapper();
	    String json = null;

	    try {
	        json = objectMapper.writeValueAsString(vodRegistered);
	    } catch (JsonProcessingException e) {
	        throw new RuntimeException("JSON format exception", e);
	    }

	    System.out.println("[Vod.java:vodregist] "+json);
	    Processor processor = VodListApplication.applicationContext.getBean(Processor.class);
	    MessageChannel outputChannel = processor.output();

	    outputChannel.send(MessageBuilder
	            .withPayload(json)
	            .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
	            .build());
	}
}
