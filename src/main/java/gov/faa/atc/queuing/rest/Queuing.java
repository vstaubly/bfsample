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
import gov.faa.atc.queuing.dao.AircraftRepository;

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
    private HashMap<String, Integer> priorityByName = new HashMap<String, Integer>();

    private AircraftRepository repo;

    public Queuing()
    {
        for (int i = 0; i < priorityList.length; i++) {
            String pri = priorityList[i];
            queuesByPriority.put(pri, new ArrayList<Aircraft>());
            priorityByName.put(pri, i);
            System.out.println("Setting priority for \"" + pri + "\" to " + i);
        }
        repo = new AircraftRepository();
    }

    @RequestMapping(value = "/", produces = {"application/JSON"})
    public List<Aircraft> listQueue()
    {
        List<Aircraft> queueFromDB = repo.listByPriority();
        return queueFromDB;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/JSON"})
    public String addPlaneToQueue(@RequestBody Aircraft plane)
    {
        String pri = plane.priorityString();
        Integer priNum = priorityByName.get(pri);
        if (priNum != null) {
            plane.setPriority(priNum);
        }
        repo.add(plane);
        return "OK";
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE, produces = {"application/JSON"})
    public Aircraft removePlaneFromQueue()
    {
        Aircraft plane = repo.getFirstByPriority();
        if (plane != null)
            repo.removeById(plane.getId());
        return plane;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Queuing.class, args);
    }
}
