import java.util.Scanner;

public class gallonExample {
    public static void main(String[] args) {
        float galon=0;
        float milOverGalon=0;
        int count = 110;
        while (galon!=-1)
        {
            float mile;
            System.out.println("Galon Giriniz:");
            galon = new Scanner(System.in).nextFloat();
            if (galon!=-1)
            {
                System.out.println("Mil Giriniz:");
                mile = new Scanner(System.in).nextFloat();
                float tempmilOverGalon = mile/galon;
                milOverGalon+=tempmilOverGalon;
                count++;
                System.out.println("Mil/Over"+ mile/tempmilOverGalon);
            }else{
                System.out.println("Ortalama: "+ (milOverGalon/count));
            }

        }

    }
}
