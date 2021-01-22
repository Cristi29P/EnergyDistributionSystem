import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Consumer;
import entities.Distributor;
import entities.Producer;
import factories.ConsumerFactory;
import factories.DistributorFactory;
import factories.ProducerFactory;
import input.InputData;
import input.InputLoader;
import input.MonthlyUpdateInput;
import output.OutputData;
import output.OutputGenerator;
import simulation.Simulation;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public final class Main {
    private Main() {
    }
    /**
     * Apparently this method needs a javadoc as well
     * @param args some arguments
     * @throws Exception an exception
     */
    public static void main(final String[] args) throws Exception {
        ObjectMapper objMapper = new ObjectMapper();

        InputData inputData = objMapper.readValue(new File(args[0]), InputData.class);

        ArrayList<Consumer> consumersDB = new ArrayList<>();
        ArrayList<Distributor> distributorDB = new ArrayList<>();
        ArrayList<Producer> producersDB = new ArrayList<>();

        ArrayList<MonthlyUpdateInput> changes = inputData.getMonthlyUpdates();

        ConsumerFactory consumerFactory = ConsumerFactory.getInstance();
        DistributorFactory distributorFactory = DistributorFactory.getInstance();
        ProducerFactory producerFactory = ProducerFactory.getInstance();

        InputLoader loader = new InputLoader();
        loader.loadConsumers(inputData, consumersDB, consumerFactory);
        loader.loadDistributors(inputData, distributorDB, distributorFactory);
        loader.loadProducers(inputData, producersDB, producerFactory);

        Simulation simulation = new Simulation();
        simulation.preSimulation(consumersDB, distributorDB, producersDB);
        for (int i = 0; i < inputData.getNumberOfTurns(); ++i) {
            boolean gameShouldEnd = true;
            simulation.runSimulation(i, consumersDB, distributorDB,
                    producersDB, changes, consumerFactory);

            for (Distributor distributor : distributorDB) {
                if (!distributor.isBankrupt()) {
                    gameShouldEnd = false;
                    break;
                }
            }
            if (gameShouldEnd) {
                break;
            }
        }

        OutputGenerator generator = new OutputGenerator();
        OutputData output = generator.generateOutput(consumersDB, distributorDB, producersDB);
        String jSONString =  new ObjectMapper().writeValueAsString(output);
        FileWriter writer = new FileWriter(args[1]);
        writer.write(jSONString);
        writer.close();
    }
}
