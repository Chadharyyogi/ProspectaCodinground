package com.masai.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {

	private int count;
	
	private List<Entry> entries;
}
