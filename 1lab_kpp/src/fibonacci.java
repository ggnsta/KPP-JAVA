public class fibonacci {

   public long calculate(int n)
   {
       long a = 0;
       long b = 1;
       for (int i = 2; i <= n; ++i) {
           long next = a + b;
           a = b;
           b = next;
       }
       return b;
   }
}
