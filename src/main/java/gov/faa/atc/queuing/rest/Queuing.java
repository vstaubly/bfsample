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
        queue.add(plane);
        return "OK";
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE, produces = {"application/JSON"})
    public Aircraft removePlaneFromQueue()
    {
        Aircraft plane = null;
        for (Aircraft fromQ : queue) {
            if (plane == null) {
                plane = fromQ;
            } else {
                if (plane.compareTo(fromQ) < 0)
                    plane = fromQ;
            }
        }
        queue.remove(plane);
        return plane;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Queuing.class, args);
    }
}
