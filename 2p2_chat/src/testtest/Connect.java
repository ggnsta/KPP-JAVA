package testtest;

public class Connect extends Thread {

    protected Server serv;

    Connect(String name,Server serv){
        super(name);
        this.serv=serv;

    }
    @Override
    public void run() {

       // serv= new Server(4444);
       for(int i=0 ; i<1;)
        serv.get();
        System.out.printf("%s started... \n", Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
    }
}