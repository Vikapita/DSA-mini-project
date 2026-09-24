
public class Main {
    public static void main(String[] args) {


        boolean demoOnly = args.length > 0 && args[0].equalsIgnoreCase("demo");

        if (demoOnly) {
            // ----- Part C experiment (also run in demo-only mode for the report) -----
            SortingExperiment.run();
            System.out.println("Demo-only mode finished. Run 'java Main' (no arguments) "
                    + "to use the interactive Part D menu.");
            return;
        }


        ServiceCentreSystem system = new ServiceCentreSystem();
        system.run();
    }
}
