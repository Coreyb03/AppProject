public class Class_2 extends Class_1{
    private int z;
    
    public Class_2(){
        super();
        z = 0;
    }

    public Class_2(int x1, int y1, int z1){
        super(x1, y1);
        z = z1;
    }

    public void print() {
        System.out.print(super.toString() + z + " ");
    }
  
    public String toString() {
        return super.toString() + z + " ";
    }
  
    public void set(int x1, int y1, int z1) {
        super.set(x1, y1);
        z = z1;
    }
}