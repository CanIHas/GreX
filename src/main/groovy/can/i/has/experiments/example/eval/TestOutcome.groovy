package can.i.has.experiments.example.eval

import groovy.transform.Canonical

@Canonical
public class TestOutcome implements Serializable{
    int tp = 0
    int fp = 0
    int tn = 0
    int fn = 0

}
