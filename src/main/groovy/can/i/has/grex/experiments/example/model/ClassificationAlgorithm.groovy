package can.i.has.grex.experiments.example.model


interface ClassificationAlgorithm {
    Classifier buildClassifier(DataSet trainingSet)
}
