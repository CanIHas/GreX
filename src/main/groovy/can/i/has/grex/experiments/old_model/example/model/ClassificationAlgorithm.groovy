package can.i.has.grex.experiments.old_model.example.model


interface ClassificationAlgorithm {
    Classifier buildClassifier(DataSet trainingSet)
}
