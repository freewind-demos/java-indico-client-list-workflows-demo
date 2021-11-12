package demo;

import com.indico.IndicoConfig;
import com.indico.IndicoKtorClient;
import com.indico.query.ListWorkflows;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Hello {

    private static IndicoConfig loadConfig() throws IOException {
        var properties = new Properties();
        properties.load(new FileReader("config.properties"));

        return new IndicoConfig.Builder()
                .host(properties.getProperty("host"))
                .apiToken(properties.getProperty("indicoToken"))
                .build();
    }

    public static void main(String[] args) throws IOException {
        try (var client = new IndicoKtorClient(loadConfig())) {
            var listWorkflows = new ListWorkflows(client);
            var workflows = listWorkflows.query();
            for (var workflow : workflows) {
                System.out.printf("workflow: id='%s' name='%s' reviewEnabled='%s'\n", workflow.id, workflow.name, workflow.reviewEnabled);
            }
        }
    }

}
