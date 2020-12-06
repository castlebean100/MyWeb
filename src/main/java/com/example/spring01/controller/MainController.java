package com.example.spring01.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.model.dto.PointDTO;
import com.example.spring01.model.dto.ProductDTO;

@Controller
public class MainController {
	
	// 자꾸 파비콘 오류 떠서 넣어놓은 것
	@RequestMapping("**/favicon.ico")
	public String favicon() {
		return "forward:/resources/img/fav.ico";
	}
	
	
	@RequestMapping("/")
	public String main(Model model) {
		model.addAttribute("name", "백성빈");
		model.addAttribute("message", "Welcome to my page");
		return "main";
	}

	@RequestMapping("gugu.do")
	public String danWrite() {
		return "test/gugu";
	}

	@RequestMapping("gugu_result.do")
	public String gugudan(@RequestParam(defaultValue = "3") int dan, Model model) {
		String result="";
		for (int i=1; i<=9; i++) {
			result += dan + "x" + i + "=" +dan * i + "<br>";
		}
		model.addAttribute("result", result);
		return "test/gugu_result";
	}

	@RequestMapping("point.do")
	public String point() {
		return "test/point";
	}

	@RequestMapping("point_result.do")
	public String point_result(@ModelAttribute PointDTO dto, Model model) {
		dto.setTotal(dto.getKor() + dto.getEng() + dto.getMat());
		String average = String.format("%.2f", dto.getTotal()/3.0);
		dto.setAverage(Double.parseDouble(average));
		model.addAttribute("dto", dto);
		return "test/point_result";

	}

	@RequestMapping("move.do")
	public String move() {
		return "redirect:/result.do?name=paik&age=28";
	}

	@RequestMapping("result.do")
	public String result (Model model,
			@RequestParam(defaultValue = "noname") String name,
			@RequestParam(defaultValue = "10") int age) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "test/result";
	}

	@RequestMapping("mav.do")
	public ModelAndView mav() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", new ProductDTO("샤프", 1000));		// dto로 전달됨
		return new ModelAndView("test/mav_result", "map", map);
		// modelandview("페이지 url","변수명","데이터")
	}

	@RequestMapping("ajax.do")
	public String ajax() {
		return "test/ajax";
	} 

	// {"key": value"}
	// {"name":"냉장고", "price":500000}
	@RequestMapping("background.do")
	public @ResponseBody ProductDTO background() {
		ProductDTO dto = new ProductDTO("tv", 1000);
		return dto;
		// 객체 자체에 저장된 데이터를 리턴함. url이 아니라 데이터이므로 @ResponseBody를 쓰는 것
	}
	@RequestMapping("login.do")
	public String login() {
		return "test/login";
	}

	
	@RequestMapping("login_result.do")
	public String login_result(@RequestParam String id, @RequestParam String pw, Model model) {
		// login.jsp에서 전달받은 id, pw
		String result="";
		if(id.equals("paik") && pw.equals("1234")) {
			result="로그인 성공";
		}else {
			result="로그인 실패";
		}
		model.addAttribute("result", result);
		return "test/login_result";
	}
	
	@RequestMapping("ajax_gugu.do")
	public String ajax_gugu() {
		return "test/ajax_gugu";
	}
	
	@RequestMapping("ajax_gugu_result.do")
	public String ajax_gugu_result(@RequestParam(defaultValue = "3") int dan, Model model) {
		String result="";
		for (int i=1; i<=9; i++) {
			result += dan + "x" + i + "=" +dan * i + "<br>";
		}
		model.addAttribute("result", result);
		return "test/ajax_gugu_result";
	}
}
