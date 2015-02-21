package can.i.has.experiments.example.model.discretization

import can.i.has.experiments.example.model.DataSet

import groovy.transform.Canonical

@Canonical
class UniformDensityDiscretizer extends AbstractDiscretizer {

    int rangeNumber

    @Override
    List<Double> getCuts(int attrIdx, DataSet dataSet) {
        assert dataSet.scheme.isNumericalAttribute(attrIdx)

        List<Number> vals = dataSet.getAttributeSnapshot(attrIdx).toSet().toList().sort()
        List<Number> out = []
        int perRange = vals.size() / rangeNumber
        for (int i = 1; i < rangeNumber; i++){
            out.add( (vals[i*perRange - 1] + vals[i*perRange]) / 2 )
        }
        out
    }
}
