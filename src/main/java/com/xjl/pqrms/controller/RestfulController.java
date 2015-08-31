package com.xjl.pqrms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





import com.xjl.pqrms.dao.MainRead;
import com.xjl.pqrms.entity.Project;
import com.xjl.pqrms.entity.Workspace;

@RestController
public class RestfulController {

	@RequestMapping(value="/getProjectList",method=RequestMethod.GET)
	public RestfulResponse getProjectList(){
		try {
			return new RestfulResponse(true, Project.getProjectList());
		}
		catch(Exception e){
			return new RestfulResponse(false, e.getMessage());
		}
	}
	
	@RequestMapping(value="/getWorkspace",method=RequestMethod.GET)
	public RestfulResponse getWorkspace(@RequestParam(value="projectId") int projectId){
		try {
			return new RestfulResponse(true, Workspace.getWorkspace(projectId));
		}
		catch(Exception e){
			return new RestfulResponse(false, e.getMessage());
		}
	}
	
	@RequestMapping(value="/setProjectOption",method=RequestMethod.GET)
	public RestfulResponse setProjectOption(
			@RequestParam(value="projectId") int projectId,
			@RequestParam(value="questionId") int questionId,
			@RequestParam(value="seq") int seq
	){
		try {
			int sucess=Workspace.setProjectOpion(projectId, questionId, seq);
			if(sucess==1) return new RestfulResponse(true, null);
			else return new RestfulResponse(false, null);
		}
		catch(Exception e){
			return new RestfulResponse(false, e.getMessage());
		}
	}
	
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public Object test(){
		return MainRead.getWorkspace(2);
	}
	
	/*
	 @RequestMapping("getUserBody")
	    public User getUserBody(@RequestBody String body){
	        ObjectMapper mapper = new ObjectMapper();
	        User User = null;
	        try {
	            User = mapper.readValue(body,  User.class);
	        } catch (JsonParseException e) {
	            e.printStackTrace();
	        } catch (JsonMappingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return User;
	    }
	*/
}
