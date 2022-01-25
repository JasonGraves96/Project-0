

public class Art {
    public static void welcome(){
        System.out.println(" _______   ________  __     __   ______   ________  __    __  _______   ________ ");
        pauser(100);
        System.out.println("/       \\ /        |/  |   /  | /      \\ /        |/  |  /  |/       \\ /        |");
        pauser(100);
        System.out.println("$$$$$$$  |$$$$$$$$/ $$ |   $$ |/$$$$$$  |$$$$$$$$/ $$ |  $$ |$$$$$$$  |$$$$$$$$/ ");
        pauser(100);
        System.out.println("$$ |__$$ |$$ |__    $$ |   $$ |$$ |__$$ |   $$ |   $$ |  $$ |$$ |__$$ |$$ |__    ");
        pauser(100);
        System.out.println("$$    $$< $$    |   $$  \\ /$$/ $$    $$ |   $$ |   $$ |  $$ |$$    $$< $$    |  ");
        pauser(100);
        System.out.println("$$$$$$$  |$$$$$/     $$  /$$/  $$$$$$$$ |   $$ |   $$ |  $$ |$$$$$$$  |$$$$$/    ");
        pauser(100);
        System.out.println("$$ |  $$ |$$ |_____   $$ $$/   $$ |  $$ |   $$ |   $$ \\__$$ |$$ |  $$ |$$ |_____ ");
        pauser(100);
        System.out.println("$$ |  $$ |$$       |   $$$/    $$ |  $$ |   $$ |   $$    $$/ $$ |  $$ |$$       |");
        pauser(100);
        System.out.println("$$/   $$/ $$$$$$$$/     $/     $$/   $$/    $$/     $$$$$$/  $$/   $$/ $$$$$$$$/ ");
        pauser(100);
        System.out.println("<------------------------------------------------------------------------------->");
        pauser(550);
        System.out.println("                   _______    ______   __    __  __    __                       ");
        pauser(100);
        System.out.println("                  /       \\  /      \\ /  \\  /  |/  |  /  |                       ");
        pauser(100);
        System.out.println("                  $$$$$$$  |/$$$$$$  |$$  \\ $$ |$$ | /$$/                        ");
        pauser(100);
        System.out.println("                  $$ |__$$ |$$ |__$$ |$$$  \\$$ |$$ |/$$/                         ");
        pauser(100);
        System.out.println("                  $$    $$< $$    $$ |$$$$  $$ |$$  $$<                          ");
        pauser(100);
        System.out.println("                  $$$$$$$  |$$$$$$$$ |$$ $$ $$ |$$$$$  \\                         ");
        pauser(100);
        System.out.println("                  $$ |__$$ |$$ |  $$ |$$ |$$$$ |$$ |$$  \\                        ");
        pauser(100);
        System.out.println("                  $$    $$/ $$ |  $$ |$$ | $$$ |$$ | $$  |                       ");
        pauser(100);
        System.out.println("                  $$$$$$$/  $$/   $$/ $$/   $$/ $$/   $$/                        ");
        System.out.println("                                                                     -Jason Graves");
        pauser(550);
        System.out.println("\n\nWelcome to Revature Bank!");


    }
    public static void pauser(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
