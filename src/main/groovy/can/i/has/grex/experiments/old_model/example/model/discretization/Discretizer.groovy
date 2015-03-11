package can.i.has.grex.experiments.old_model.example.model.discretization

import can.i.has.grex.experiments.old_model.example.model.DataSet

public interface Discretizer {

    public List<Number> getCuts(int attrIdx, DataSet dataSet)
    public DataSet discretize(DataSet dataSet)







}