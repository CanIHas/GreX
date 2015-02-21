package can.i.has.experiments.example.knn

import can.i.has.experiments.example.eval.CrossValidation
import can.i.has.experiments.example.model.ClassificationAlgorithm
import can.i.has.experiments.example.model.Classifier
import can.i.has.experiments.example.model.DataSet
import can.i.has.experiments.example.model.DataSetResources
import can.i.has.experiments.example.model.Instance

import groovy.transform.Canonical

@Canonical
class Knn implements ClassificationAlgorithm{
    //those should be protected, or should supply getters at most
    final Closure<Double> resultInput;//(Instance neighbour, Instance classified, Collection<Instance> neighbourhood)
    final int k;
    final Closure<Double> distance;
    final boolean ignoreSelf

    Knn(Closure<Double> resultInput, int k, Closure<Double> distance, boolean ignoreSelf=false) {
        this.resultInput = resultInput
        this.k = k
        this.distance = distance.memoize()
        this.ignoreSelf = ignoreSelf
    }

    @Override
    Classifier buildClassifier(DataSet trainingSet) {
        return new Model(trainingSet)
    }

    @Canonical
    class Model implements Classifier {

        DataSet knownInstances;

        @Override
        String classify(Instance instance) {
            Map<String, Double> votes = [:].withDefault {0.0}
            def neighbourhood = getNeighbourhood(instance)
            neighbourhood.each {
                votes[it.classValue] += resultInput.call(it, instance, neighbourhood, distance)
            }
            return votes.keySet().max { votes[it] }
        }

        protected Collection<Instance> getNeighbourhood(Instance instance){
            List<Instance> out = []
            knownInstances.instances.each {
                if (!ignoreSelf || !instance.is(it))
                    if (out.size()<k) {
                        out.add it
                        out = out.sort { distance.call(instance.valuesWithoutClass, it.valuesWithoutClass) }
                    }
                    else
                        if (
                        distance.call(
                            instance.valuesWithoutClass,
                            it.valuesWithoutClass
                        )<distance.call(
                            instance.valuesWithoutClass,
                            out[0].valuesWithoutClass)
                        )
                            out = [it] + out[0..(k-2)]
            }
            out
        }
    }

    static void main(String[] args){
        def result = CrossValidation.evaluate(new Knn(ResultInput.weightedWithExp, 3, Distance.euclidean()), DataSetResources.iris)
        println result
    }
}
