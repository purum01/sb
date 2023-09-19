package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"val3"})
@NoArgsConstructor
@AllArgsConstructor
public class SampleVO {
	private String val1;
	private String val2;
	private String val3;
}
