package com.naver.cafe.member.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.naver.cafe.club.web.ClubController;
import com.naver.cafe.common.SHA256Util;
import com.naver.cafe.member.service.MemberService;
import com.naver.cafe.member.vo.MemberVO;

@Controller
public class MemberController {

	private MemberService memberService;
	private Logger logger = LoggerFactory.getLogger(ClubController.class);
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	
	@RequestMapping(value="/member/signUp", method=RequestMethod.GET)
	public String viewSignUpPage(){
		return "member/signUp";
	}
	@RequestMapping(value="/member/signUp", method=RequestMethod.POST)
	public String doSignUpPage(MemberVO member){
		String password = member.getMemberPassword();
		String salt = SHA256Util.generateSalt();
		
		password = SHA256Util.getEncrypt(password, salt);
		
		member.setMemberPassword(password);
		member.setSalt(salt);
		
		if(memberService.addOneMember(member)){
			return "redirect:/member/signIn";
		}
		return "redirect:/member/signUp";
	}
	
	@RequestMapping(value="/member/signIn", method=RequestMethod.GET)
	public String viewSignInPage(){
		return "member/signIn";
	}
	@RequestMapping(value="/member/signIn", method=RequestMethod.POST)
	public String doSignInActionPage(MemberVO member, HttpSession session){
		
		if(memberService.login(session, member)){
			String token = UUID.randomUUID().toString();
			member = memberService.getOneMember(member);
			session.setAttribute("_CSRF_TOKEN_", token);
			session.setAttribute("_MEMBER_", member);
			
			return "redirect:/";
		} 
		return "/member/signIn";
	}
	
	@RequestMapping(value="/member/modify", method=RequestMethod.GET)
	public ModelAndView viewModifyPage(HttpSession session){
		ModelAndView view = new ModelAndView();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		view.setViewName("member/modify");
		view.addObject("member", member);
		return view;
	}
	
	@RequestMapping(value="/member/modify", method=RequestMethod.POST)
	public String doModifyActionPage(MemberVO member){
		
		String salt = memberService.getSaltByMemberId(member.getMemberId());
		String password = member.getMemberPassword();
		
		password = SHA256Util.getEncrypt(password, salt);
		member.setMemberPassword(password);
		
		if(memberService.modifyOneMember(member)){
			return "redirect:/member/signIn";
		} else {
			return "redirect:/member/modify";
		}
	}
	
	@RequestMapping(value="/member/signOut")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/member/signIn";
	}
	
	@RequestMapping(value="/member/checkDuplicatedMemberId", method=RequestMethod.POST)
	public void checkDuplicatedMemberId(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String memberId = request.getParameter("memberId");
		
		boolean isDuplicated = memberService.checkDuplicatedMemberId(memberId);
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("isDuplicated", isDuplicated);
		
		PrintWriter writer = response.getWriter();
		writer.append(gson.toJson(map));
		writer.flush();
		writer.close();
	}
	
	@RequestMapping("/member/checkPasswordCondition")
	public void checkPasswordCondition( HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String password = request.getParameter("memberPassword");
		
		boolean isValidNumber = verifyNumber(password);
		boolean isValidCharacter = verifyCharacter(password);
		boolean isValidSpecialCharacter = verifySpecialCharacter(password);
		boolean isOverEight = password.length() >= 8;
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isValidNumber", isValidNumber);
		map.put("isValidCharacter", isValidCharacter);
		map.put("isValidSpecialCharacter", isValidSpecialCharacter);
		map.put("isOverEight", isOverEight);
		
		PrintWriter writer = response.getWriter();
		writer.append(gson.toJson(map));
		writer.flush();
		writer.close();
	}
	
	public boolean verifyNumber(String password){
		String passwordPolicy = "^.*([0-9]).*$";
		
		Pattern pattern = Pattern.compile(passwordPolicy);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
	public boolean verifyCharacter(String password){
		String passwordPolicy = "^.*([a-zA-Z]).*$";
		
		Pattern pattern = Pattern.compile(passwordPolicy);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
	public boolean verifySpecialCharacter(String password){
		String passwordPolicy = "^.*([^a-zA-Z0-9]).*$";
		
		Pattern pattern = Pattern.compile(passwordPolicy);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
}
