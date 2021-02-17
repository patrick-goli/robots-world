public class Main {

         public static void main(String[] args) throws Exception {
             Monde monde=new Monde(10,10);
             monde.putDirtyPaper(1,2);
             monde.putDirtyPaper(1,6);
             monde.putDirtyPaper(8,4);
             monde.printMatrix();
        }
    }