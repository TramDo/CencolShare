package com.cencolshare.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cencolshare.model.Document;
import com.cencolshare.model.Group;
import com.cencolshare.model.Upload;
import com.cencolshare.model.User;
import com.cencolshare.service.DocumentService;
import com.cencolshare.service.UploadService;
import com.cencolshare.util.GroupUtil;

@Controller
@RequestMapping("/docs")
public class DocumentController extends BaseController {
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	UploadService uploadService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView listDocument() {
		ModelAndView mav = new ModelAndView("docs/document-list");
		User user=getLoggedInUser();
		List<Document> documents = documentService.findAllDocumentByUser(user);
		mav.addObject("documents",documents);
		return mav;
	}
	
	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public ModelAndView discussionListPage() {
		ModelAndView mav = new ModelAndView("document/document-preview");
		mav.addObject("title", "hello");
		return mav;
	}

		
			
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public ModelAndView uploadDocs() {
		ModelAndView mav = new ModelAndView("docs/document-upload");
		return mav;
	}
	
		
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView saveDoc() {
		User user = getLoggedInUser();
		/*Upload upload = new Upload();
		upload.setFileName("java");
		upload.setFileSize("123");
		upload.setFileType(".pdf");
		upload.setOriginalFileName("Java for beginner");
		upload.setUploadDate(new Date());
		upload.setContentType("unknown");
		*/
		Long ID = uploadService.getTheMostRecentUploadId();
		Upload upload = uploadService.getUploadById(ID);
		
		Document doc= new Document();
		doc.setDocumentTitle(request.getParameter("docNameTxt"));
		doc.setDocumentDescription(request.getParameter("docDesTxt"));
		doc.setTag(request.getParameter("tag"));
		doc.setDateUploaded(new Date());
		doc.setFileUrl(upload.getFilePath());
		doc.setUser(user);
		doc.setUpload(upload);
						
		if(!request.getParameter("documentId").equals("")) {
			doc.setDocumentId(Long.parseLong(request.getParameter("documentId")));
		}
		
		doc = documentService.saveDocument(doc);
		if(doc.getDocumentId() > 0) {
			return new ModelAndView(new RedirectView("list"));
		}
		return null;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteDocument(@PathVariable Long id) {
		
		User user = getLoggedInUser();
		final Document doc = documentService.getDocumentById(id);
		Long uploadId = doc.getUpload().getId();
		int docUserId = doc.getUser().getUserId();
		int userID =user.getUserId();
		
		
		if (userID == docUserId){
		
			documentService.deleteDocumentbyID(id);
			uploadService.deleteUpload(uploadId);
		}
		//documentService.deleteDocumentbyID(id);
		//uploadService.deleteUpload(uploadId);
		return new ModelAndView(new RedirectView("/cencolshare/docs/list"));
	}

}
