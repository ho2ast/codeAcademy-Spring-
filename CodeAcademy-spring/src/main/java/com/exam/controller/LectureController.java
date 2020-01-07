package com.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lecture/*")
public class LectureController {
	@GetMapping("/lectureMain")
	public String lectureMain() {
		return "lecture/lectureMain";
	}
}
