package can.i.has.utils

import can.i.has.experiments.Workspace

import groovy.json.JsonBuilder


class ResultPeeker {
    static <T> void prettyPrintResult(Workspace workspace, String experimentName, String key){
        println new JsonBuilder(workspace.newStorage(experimentName).getAt(key)).toPrettyString()
    }

    static void main(String[] args){
        prettyPrintResult(new Workspace("./workspace"), "all_knn", "5.5.wine.Euclidean.weightedWithDistance")
    }
}
