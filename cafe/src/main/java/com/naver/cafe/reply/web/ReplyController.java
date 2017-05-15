package com.naver.cafe.reply.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.naver.cafe.common.Auth;
import com.naver.cafe.reply.service.ReplyService;
import com.naver.cafe.reply.vo.ReplyVO;

@Controller
public class ReplyController {

	private ReplyService replyService;
	private Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@Auth("USR")
	@RequestMapping(value="/club/reply/write/{menuId}/{articleId}", method=RequestMethod.POST)
	public String doReplyWriteActionPage(@PathVariable String menuId, @PathVariable String articleId
				, ReplyVO reply){
		logger.info("doReplyWriteAction");
		logger.info("reply.memberId : " + reply.getMemberId());
		logger.info("reply.articleId : " + reply.getArticleId());
		logger.info("reply.content : " + reply.getContent());
		
		logger.info("reply.parentId : " + reply.getParentReplyId());
		
		String contents = reply.getContent();
		contents = contents.replaceAll("\n", "<br/>");
		contents = contents.replaceAll("\r", "");
		reply.setContent(contents);
		
		replyService.addOneReply(reply);
		
			return "redirect:/club/detail/" + menuId + "/" + articleId;
	}
	
	@Auth("USR")
	@RequestMapping(value="/club/reply/modify/{menuId}/{articleId}", method=RequestMethod.POST)
	public String doReplyModifyActionPage(@PathVariable String menuId, @PathVariable String articleId
			,ReplyVO reply){
		
		logger.info("doReplyModifyAction");
		logger.info("reply.replyId : " + reply.getReplyId());
		logger.info("reply.content : " + reply.getContent());
		
		String contents = reply.getContent();
		contents = contents.replaceAll("\n", "<br/>");
		contents = contents.replaceAll("\r", "");
		reply.setContent(contents);
		
		replyService.modifyOneReply(reply);
		
		return "redirect:/club/detail/" + menuId + "/" + articleId;
	}
	
	@Auth("USR")
	@RequestMapping(value="/club/reply/remove/{menuId}/{articleId}", method=RequestMethod.POST)
	public String doReplyDeleteActionPage(@PathVariable String menuId, @PathVariable String articleId
			, ReplyVO reply){
		
		logger.info("doReplyModifyAction");
		logger.info("reply.replyId : " + reply.getReplyId());
		logger.info("reply.content : " + reply.getContent());
		
		replyService.removeOneReply(reply.getReplyId());
		
		return "redirect:/club/detail/" + menuId + "/" + articleId;
	}
}