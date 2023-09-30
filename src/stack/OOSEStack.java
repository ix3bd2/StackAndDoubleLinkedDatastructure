package stack;

public interface OOSEStack {
    void push(Double d);

    Double peek();

    Double pop();

    Integer getSize();

    static OOSEStack sort(OOSEStack stack){return null;}
}
