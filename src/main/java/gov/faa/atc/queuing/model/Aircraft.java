package gov.faa.atc.queuing.model;

public class Aircraft
{
    public enum Priority
    {
        Emergency,
        VIP,
        Passenger,
        Cargo
    };

    public enum Size
    {
        Small,
        Large
    };

    private String name;
    private Priority priority;
    private Size size;

    public Aircraft(String name, Priority priority, Size size)
    {
        this.name = name;
        this.priority = priority;
        this.size = size;
    }

    public String getName()
    {
        return name;
    }

    public Priority getPriority()
    {
        return priority;
    }

    public Size getSize()
    {
        return size;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("Aircraft[");
        if (name != null)
            sb.append("name=" + name + " ");
        if (priority != null)
            sb.append("priority=" + priority + " ");
        if (size != null)
            sb.append("size=" + size + " ");
        sb.append("]");
        return sb.toString();
    }
}
