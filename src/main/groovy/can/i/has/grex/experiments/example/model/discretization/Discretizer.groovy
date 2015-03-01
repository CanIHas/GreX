package can.i.has.grex.experiments.example.model.discretization

import can.i.has.grex.experiments.example.model.DataSet

public interface Discretizer {

    public List<Number> getCuts(int attrIdx, DataSet dataSet)
    public DataSet discretize(DataSet dataSet)







}