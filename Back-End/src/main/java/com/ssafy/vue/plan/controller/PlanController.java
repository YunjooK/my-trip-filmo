package com.ssafy.vue.plan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.plan.model.PlanDto;
import com.ssafy.vue.plan.service.PlanServcie;

@RestController
@RequestMapping("/plan")
public class PlanController {
	private Logger logger = LoggerFactory.getLogger(PlanController.class);
	private PlanServcie planService;

	@Autowired
	public PlanController(PlanServcie planService) {
		super();
		this.planService = planService;
	}
	
	@PostMapping("/regist")
	public ResponseEntity<String> writeArticle(@RequestBody PlanDto planDto) throws Exception {
		logger.info("regist plan - 호출");
		try {
			planService.regist(planDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}