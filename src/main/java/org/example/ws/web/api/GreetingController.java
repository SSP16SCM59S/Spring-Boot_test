package org.example.ws.web.api;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import org.example.ws.model.Greeting;
import org.example.ws.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Greeting>> getGreetings() {

        Collection<Greeting> greetings = greetingService.findAll();
        
        if (greetings == null) 
        {
            return new ResponseEntity<Collection<Greeting>>(HttpStatus.NOT_FOUND);
        }
        
        
        return new ResponseEntity<Collection<Greeting>>(greetings,
                HttpStatus.OK);
        
    }

    @RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id) {

        Greeting greeting = greetingService.findOne(id);
        if (greeting == null) 
        {
            return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/download",method = RequestMethod.GET)
    public ResponseEntity<Collection<Greeting>> getGreetings1() {

        Collection<Greeting> greetings = greetingService.findAll();
        
        if (greetings == null) 
        {
            return new ResponseEntity<Collection<Greeting>>(HttpStatus.NOT_FOUND);
        }
        
        
        return new ResponseEntity<Collection<Greeting>>(greetings,
                HttpStatus.OK);
        
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST) 
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes)
    { 
    	Map<String, Object> jsonMap;
    	jsonMap = null;

        if (file.isEmpty()) 
        {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "File Empty";
        }
        else
        {
        	try
        	{
        		InputStream inputStream = file.getInputStream();
        		System.out.println("reached");
        		ObjectMapper mapper = new ObjectMapper();
        		jsonMap = mapper.readValue(inputStream, Map.class);
        		
        		
        		Greeting obj = new Greeting();
        		
        		int temp = Integer.parseInt(jsonMap.get("stock_id").toString());
        		obj.setStock_id(new Long(temp));
        		obj.setStock_name(jsonMap.get("stock_name").toString());
        		obj.setStock_market(jsonMap.get("stock_market").toString());
        		obj.setStock_price(jsonMap.get("stock_price").toString());
        		obj.setLast_update(jsonMap.get("last_update").toString());
        		
        		Greeting savedGreeting = greetingService.create(obj);
        		
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	
        }
        return jsonMap.toString();
    }

    @RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    
    public ResponseEntity<Greeting> createGreeting(
            @RequestBody Greeting greeting) 
    {
    	
        Greeting savedGreeting = greetingService.create(greeting);
        
        return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> updateGreeting(
            @RequestBody Greeting greeting) {

        Greeting updatedGreeting = greetingService.update(greeting);
        if (updatedGreeting == null) {
            return new ResponseEntity<Greeting>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id,
            @RequestBody Greeting greeting) {

        greetingService.delete(id);

        return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
    }

}
