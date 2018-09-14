package cohesion;

public class TestClass {
    int a, b ,c ,d;

    void x(){
        a = a + 1;
    }

    void y(){
        a = b + 1;
    }

    void z(){
        System.out.println(d);
    }
}
