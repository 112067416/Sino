package com.coco.sys.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coco.sys.bo.api.IAttachmentBO;
import com.coco.sys.orm.Attachment;
import com.coco.sys.vo.AttachVO;

/**
 * 附件上传控制器
 * 
 * @author 许德建[xudejian@126.com]
 */
@Controller
@RequestMapping("/sys/attachment")
public class AttachmentController {

	@Autowired
	private IAttachmentBO bo;

	/**
	 * 文件上传
	 * 
	 * @param file
	 * @param vo
	 * @return String
	 */
	@RequestMapping("/upload")
	public @ResponseBody
	String upload(@RequestParam MultipartFile file, AttachVO vo) {
		if (file.isEmpty()) {
			return "{id:null,result:\"文件为空\"}";
		}
		Attachment entity = bo.upload(file, vo);
		return "{id:\"" + entity.getId() + "\", single:" + vo.isSingle() + "}";
	}

	/**
	 * 下载文件
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/download")
	public void download(String id, HttpServletResponse response) {
		Attachment entity = bo.download(id);
		response.setContentType(entity.getContentType());
		try {
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(entity.getOriginalName(), "UTF-8"));
		}
		catch (UnsupportedEncodingException e) {
		}
		try {
			response.getOutputStream().write(entity.getStream());
		}
		catch (IOException e) {
		}
	}

	/**
	 * @return the bo
	 */
	public IAttachmentBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IAttachmentBO bo) {
		this.bo = bo;
	}
}
