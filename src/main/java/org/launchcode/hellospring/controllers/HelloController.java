package org.launchcode.hellospring.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import javax.xml.ws.RequestWrapper;

//make a new line and use the @ symbol and the word Controller
@Controller
//this is an annotation in java usually above a class or method definition - not a part of the code but helps framework or runtime
public class HelloController {
    @RequestMapping(value = "") // allows the root of our site to be mapped to our controller method
    @ResponseBody // this is to return plain text if we are not using templates
    public String index(HttpServletRequest request) {

        String name = request.getParameter("name");

        if (name == null) { // NULLS are dangerous and to be avoided at all costs!
            name = "World";
        }

       return "Hello " + name;
   }

   @RequestMapping(value = "hello", method = RequestMethod.GET) // this one is for the form input
   @ResponseBody
   public String helloForm() {

        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                "<input type='submit' value='Greet me!' />" +
                "</form>";
        return html;
   }
   @RequestMapping(value = "hello", method = RequestMethod.POST) //only responds to post requests
   @ResponseBody
   public String helloPost(HttpServletRequest request) {

        String name = request.getParameter("name");
        return "Hello " + name;


   }
   @RequestMapping(value =  "create", method = RequestMethod.GET)
   @ResponseBody
   public String createMessage() {

       String html = "<form method='post'>" +
               "<input type='text' name='name' />" +

               "<select id='language' name='language'>\n" + // this is for selecting a language from a drop down box
               "<option value='english'>English</option>\n" +
               "<option value='spanish'>Spanish</option>\n" +
               "<option value='french'>French</option>\n" +
               "<option value='xhosa'>Xhosa</option>\n" +
               "<option value='korean'>Korean</option>\n" +
               "</select>"+ // end of language selection

               "<input type='submit' value='Greet me!' />" +
               "</form>";
       return html;

   }
    @RequestMapping(value = "create", method = RequestMethod.POST) //only responds to post requests
    @ResponseBody
    public String createMessagePost(HttpServletRequest request) {

        String language = request.getParameter("language");
        String name = request.getParameter("name");

        if (language.matches("english")) { // this should be getting the query parameter from select but it is not :/ fall through
            return "Hello " + name;
        } else if (language.matches("spanish")) {
            return "Hola " + name;
        } else if (language.matches("french")) {
            return "Bonjour " + name;
        } else if ( language.matches("xhosa")) {
            return "Mholweni " + name;
        } else if (language.matches("korean")) {
            return "여보세요 " + name;
        } else {
            return "please choose a language next time";
        }

    }

   @RequestMapping(value ="hello/{name}") //this says that {name} is a parameter for the handler method
   @ResponseBody
   public String helloUrlSegment(@PathVariable String name) {// the 'name' needs to match what is in the { }
        return "Hello " + name;
   }

   @RequestMapping(value = "goodbye")
   public String goodbye() {
        return "redirect:/";
   }
}
