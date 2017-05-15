package com.naver.cafe.club.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.naver.cafe.club.service.ClubService;
import com.naver.cafe.club.vo.ClubListVO;
import com.naver.cafe.club.vo.ClubSearchVO;
import com.naver.cafe.club.vo.ClubVO;
import com.naver.cafe.common.Auth;
import com.naver.cafe.common.DownloadUtil;
import com.naver.cafe.common.web.ListPageExplorer;
import com.naver.cafe.common.web.PageExplorer;
import com.naver.cafe.member.vo.MemberVO;
import com.naver.cafe.menu.vo.MenuVO;
import com.naver.cafe.reply.biz.ReplyBiz;
import com.naver.cafe.reply.vo.ReplyVO;
import com.nhncorp.lucy.security.xss.XssFilter;

@Controller
public class ClubController {

	private Logger logger = LoggerFactory.getLogger(ClubController.class);
	private ClubService clubService;
	

	public void setClubService(ClubService clubService) {
		this.clubService = clubService;
	}
	

	// 검색 조건(Page 정보) 사라진 처음 페이지 보여주고 싶은 경우
	@Auth("USR")
	@RequestMapping("/club/{menuId}/init")
	public String viewClubListInitPage(@PathVariable String menuId, HttpSession session) {
		session.removeAttribute("_SEARCH_"); // SEARCH 속성을 지워라
		return "redirect:/club/" + menuId;
	}

	@Auth("USR")
	@RequestMapping("/")
	public String viewListPage(){
		return "redirect:/club/"+ "MN-20170423-000003";
	}
	// 세션에 SearchVO 넣어줘 페이지 정보 전달해준다.
	// detail 읽은 게시판이 있는 페이지 찾기 위해서. url로 추가해도 되지만 더욱 번거로움. url 매개변수는 최소화하는 것이
	// 좋음~
	
	@Auth("USR")
	@RequestMapping("/club/{menuId}")
	public ModelAndView viewClubListPage(@PathVariable String menuId, ClubSearchVO clubSearchVO, HttpSession session
				, HttpServletRequest request) {
		// 선택한 카테고리에 해당하는 게시판 글 리스트로 가져옴
		// 페이지 정보 없이 들어오는 경우 (최초 검색)
		if (clubSearchVO.getPageNo() == null || clubSearchVO.getPageNo().length() == 0) {
			ClubSearchVO clubSearchVOInSession = (ClubSearchVO) session.getAttribute("_SEARCH_");
			if (clubSearchVOInSession != null) {
				clubSearchVO = clubSearchVOInSession; // 항상 가장 최신 정보 가질 수 있음
			}
		}
		
		clubSearchVO.setSearchType(request.getParameter("searchType"));
		clubSearchVO.setMenuId(menuId);
		ClubListVO clubListVO = clubService.getAllClub(clubSearchVO);
		session.setAttribute("_SEARCH_", clubSearchVO);

		// 권한에 따라
		ModelAndView view = new ModelAndView();
		view.addObject("menu", clubListVO.getMenuList());

		/**
		 * 리스트 0인 경우 비교방법 1) totalCount 0인가? 2) clubList 가 null인가? (jstl에서
		 * empty)
		 */
		view.addObject("clubList", clubListVO.getClubList());
		view.addObject("totalCount", clubListVO.getPager().getTotalArticleCount());
		view.addObject("currentMenu", clubListVO.getCurrentMenu());

		PageExplorer pageExplorer = new ListPageExplorer(clubListVO.getPager());
		String pager = pageExplorer.getPagingList("pageNo", "@", "Prev", "Next", "searchForm");
		view.addObject("pager", pager);
		view.setViewName("club/list");
		view.addObject("menuId", menuId);

		return view;
	}

	// 조회수 증가
	@Auth("USR")
	@RequestMapping("/club/read/{menuId}/{id}")
	public String viewReadDetailPage(@PathVariable String menuId, @PathVariable String id) {
		ClubSearchVO clubSearch = new ClubSearchVO();
		clubSearch.setMenuId(menuId);
		clubSearch.setArticleId(id);
		
		clubService.addReadCount(clubSearch);
		return "redirect:/club/detail/" + menuId + "/" + id;
	}
	
	@Auth("USR")
	@RequestMapping("/club/detail/{menuId}/{articleId}")
	public ModelAndView viewDetailPage(@PathVariable String menuId, @PathVariable String articleId) {
		ModelAndView view = new ModelAndView();
		ClubSearchVO clubSearch = new ClubSearchVO();
		clubSearch.setMenuId(menuId);
		clubSearch.setArticleId(articleId);
		
		Map<String, Object> club = clubService.getOneClub(clubSearch);
		ClubVO clubVO = (ClubVO) club.get("club");
		logger.info("memberId:" + clubVO.getMemberId());
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		clubVO.setSubject(filter.doFilter(clubVO.getSubject()));
		clubVO.setContent(filter.doFilter(clubVO.getContent()));
		@SuppressWarnings("unchecked")
		List<MenuVO> allMenu = (List<MenuVO>) club.get("menu");
		@SuppressWarnings("unchecked")
		List<MenuVO> currentMenu = (List<MenuVO>) club.get("currentMenu");

		view.addObject("club", clubVO);
		view.addObject("menu", allMenu);
		view.addObject("currentMenu", currentMenu);
		view.addObject("menuId", menuId);
		view.setViewName("club/detail");
		return view;
	}

	@Auth("ADM")
	@RequestMapping(value = "/club/write/{menuId}", method = RequestMethod.GET)
	public ModelAndView viewWriteArticlePage(@PathVariable String menuId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		System.out.println("memberId : " + member.getMemberId());
		view.setViewName("club/write");
		view.addObject("menuId", menuId);
		view.addObject("memberId", member.getMemberId());
		return view;
	}

	@Auth("USR")
	@RequestMapping(value = "/club/write/{menuId}", method = RequestMethod.POST)
	public String doWriteArticleAction(HttpServletRequest request, @PathVariable String menuId, @Valid @ModelAttribute("writeForm") ClubVO clubVO) {
		String jspPageToken = request.getParameter("csrf_token");
		String sessionToken = (String) request.getSession().getAttribute("_CSRF_TOKEN_");
		MemberVO member = (MemberVO) request.getSession().getAttribute("_MEMBER_");
		logger.info("memberId:" + member.getMemberId());
		
		if(clubVO.getContent() == null || clubVO.getContent().length() == 0){
			return "redirect:/club/write/" + menuId;
		}
		
		if(!jspPageToken.equals(sessionToken)){
			throw new RuntimeException("잘못된 글쓰기입니다.");
		}
		
		clubVO.setMenuId(menuId);
		clubVO.setMemberId(member.getMemberId());
		logger.info("clubVO.memberId:" + clubVO.getMemberId());

		XssFilter xssFilter = XssFilter.getInstance("lucy-xss-superset.xml");
		String subject = xssFilter.doFilter(clubVO.getSubject());
		String content = xssFilter.doFilter(clubVO.getContent());
		clubVO.setSubject(subject);
		clubVO.setContent(content);
		
		boolean isSuccess = clubService.addNewClub(clubVO);

		return "redirect:/club/" + menuId;
		
	}
	
	@Auth("USR")
	@RequestMapping(value="/club/modify/{menuId}/{articleId}", method=RequestMethod.GET)
	public ModelAndView viewModifyArticlePage(@PathVariable String articleId, @PathVariable String menuId){
		ModelAndView view = new ModelAndView();
		ClubSearchVO clubSearch = new ClubSearchVO();
		clubSearch.setMenuId(menuId);
		clubSearch.setArticleId(articleId);
		
		Map<String, Object> club = clubService.getOneClub(clubSearch);
		ClubVO clubVO = (ClubVO) club.get("club");
		view.addObject("club", clubVO);
		view.setViewName("club/modify");
		return view;
	}
	
	@Auth("USR")
	@RequestMapping(value="/club/modify/{menuId}/{articleId}", method=RequestMethod.POST)
	public String doModifyArticleActionPage(@PathVariable String articleId, @PathVariable String menuId, ClubVO club){
		if(clubService.editOneClub(club)){
			logger.info("edit Success");
			return "redirect:/club/detail/"+menuId+"/"+articleId;
		} else {
			return "redirect:/club/modify/{articleId}";
		}
	}
	
	@Auth("USR")
	@RequestMapping(value="club/delete/{articleId}", method=RequestMethod.GET)
	public String doDelteArticleActionPage(@PathVariable String articleId){
		if(clubService.removeOneClub(articleId)){
			return "redirect:/club";
		}else {
			return "redirect:/club/modify/{articleId}";
		}
	}
	
	@Auth("USR")
	@RequestMapping(value="/club/deleteArticles/{menuId}", method=RequestMethod.POST)
	public String doDeleteArticlesActionPage(@PathVariable String menuId, HttpServletRequest request){
		String[] articleIdList = request.getParameterValues("deleteCheckedArticles");
		logger.info("menuId:"+menuId);
		
		for(String articleId : articleIdList){
			
			clubService.removeOneClub(articleId);
		}
		
		return "redirect:/club/" + menuId;
	}
	
	@Auth("USR")
	@RequestMapping(value = "/club/upload", method = RequestMethod.POST)
	public void doUploadFile(MultipartHttpServletRequest request, HttpServletResponse response) {

		MultipartFile file = request.getFile("file");
		logger.info(file.getOriginalFilename() + " is getOriginalFilename");

		if (file != null && !file.isEmpty()) {
			File uploadPath = new File("C:/uploadFile/" /*+ file.getOriginalFilename()*/
						+ file.getOriginalFilename().replaceAll(" ", "_"));
			try {
				file.transferTo(uploadPath);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("parent.document.getElementById('uploadFiles').innerHTML +=");
			out.write("'<p>http://localhost:8080/cafe/club/download/" 
				+ file.getOriginalFilename()
					.replaceAll(" ", "_")
					.replaceAll("[.]", "_dot_") + "</p>';");
			out.write("</script>");
			out.flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Auth("USR")
	@RequestMapping("/club/download/{fileName}")
	public void downloadAttachedFile(@PathVariable String fileName
					, HttpServletRequest request
					, HttpServletResponse response) {
		DownloadUtil download = DownloadUtil.getInstance("C:/uploadFile/");
		fileName = fileName.replaceAll("_dot_", ".");
		
		try {
			download.download(request, response, fileName, fileName);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
}
