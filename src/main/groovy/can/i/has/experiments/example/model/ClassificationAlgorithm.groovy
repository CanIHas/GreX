package can.i.has.experiments.example.model


interface ClassificationAlgorithm {
    Classifier buildClassifier(DataSet trainingSet)
}
