package logic.entrance;

        import entities.Player;
        import entities.decks.Deck;
        import game.Main;
        import logs.Log;
        import org.json.simple.JSONArray;
        import org.json.simple.JSONObject;
        import org.json.simple.parser.JSONParser;
        import org.json.simple.parser.ParseException;
        import java.io.*;
        import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;
        import java.util.*;
        import java.io.FileNotFoundException;

public  class Entrance {
    private static int usersNum=0;

    public static int getUsersNum()
    {
        return usersNum;
    }
    public static boolean loginPermission(String userName,String password,String job) {
        Register R = new Register();
        SignIn SI = new SignIn();
        if(job.equals("register"))
        {

            if(R.getIsThere(userName,password,"register")){
                Main.player=(new Player(userName,password));
                Log.makelog();
            return true;}
            else return false;
        }
        else if(job.equals("signin")){
        if(SI.getIsThere(userName, password, "signin"))
        {
            Main.player=new Player(userName,password);
            return true;}
            else return false;
        }
        //Scanner s = new Scanner(System.in);

//        System.out.println("already have an account?(y/n)");
//        String isAcc;
//        isAcc = s.next();
//        if (isAcc.charAt(0) == 'y' || isAcc.charAt(0) == 'Y') {
//
//            while (!SI.getData()) {
//                System.out.println("invalid password or username plz enter again");
//
//            }
//            Main.player=new Player(SI.getUser(),SI.getPass());
//
//        }
//        else{
//            while (!R.getData()) {
//                System.out.println("this username exists plz enter another username");
//            }
//            Main.player=new Player(R.getUser(),R.getPass());
//        }
        return true;


    }
}



class SignIn extends IsItGood
{
    private   Scanner s=new Scanner(System.in);
    private String user,pass;
//    public boolean getData() {
//
//
//        System.out.print("Username: ");
//        user = s.next();
//        System.out.print("Password: ");
//        pass = s.next();
//        if(getIsThere(user, pass, "signin")) {
//            System.out.println("you entered successfully");
//            return true;
//
//        }
//        else{
//            return false;
//        }
//
//
//
//    }
    public String getUser(){
        return user;
    }
    public String getPass()
    {
        return pass;
    }
}
class Register extends IsItGood {
    private boolean ISGOOD=false;
    Scanner s = new Scanner(System.in);
    private String user, pass;
//    public boolean getData() {
//        System.out.print("Username: ");
//        user = s.next();
////         System.out.println();
////         String st=s.nextLine();
//
//        System.out.print("Password: ");
//        pass = s.next();
//        if(getIsThere(user,pass,"register")){
//
//            ISGOOD= true;}
//        else {
//            ISGOOD= false;
//        }
//
//        return ISGOOD;
//    }
    public String getUser()
    {
        return user;
    }
    public String getPass()
    {
        return pass;
    }

    private static int numuser()
    {try {
        String filePath = "usersFile\\numberusers.txt";
        String st = readAllBytesJava7(filePath);
        if (st.equals("")) {
            return 0;
        } else {
            int ans = 0;
            ans = Integer.valueOf(st);
            return ans;
        }
    }
    catch(Exception e)
    {
        Log.LOGGER.finest("error "+e.getStackTrace());

    }
        return 0;

    }
    public static int getUserNum()
    {
        return numuser();
    }
    private static String readAllBytesJava7(String filePath)
    {
        String content = "";

        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            Log.LOGGER.finest("error "+e.getStackTrace());

        }
        return content;
    }

}
class IsItGood  {
    private static boolean isThere(String user, String pass,String job) {
        boolean can = true;
        try {
            int usersNum = Register.getUserNum();
            String[] usepass = new String[2];
            if (!isMade(user, pass, job))
                return false;
        }
        catch (Exception e)
        {
            Log.LOGGER.finest("error "+e.getStackTrace());

        }
        return true;


    }

    public static boolean getIsThere(String user, String pass,String job) {
        return isThere(user, pass,job);
    }

    private static boolean isMade(String user , String pass,String job) {
        boolean IsMade=false;
        String[] usepass = new String[2];


        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("usersFile\\" + user + ".json")) {
// parsing file "JSONExample.json"
            FileReader reade=   new FileReader("usersFile\\" + user + ".json");
            Object obj = new JSONParser().parse(reade);
            reade.close();
            reader.close();
            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // getting firstName and lastName
            String firstName = (String) jo.get("UserName");
            String lastName = (String) jo.get("Password");
            usepass[0]=firstName;
            usepass[1]=lastName;
//            System.out.println(firstName);
//            System.out.println(lastName);
            if(job.equals("signin")) IsMade=true;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            if(job.equals("register")) {
                MakeUser(user, pass);
                IsMade=true;
            }
            else
            {
                IsMade=false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.LOGGER.finest("error "+e.getStackTrace());

        }
        catch(Exception e)
        {
            Log.LOGGER.finest("error "+e.getStackTrace());

        }
        if(IsMade&&job.equals("signin"))
        {
            if(!usepass[1].equals(pass)) IsMade=false;
        }

        return IsMade;
    }
    private void setnum(int n)
    {

    }
    private static void MakeUser(String user, String pass)  {
        JSONObject userDetails = new JSONObject();
        JSONArray array1 = new JSONArray();
        JSONArray array2=new JSONArray();
        JSONArray array3=new JSONArray();
        JSONArray array4=new JSONArray();
        JSONArray array5=new JSONArray();
          array1.add("The Dark Portal");array1.add("Warpath");array1.add("Inner Rage");array1.add("Silence");array1.add("Fungal Fortunes");array1.add("Wisp");array1.add("Vicious Scalehide");array1.add("Novice Engineer");array1.add("Test Subject");array1.add("Polymorph");

        array3.add("MAGE");
        userDetails.put("UserName", user);
        userDetails.put("Password", pass);
        userDetails.put("Coins", 50);
        userDetails.put("WholeCards", array1);
        userDetails.put("NowCards", array2);
        userDetails.put("OpenedHeros",array3);
        userDetails.put("ID",Register.getUserNum());


        JSONObject userObject = new JSONObject();
        userObject.put("user", userDetails);
        //Add users to list
        JSONArray userList = new JSONArray();
        userList.add(userObject);
        //Write JSON file

        try {
            int u= Register.getUserNum()+ 1;
            FileWriter fw = new FileWriter("usersFile\\numberusers.txt");

            fw.write(u + "");
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
            Log.LOGGER.finest("error "+e.getStackTrace());

        }
//        System.out.println("Success...");


        try (FileWriter file1 = new FileWriter("usersFile\\" + user + ".json")) {
            file1.write(userDetails.toJSONString());
            file1.flush();
            file1.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Log.LOGGER.finest("error "+e.getStackTrace());

        }
        try (FileWriter file1 = new FileWriter("logs\\" + user+"-" +( Register.getUserNum())+ ".log")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            file1.write("USER:"+user+"\n"+"CREATED_AT:"+dtf.format(now)+"\n"+"PASSWORD:"+pass+"\n");

            file1.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Log.LOGGER.finest("error "+e.getStackTrace());
        }
        makeUserDeckFile(user);

    }
    private static void makeUserDeckFile(String user)
    {
        JSONObject userDetails = new JSONObject();
        JSONArray array2=new JSONArray();
        array2.add("Classic Mage");
        userDetails.put("Decks",array2);

        for(int i=0;i<array2.size();i++)
        {
            JSONArray array1 = new JSONArray();
            ArrayList<String> cards=Deck.getInstance(array2.get(i).toString()).getCards();
            for(int j=0;j<cards.size();j++)
            {
                array1.add(cards.get(j));
            }
            userDetails.put(array2.get(i).toString(),array1);
        }
      //  array1.add("The Dark Portal");array1.add("Warpath");array1.add("Inner Rage");array1.add("Silence");array1.add("Fungal Fortunes");array1.add("Wisp");array1.add("Vicious Scalehide");array1.add("Novice Engineer");array1.add("Test Subject");array1.add("Polymorph");
      // userDetails.put();
        try (FileWriter file1 = new FileWriter("usersdeck\\" + user + ".json")) {
            file1.write(userDetails.toJSONString());
            file1.flush();
file1.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Log.LOGGER.finest("error "+e.getStackTrace());
        }
    }
}

class DeleteAccount extends IsItGood{
    private Player player;
    private String pass;
    private Scanner s=new Scanner(System.in);
    public DeleteAccount(Player player)
    {
        this.player=player;
    }
    public boolean delete()
    {try {
        while (!isValid()) {
            System.out.println("invalid password or username plz enter again");

        }

        File file = new File("usersFile\\" + player.getUser() + ".json");
        // System.out.println("user is"+file.c);

        if (file.delete()) {
            System.out.println("Your Account Is Deleted");
            return true;
        } else {
            System.out.println("Failed To Delete Account");
            return false;
        }

    }
    catch(Exception e)
    {
        Log.LOGGER.finest("error "+e.getStackTrace());
    }
        return true;
    }
    private boolean isValid()
    {
        System.out.print("Password: ");
        pass = s.next();

        return getIsThere(player.getUser(), pass, "signin");
    }
}
