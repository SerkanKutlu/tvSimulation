
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class televisionSim {
    static ArrayList<channels> channelList= new ArrayList<>();
    static int channelNo=1;
    public static void main(String[] args) {
        System.out.println("#### TELEVİZYONA HOŞ GELDİNİZ. #######");
        System.out.println("Lütfen TV'nize Bir İsim Giriniz: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Kac Inch?");
        int inch= new Scanner(System.in).nextInt();
        television TV = new television(name,inch);


        boolean kontrol= true;
        while(kontrol)
        {
            System.out.println("1- Televizyonu Aç.");
            System.out.println("2- Kanal Ekle");
            System.out.println("3- Kanal Bilgisi Göster");
            System.out.println("4-  Kanal Aç");
            System.out.println("5- Ses Değiştir.");
            System.out.println("6- Hangi Kanaldayım?");
            int choose = new Scanner(System.in).nextInt();
            if (choose==1)
                TV.turnOn();
            else if (choose==2)
                createChannel();
            else if (choose==3)
                showChannelInf();
            else if (choose==5)
            {
                System.out.println("+ OR -");
                String symbol = new Scanner(System.in).nextLine();
                TV.setVolume(symbol);
            }
            else if (choose==4)
            {
                getChannel(TV);
            }
            else if (choose ==6)
            {
                whichChannel(TV);
            }
            else
                System.out.println("Yanlış Seçim Tekrar Giriniz:");
        }



    }

    private static void whichChannel(television tv) {
        for (channels i: channelList)
            if (tv.getActiveChannel() == i.channelNo)
                System.out.println("İzlediğiniz Kanal: "+ i.channelName);
    }
    private static void getChannel(television tv) {
        System.out.println("Kaçıncı Kanala Gitmek İstersiniz?");
        int channelchoose = new Scanner(System.in).nextInt();
        if (channelchoose > channelNo)
            System.out.println("Böyle bir kanal yok.");
        else
            for (channels i : channelList)
                if (i.channelNo == channelchoose)
                {
                    System.out.println(i.channelName+" Kanalı Açıldı");
                    tv.setActiveChannel(channelchoose);
                }
    }
    public static void createChannel()
    {
        System.out.println("Ne Tür Bir Kanal? (Haber:1 Music:2)");
        int type = new Scanner(System.in).nextInt();
        System.out.println("Hangi Kanalı Eklemek İstersiniz? ");
        String chanellName = new Scanner(System.in).nextLine();
        if (type==2)
        {
            System.out.println("Bu Kanalda ne tür müzikler çalıyor? ");
            String musicType = new Scanner(System.in).nextLine();
            channels channel = new musicChanells(channelNo,chanellName,musicType);
            channel.channelType= "MUSIC";
            channelList.add(channel);
            channelNo++;
        }
        else if (type==1)
        {
            System.out.println("Bu Kanal Yerli mi, Yabancı mı ?");
            String newsType = new Scanner(System.in).nextLine();
            channels channel = new newsChannels(channelNo,chanellName,newsType);
            channel.channelType = "NEWS";
            channelList.add(channel);
            channelNo++;
        }else
            System.out.println("Lütfen sadece 1 ve ya 2 Giriniz.");
    }
    public static void showChannelInf()
    {
        System.out.println("Hangi Kanal Bilgilerine Ulaşmak İstersiniz?");
        String channelNamee = new Scanner(System.in).nextLine();
        boolean kontrol=false;
        for (channels i:channelList)
        {
            if (i.channelName.equals(channelNamee))
            {
                System.out.println("######### "+channelNamee+" ##########");
                System.out.println("Kanal İsmi: "+i.channelName);
                System.out.println("Kanal Numarası: "+i.channelNo);
                System.out.println("Kanal Türü: "+i.channelType );
                kontrol=true;
                if (i.channelType.equals("MUSIC"))
                {
                    musicChanells tempChannel = (musicChanells)i;
                    System.out.println("Kanal İçeriği: "+tempChannel.getMusicType());
                }else if (i.channelType.equals("NEWS"))
                {
                    newsChannels tempChannel = (newsChannels)i;
                    System.out.println("Kanal İçeriği: "+tempChannel.nationality+" Haber Kanalı.");
                }
                else
                    System.out.println("Bir Sorun Var.");
            }

        }
        if (!kontrol)
            System.out.println("Böyle bir Kanal Bulunamadı.");

    }
}
class television{
    private boolean status;
    private int volume;
    private int activeChannel;
    private String nameofTv;
    private int inch;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getActiveChannel() {
        return activeChannel;
    }

    public void setActiveChannel(int activeChannel) {
        this.activeChannel = activeChannel;
    }

    public String getNameofTv() {
        return nameofTv;
    }

    public void setNameofTv(String nameofTv) {
        this.nameofTv = nameofTv;
    }

    public int getInch() {
        return inch;
    }

    public void setInch(int inch) {
        this.inch = inch;
    }

    public television(String nameofTv, int inch)
    {
        this.status=false;
        this.nameofTv=nameofTv;
        this.inch=inch;
        System.out.println("Televizyon Oluşturuldu..");
    }

    public void turnOn(){
        if (!status)
        {
            System.out.println("Televizyon Açılıyor:");
            System.out.println("Televizyonun adı: "+ this.nameofTv);
            System.out.println("Televizyonun Boyutu: "+this.inch+"inch");
            this.status = true;
        }
        else
        {
            System.out.println("Televizyon Zaten Açık.");
        }
    }

    public void setVolume(String symbol)
    {
        if (symbol.equals("+"))
        {
            volume++;
            System.out.println("Ses Arttırıldı. Ses Seviyesi: "+this.volume);
        }
        else if (symbol.equals("-"))
        {
            volume--;
            System.out.println("Ses Azaltıldı. Ses Seviyesi: "+this.volume);
        }
        else
            System.out.println("Geçersiz Giriş. Ses Seviyesi: "+this.volume);
    }

}
class channels{
    int channelNo;
    String channelName;
    String channelType;

    public channels(int channelNo,String channelName)
    {
        this.channelName = channelName;
        this.channelNo=channelNo;
    }
}
class newsChannels extends channels{
    String nationality;
    public newsChannels(int channelNo, String channelName,String nationality)
    {
        super(channelNo,channelName);
        this.nationality=nationality;
    }
}
class musicChanells extends channels{
    private String musicType;

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public musicChanells(int channelNo, String ChannelName, String musicType)
    {
        super(channelNo,ChannelName);
        this.musicType=musicType;
    }
}






























