package com.example.spring01.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring01.model.dao.MemberDAO;
import com.example.spring01.model.dto.MemberDTO;

@Controller
public class MemberController {

	@Inject
	MemberDAO memberDAO;

	@RequestMapping("member/list.do")
	public String memberList(Model model) {
		List<MemberDTO> list = memberDAO.list();
		/* DAO는 인터페이스이므로 DAOImpl에서 갖고옴
		 * 그러므로 DAOImpl에서 selectList > member.xml의
		 * namespace가 member이고 id가 list인 게 실행됨
		 * 이후 레코드들이 저장되서 array list 로 저장되어서 model로...
		 */
		model.addAttribute("items",list);
		return "member/list";
	}

	// 회원등록폼으로 이동
	@RequestMapping("member/write.do")
	public String write() {
		return "member/write";
	}

	@RequestMapping("member/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		memberDAO.insert(dto);
		return "redirect:/member/list.do";
	}

	@RequestMapping("member/view.do")
	public String view(@RequestParam String userid, Model model) {
		model.addAttribute("dto", memberDAO.detail(userid));
		// 회원 정보를 model에 저장 변수명은 dto로...
		return "member/detail";
		// detail.jsp로 포워딩
	}

	@RequestMapping("member/update.do")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		// 비밀번호 체크
		boolean result = memberDAO.check_passwd(dto.getUserid(), dto.getPasswd());
		if(result) {
			memberDAO.update(dto);
			return "redirect:/member/list.do";
		} else {
			// 가입일자가 지워지지 않도록 처리하는 부분
			MemberDTO dto2 = memberDAO.detail(dto.getUserid());
			dto.setRegDate(dto2.getRegDate());
			model.addAttribute("dto",dto);
			model.addAttribute("message","비밀번호가 일치하지 않습니다.");
			return "member/detail";
		}
	}
	
	@RequestMapping("member/delete.do")
	public String delete(@RequestParam String userid, @RequestParam String passwd, Model model) {
		// 비번체크
		boolean result = memberDAO.check_passwd(userid, passwd);
		if(result) {
			// 삭제
			memberDAO.delete(userid);
			// 목록으로 이동
			return "redirect:/member/list.do";
		} else {
			// 비번 틀리면
			model.addAttribute("message", "비밀번호 불일치");
			model.addAttribute("dto", memberDAO.detail(userid));
			// view.jsp로 포워딩
			return "member/detail";
		}
	}
}

