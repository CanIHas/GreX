package can.i.has.experiments.example

import can.i.has.experiments.Experiment
import can.i.has.experiments.ExperimentRunnerRoutines
import can.i.has.experiments.config.FullSearchYield
import can.i.has.experiments.Workspace
import can.i.has.experiments.example.eval.CrossValidation
import can.i.has.experiments.example.eval.Evaluation
import can.i.has.experiments.example.knn.Distance
import can.i.has.experiments.example.knn.Knn
import can.i.has.experiments.example.knn.ResultInput
import can.i.has.experiments.example.model.DataSet
import can.i.has.experiments.example.model.DataSetResources
import can.i.has.experiments.runner.SingleThreadExperimentRunner
import can.i.has.utils.NamedList

//def f = new File("./workspace")
def f = new File("/home/phill/repos/groovy/LaTeX/workspace")
Workspace workspace = new Workspace(f) //todo: add String constructor

def params = [
    "folds",
    "k",
    "dataSet",
    "distance",
    "input"
]
def domains = [
    folds: [2, 3, 5, 10],
    k: [1, 3, 5, 7, 11, 13, 17, 19, 23]
]
def values = [
    dataSet: [
        glass: DataSetResources.glass,
        iris: DataSetResources.iris,
        wine: DataSetResources.wine
    ],
    distance: [
        Manhattan: {x -> Distance.manhattan()},
        Euclidean: {x -> Distance.euclidean()},
        "Minkovsky[3]": {x -> Distance.minkowsky(3)},
        "Minkovsky[5]": {x -> Distance.minkowsky(5)},
        Chebyshev: {x -> Distance.chebyshev()},
//        Mahalanobis: {x -> Distance.mahalanobis(x)} //todo: remove this
    ],
    input: [
        constant: ResultInput.constant,
        proportional: ResultInput.proportional,
        weightedWithDistance: ResultInput.weightedWithDistance,
        weightedWithExp: ResultInput.weightedWithExp,
        weightedWithSquare: ResultInput.weightedWithSquare
    ]
]
try {
    new Experiment<Evaluation>(
         "all",
        workspace.newStorage("all", Evaluation),
        new FullSearchYield<Evaluation>(params, domains, values)
    ).runWith(new SingleThreadExperimentRunner<Evaluation>()) { NamedList config ->
        DataSet clean = config["dataSet"].cleanSet
        def knn = new Knn(config["input"], config["k"], config["distance"](clean))
        def out = CrossValidation.evaluate(knn, clean, config["folds"])
        println "returning $out"
        return out
    }
} catch (Throwable t) {
    t.printStackTrace()
}


//FOLDS = [2, 3, 5, 10]
//KS = [1, 3, 5, 7, 11, 13, 17, 19, 23]
//DATA_SETS = [
//    DataSetResources.glass,
//    DataSetResources.iris,
//    DataSetResources.wine
//]
//DISTANCES = [
//    ["Manhattan", {x -> Distance.manhattan()}],
//    ["Euclidean", {x -> Distance.euclidean()}],
//    ["Minkovsky[3]", {x -> Distance.minkowsky(3)}],
//    ["Minkovsky[5]", {x -> Distance.minkowsky(5)}],
//    ["Chebyshev", {x -> Distance.chebyshev()}],
//    ["Mahalanobis", {x -> Distance.mahalanobis(x)}]
//]
//
//INPUTS = [
//    ["constant", ResultInput.constant],
//    ["proportional", ResultInput.proportional],
//    ["weightedWithDistance", ResultInput.weightedWithDistance],
//    ["weightedWithExp", ResultInput.weightedWithExp],
//    ["weightedWithSquare", ResultInput.weightedWithSquare]
//]
//
//DATA_SETS.each { DataSet ds ->
//    println ds.name
//    println HEADER
//    DISTANCES.each { distDesc ->
//        String distName = distDesc[0]
//        Closure<Closure<Double>> distFactory = distDesc[1];
//        INPUTS.each { inputDesc ->
//            String inputName = inputDesc[0]
//            Closure<Double> input =inputDesc[1]
//            FOLDS.each { int folds ->
//                KS.each { int k ->
//                    DataSet clean = ds.cleanSet
//                    def knn = new Knn(input, k, distFactory(clean))
//                    println([distName, "$inputName", "$folds", "$k", CrossValidation.evaluate(knn, clean, folds).csvLine].join(";"))
//                }
//            }
//        }
//    }
//    println ""
//}
//
