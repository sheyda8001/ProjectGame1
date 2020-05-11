package logs;
import game.Main;

import java.io.*;
import java.util.logging.*;
public class Log {
    public final static Logger LOGGER = Logger.getLogger(Log.class.getName());
    private static FileHandler fileHandler;    public static void makelog() {
        try {String st="";
            try {
                File file = new File("logs\\"+ Main.player.getUser()+"-"+(Main.player.getid()+1)+".log");
                FileReader fr = new FileReader(file);   //reads the file
                BufferedReader br = new BufferedReader(fr);
                //creates a buffering character input stream
//            StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
                String line;
                while ((line = br.readLine()) != null) {

                    st+=line+"\n";
                }
                fr.close();    //closes the stream and release the resources
//            System.out.println("Contents of File: ");
//            System.out.println(sb.toString());   //returns a string that textually represents the object
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e)
            {
                Log.LOGGER.finest("error  "+e.getStackTrace());

            }
            LOGGER.setLevel(Level.FINEST);
            fileHandler = new FileHandler("logs\\"+Main.player.getUser()+"-"+(Main.player.getid()+1)+".log");

            LOGGER.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINEST);
            LOGGER.finest("\n"+st);
        }
        catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.LOGGER.finest("error "+e.getStackTrace());

        }

    }
}
