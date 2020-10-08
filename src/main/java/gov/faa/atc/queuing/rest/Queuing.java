package gov.faa.atc.queuing.rest;

import java.util.ArrayList;
import java.util.HashMap;
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
    public static final String[] priorityList = {
        "Large Emergency",
        "Small Emergency",
        "Large VIP",
        "Small VIP",
        "Large Passenger",
        "Small Passenger",
        "Large Cargo",
        "Small Cargo"
    };
    private HashMap<String, List<Aircraft>> queuesByPriority = new HashMap<String, List<Aircraft>>();

    public Queuing()
    {
        for (String pri : priorityList) {
            queuesByPriority.put(pri, new ArrayList<Aircraft>());
        }
    }

    @RequestMapping(value = "/", produces = {"application/JSON"})
    public List<Aircraft> listQueue()
    {
        List<Aircraft> combinedQueue = new ArrayList<Aircraft>();
        synchronized (queuesByPriority) {
            for (String pri : priorityList) {
                // in real version, list would likely be "paged" (with offset and count)
                //    if (offset > 0), we would decrement offset by queue.size() and then only pull in count elements
                List<Aircraft> queue = queuesByPriority.get(pri);
                if ((queue != null) && (queue.size() > 0))
                    combinedQueue.addAll(queue);
            }
        }
        return combinedQueue;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST /*, consumes = {"application/JSON"} */)
    public String addPlaneToQueue(@RequestBody Aircraft plane)
    {
        String pri = plane.getPriorityString();
        synchronized (queuesByPriority) {
            List<Aircraft> queue = queuesByPriority.get(pri);
            queue.add(plane);
        }
        return "OK";
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE, produces = {"application/JSON"})
    public Aircraft removePlaneFromQueue()
    {
        Aircraft plane = null;
        synchronized (queuesByPriority) {
            for (String pri : priorityList) {
                List<Aircraft> queue = queuesByPriority.get(pri);
                if ((queue != null) && (queue.size() > 0)) {
                    plane = queue.get(0);
                    queue.remove(plane);
                    break;
                }
            }
        }
        return plane;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Queuing.class, args);
    }
}
