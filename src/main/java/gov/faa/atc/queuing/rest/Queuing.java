package gov.faa.atc.queuing.rest;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import gov.faa.atc.queuing.model.Aircraft;

@RestController
@EnableAutoConfiguration
public class Queuing
{
    @RequestMapping("/")
    String home()
    {
        Aircraft plane = new Aircraft("Flight 288", Aircraft.Priority.VIP, Aircraft.Size.Small);
        return "Hello World! " + plane;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Queuing.class, args);
    }

}
