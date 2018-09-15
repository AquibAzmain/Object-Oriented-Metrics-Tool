package cohesion;

public class TestClass {
    int a, b ,c ,d;

    void x(int w){
        a = a + 1;
        int y = a;
        Math.abs(a);
    }

    void y(int x, int y){
        a = b + x;
        x(a);
    }

    void z(){
        System.out.println(d);
        System.out.println(b);
    }
}
