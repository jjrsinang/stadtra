package com.cmsc128.stadtra.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsc128.stadtra.entities.Filter;
import com.cmsc128.stadtra.entities.Student;
import com.cmsc128.stadtra.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.toolkt.utils.CrudError;
import com.toolkt.utils.ErrorHandler;
import com.toolkt.utils.StringUtils;
import com.toolkt.utils.json.JsonData;
import com.toolkt.utils.model.ApplicationError;

@RequestMapping(value="/students")
@Controller
public class StudentController extends AbstractController {
	private final Log log = LogFactory.getLog(getClass());
	
	@Resource
	private StudentService service;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public JsonData create(HttpServletRequest request, @RequestBody Student student) {
		JsonData data = new JsonData();
		
		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			Student created = service.create(student);
			data.setData(created);
			data.setRecordCount(1);
			data.setSuccess(true);
		} catch(Exception e) {
			data = controllerError(e);		
		}
		return data;
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public JsonData retrieve(HttpServletRequest request, @PathVariable("id") Long id) {
		JsonData data = new JsonData();
		
		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			
			Student student = service.findById(id);
			
			data.setData(student);
			data.setRecordCount(1);
			data.setSuccess(true);

		} catch (Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public JsonData update(HttpServletRequest request, @RequestBody Student student, 
			@PathVariable("id") Integer id) {
		JsonData data = new JsonData();
		
		try { 
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			
			Student updated = service.update(student);
			data.setData(updated);
			data.setRecordCount(1);
			data.setSuccess(true);
		} catch (Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public JsonData delete(HttpServletRequest request, @PathVariable("id") Long id) {
		JsonData data = new JsonData();
		
		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			
			service.delete(id);
			data.setSuccess(true);
		} catch (Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}
	
	//Note:
	//To test this method manually, point the browser to: 
	//	http://host:port/url/students?page=val&start=val&limit=val
	//	Ex. http://localhost:8080/stadtra/ws/srudents?page=1&start=1&limit=20
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody JsonData list(HttpServletRequest request, 
			@RequestParam("page") int page, 
			@RequestParam("start") int start, 
			@RequestParam("limit") int limit,
			@RequestParam(value="filter", required=false) String filter) {
		JsonData data = new JsonData();

		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			
			Student student = new Student();
				if (StringUtils.hasText(filter)){
				ObjectMapper mapper = new ObjectMapper();
				TypeFactory typeFactory = mapper.getTypeFactory();
				List<Filter> list = mapper.readValue(filter, typeFactory.constructCollectionType(List.class, Filter.class));
				
				for (Filter propertyFilter : list) {
					String property = (String)propertyFilter.getProperty();
					String value = (String)propertyFilter.getValue();
					if (StringUtils.hasText(property)) {
						if (property.equals("lName")) {
							student.setlName(value);
						} else if (property.equals("studentNo")) {
							student.setStudentNo(value);
						}
					}
				}
			}
			
			Page<Student> students = service.findAll(student, page, start, limit);
			data.setData(students.getContent());
			data.setRecordCount(students.getTotalElements());
			data.setSuccess(true);
			
		} catch(Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}	

	@RequestMapping(value="/query/results", method=RequestMethod.POST)
	public @ResponseBody JsonData search(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Student student, 
			@RequestParam("page") int page,  
			@RequestParam("start") int start, 
			@RequestParam("limit") int limit) {
		JsonData data = new JsonData();

		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			
			Page<Student> students = service.findAll(student, page, start, limit);
			data.setData(students);
			data.setRecordCount(students.getTotalElements());
			data.setSuccess(true);
			
		} catch (Exception e) {
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
