package com.spring.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.CacheModel;

@Controller
public class SampleController {
	
	// inject via application.properties
		@Value("${welcome.message:test}")
		private String message = "Hello World";

		@RequestMapping("/zxc")
		public String welcome(Map<String, Object> model) {
			model.put("message", this.message);
			List<CacheModel> cacheModelList = new ArrayList<CacheModel>();
			cacheModelList.add(new CacheModel("Key1", "Val1"));
			cacheModelList.add(new CacheModel("Key2", "Val222"));
			model.put("cacheModelList", cacheModelList);
			return "welcome";
		}

}
