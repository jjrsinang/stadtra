package com.cmsc128.stadtra.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cmsc128.stadtra.entities.Announcement;
import com.cmsc128.stadtra.services.AnnouncementService;
import com.toolkt.utils.CrudError;
import com.toolkt.utils.ErrorHandler;
import com.toolkt.utils.StringUtils;
import com.toolkt.utils.json.JsonData;
import com.toolkt.utils.model.ApplicationError;
import com.toolkt.utils.upload.svc.FileUploadService;

@RequestMapping(value="/announcements")
@Controller
public class AnnouncementController extends AbstractController {
	private final Log log = LogFactory.getLog(getClass());
	
	@Resource
	private AnnouncementService service;
	
	@Resource
	private FileUploadService uploadService;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody JsonData upload(@RequestParam(value = "data", required = false) CommonsMultipartFile fileUpload,
			@RequestParam("title") String title,
			@RequestParam("body") String body) {
		JsonData data = new JsonData();
		
		try {
			
			Announcement announcement = new Announcement();
			announcement.setTitle(title);
			announcement.setBody(body);
			
			if (fileUpload != null && !fileUpload.isEmpty()) {
				System.out.println("Saving file: " + fileUpload.getOriginalFilename());
				Blob blob = new javax.sql.rowset.serial.SerialBlob(fileUpload.getBytes());
				announcement.setData(blob);
				announcement.setFilename(fileUpload.getOriginalFilename());				
			}
			
			service.create(announcement);
			
			data.setRecordCount(1);
			data.setSuccess(true);
			
		} catch (Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}
	
	@RequestMapping("/{id}")
    public String download(
    		@PathVariable("id") Long id, 
    		HttpServletResponse response) {
         
        try {
        	Announcement announcement = service.findById(id);
        	
        	if (StringUtils.hasText(announcement.getFilename())) {
        		response.setHeader("Content-Disposition", "inline;filename=\"" +announcement.getFilename()+ "\"");
                OutputStream out = response.getOutputStream();
                response.setContentType("application/pdf");
                IOUtils.copy(announcement.getData().getBinaryStream(), out);
                out.flush();
                out.close();				
			}
            
         
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
         
        return null;
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody JsonData list(HttpServletRequest request, 
			@RequestParam("page") int page, 
			@RequestParam("start") int start, 
			@RequestParam("limit") int limit) {
		JsonData data = new JsonData();

		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			
			Page<Announcement> announcements = service.findAll(new Announcement(), page, start, limit);
			data.setData(announcements.getContent());
			data.setRecordCount(announcements.getTotalElements());
			data.setSuccess(true);
			
		} catch(Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}
	
	private JsonData controllerError(Exception e) {
		JsonData data = new JsonData();
		ApplicationError error = new ApplicationError();

		String logMarker = ErrorHandler.logError(log, e);
		error.setErrorLog(e.getMessage());
		error.setLogMarker(logMarker);
		CrudError crudError = new CrudError("DB ERROR; "+e.getMessage() + "\n" + ErrorHandler.getStackTrace(e));
		data.setData(crudError);
		data.setSuccess(false);
		data.setError(error);
		return data; 
	}
}
