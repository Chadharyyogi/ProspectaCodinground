package com.masai.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.masai.entity.Entry;
import com.masai.entity.EntryDTO;


import lombok.Data;

@RestController
public class MyController {

	@Autowired
	RestTemplate restTemplate;
	
	
	
	@GetMapping("/entries/{category}")
	public List<EntryDTO> getEntriesHandler(@PathVariable("category") String category){
		com.masai.entity.Data d=(com.masai.entity.Data) restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		List<Entry> entries=d.getEntries();
		List<EntryDTO> list=new ArrayList<>();
		
		for(Entry e:entries) {
			if(e.getCategory().equals(category)) {
				list.add(new EntryDTO(e.getApi(), e.getDescription()));
			}
		}
		return list;
		
	}

	@PostMapping("/entries")
	public String saveEntryHandler(@RequestBody Entry entry){
		com.masai.entity.Data d=(com.masai.entity.Data) restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		List<Entry> entries=d.getEntries();
		entries.add(entry);
		return "Entry Added Successfully";
	   
	}
	
}
