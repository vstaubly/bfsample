package gov.faa.atc.queuing.model;


public class Aircraft
{
    public enum Type
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
    private Type type;
    private Size size;
    private int priority;

    public Aircraft(String name, Type type, Size size)
    {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getName()
    {
        return name;
    }

    public Type getType()
    {
        return type;
    }

    public Size getSize()
    {
        return size;
    }

    public String priorityString()
    {
        return "" + size + " " + type;
    }

    // this is computed externally based on set of priority rules
    public void setPriority(int pri)
    {
        this.priority = priority;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("Aircraft[");
        if (name != null)
            sb.append("name=" + name + " ");
        sb.append("pri=" + priority + " ");
        if (type != null)
            sb.append("type=" + type + " ");
        if (size != null)
            sb.append("size=" + size + " ");
        sb.append("]");
        return sb.toString();
    }
}
