package com.sensorflare.classification;

import us.jubat.classifier.ClassifierClient;
import us.jubat.classifier.EstimateResult;
import us.jubat.classifier.LabeledDatum;
import us.jubat.common.Datum;

import java.util.Arrays;
import java.util.List;

/**
 * This program classifies a person into male or female.
 */
public class GenderClassifier {

    private static Datum makeDatum(String hair, String top, String bottom,
                                   double height) {
        return new Datum().addString("hair", hair)
                .addString("top", top)
                .addString("bottom", bottom)
                .addNumber("height", height);
    }

    private static LabeledDatum makeTrainDatum(String label, String hair,
                                               String top, String bottom, double height) {
        return new LabeledDatum(label, makeDatum(hair, top, bottom, height));
    }

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 9199;
        String name = "test";

        final ClassifierClient client = new ClassifierClient(host, port, name, 1);

        client.clear();

        LabeledDatum[] trainData = { //
                makeTrainDatum("male", "short", "sweater", "jeans", 1.70),
                makeTrainDatum("female", "long", "shirt", "skirt", 1.56),
                makeTrainDatum("male", "short", "jacket", "chino", 1.65),
                makeTrainDatum("female", "short", "T shirt", "jeans", 1.72),
                makeTrainDatum("male", "long", "T shirt", "jeans", 1.82),
                makeTrainDatum("female", "long", "jacket", "skirt", 1.43),
                // makeTrainDatum("male", "short", "jacket", "jeans", 1.76),
                // makeTrainDatum("female", "long", "sweater", "skirt", 1.52),
        };

        client.train(Arrays.asList(trainData));

        Datum[] testData = {
                makeDatum("short", "T shirt", "jeans", 1.81),
                makeDatum("short", "T shirt", "skirt", 1.50),
        };

        List<List<EstimateResult>> results = client.classify(
                Arrays.asList(testData));

        for (List<EstimateResult> result : results) {
            for (EstimateResult r : result) {
                System.out.printf("%s %f\n", r.label, r.score);
            }
            System.out.println();
        }

        System.exit(0);
    }

}
