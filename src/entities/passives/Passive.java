package entities.passives;

import logic.PlayManager;
import logs.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Passive  {
public static ArrayList<PassiveType> getAllPassives()
{
    ArrayList<PassiveType> list=new ArrayList<>();
    try {
        File file = new File("passives\\passives.txt");    //creates a new file instance
        FileReader fr = new FileReader(file);   //reads the file
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
        String line;
        while ((line = br.readLine()) != null) {
            list.add(PassiveType.valueOf(line));
        }
        fr.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    catch (Exception e)
    {
        Log.LOGGER.finest("error  "+e.getStackTrace() );

    }
    return list;
}
    public abstract String getJob();
    abstract void doTheJob();
    public static Passive getPassive(PassiveType passiveType)
    {
        Passive passive=null;
        if(passiveType.equals(PassiveType.FreePower)) passive=new FreePower();
        else if (passiveType.equals(PassiveType.ManaJump)) passive=new ManaJump();
        else if (passiveType.equals(PassiveType.Nurse)) passive=new Nurse();
        else if (passiveType.equals(PassiveType.OffCard)) passive=new OffCard();
        else if (passiveType.equals(PassiveType.TwiceDraw)) passive=new TwiceDraw();
        else if (passiveType.equals(PassiveType.Warriors)) passive=new Warriors();
        return passive;
    }
}
