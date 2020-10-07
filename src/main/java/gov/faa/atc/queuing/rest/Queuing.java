package gov.faa.atc.queuing.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import gov.faa.atc.queuing.model.Aircraft;

@RestController
@EnableAutoConfiguration
public class Queuing
{
    List<Aircraft> queue = new ArrayList<Aircraft>();

    @RequestMapping(value = "/", produces = {"application/JSON"})
    public List<Aircraft> listQueue()
    {
        return queue;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST /*, consumes = {"application/JSON"} */)
    public String addPlaneToQueue(@RequestBody Aircraft plane)
    {
        // Aircraft plane = new Aircraft("Flight 288", Aircraft.Priority.VIP, Aircraft.Size.Small);
        queue.add(plane);
        return "New queue size " + queue.size();
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public String removePlaneFromQueue()
    {
        Aircraft plane = queue.remove(0); // remove plane at head of queue
        return "Removed from queue: " + plane;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Queuing.class, args);
    }

}
