package com.o1teck.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.o1teck.model.entity.StatusUpdate;
import com.o1teck.service.StatusUpdateService;

@Controller
public class StatusUpdateController {
	
	@Autowired
	StatusUpdateService statusUpdateService;

	
	@RequestMapping(value="/editstatus")
	ModelAndView editStatus(ModelAndView modelAndView, @RequestParam(name="id")Long id){
		
		StatusUpdate statusUpdate = statusUpdateService.get(id);
		
		modelAndView.getModel().put("statusUpdate", statusUpdate);
		
		modelAndView.setViewName("app.editStatus");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/editstatus", method=RequestMethod.POST)
	ModelAndView saveStatus(ModelAndView modelAndView, @Valid StatusUpdate statusUpdate, BindingResult result){
		
		modelAndView.setViewName("app.editStatus");
		
		if (!result.hasErrors()){
			statusUpdateService.save(statusUpdate);
			modelAndView.setViewName("redirect:/viewstatus");
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/deletestatus")
	ModelAndView deleteStatus(ModelAndView modelAndView, @RequestParam(name="id")Long id){
		
		statusUpdateService.delete(id);
		
		modelAndView.setViewName("redirect:/viewstatus");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/viewstatus", method=RequestMethod.GET)
	ModelAndView viewStatus(ModelAndView modelAndView, @RequestParam(name="p", defaultValue="1") int pageNumber){
		
		Page<StatusUpdate> page = statusUpdateService.getPage(pageNumber);
		
		modelAndView.getModel().put("page",  page);
		modelAndView.setViewName("app.viewStatus");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/addstatus", method = RequestMethod.GET)
	ModelAndView addStatus(ModelAndView modelAndView, @ModelAttribute("statusUpdate") StatusUpdate statusUpdate) {
														//Even without the annotation, Spring will actually...
														//create a statusUpdate object and pass it to the model
		modelAndView.setViewName("app.addStatus");

		//StatusUpdate statusUpdate = new StatusUpdate();
		
		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
		//StatusUpdate latestStatusUpdate = statusUpdate;
		
		//modelAndView.getModel().put("statusUpdate", statusUpdate);
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);

		return modelAndView;
	}

	
	@RequestMapping(value = "/addstatus", method = RequestMethod.POST)
	ModelAndView addStatus(ModelAndView modelAndView, @Valid StatusUpdate statusUpdate, BindingResult result) {

		modelAndView.setViewName("app.addStatus");
		
		if (!result.hasErrors()){
			statusUpdateService.save(statusUpdate);
			modelAndView.getModel().put("statusUpdate", new StatusUpdate());
			modelAndView.setViewName("redirect:/viewstatus");
		}
		
		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
		
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);
		
		return modelAndView;
	}
}

