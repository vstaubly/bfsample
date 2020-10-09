package gov.faa.atc.queuing.model;

public class Aircraft implements Comparable<Aircraft>
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

    /**
     * Returns the comparative priority for dequeuing of this aircraft and another
     */
    public int compareTo(Aircraft other)
    {
        if (type != other.getType()) {
            switch (type) {
                case Emergency:
                    return 1;
                case VIP:
                    if (other.getType() != Type.Emergency)
                        return 1;
                    else
                        return -1;
                case Passenger:
                    if ((other.getType() != Type.Emergency) && (other.getType() != Type.VIP))
                        return 1;
                    else
                        return -1;
                default:
                    return -1;
            }
        } else if (size != other.getSize()) {
            if (size == Size.Large)
                return 1;
            else
                return -1;
        }
        return 0;
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
