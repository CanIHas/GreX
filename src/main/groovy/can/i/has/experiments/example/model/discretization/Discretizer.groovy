package can.i.has.experiments.example.model.discretization

import can.i.has.experiments.example.model.DataSet

public interface Discretizer {

    public List<Number> getCuts(int attrIdx, DataSet dataSet)
    public DataSet discretize(DataSet dataSet)







}